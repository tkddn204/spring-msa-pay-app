package net.rightpair.money.application.service;

import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.rightpair.common.annotation.UseCase;
import net.rightpair.common.kafka.task.RechargingMoneyTask;
import net.rightpair.common.kafka.task.SubTask;
import net.rightpair.money.adapter.out.persistence.MemberMoneyJpaEntity;
import net.rightpair.money.adapter.out.persistence.MoneyChangingRequestMapper;
import net.rightpair.money.application.port.in.command.DecreaseMoneyRequestCommand;
import net.rightpair.money.application.port.in.command.IncreaseMoneyRequestCommand;
import net.rightpair.money.application.port.in.usecase.AsyncIncreaseMoneyRequestUseCase;
import net.rightpair.money.application.port.in.usecase.DecreaseMoneyRequestUseCase;
import net.rightpair.money.application.port.in.usecase.IncreaseMoneyRequestUseCase;
import net.rightpair.money.application.port.out.DecreaseMoneyPort;
import net.rightpair.money.application.port.out.IncreaseMoneyPort;
import net.rightpair.money.application.port.out.MoneyChangingRequestPort;
import net.rightpair.money.application.port.out.SendRechargingMoneyTaskPort;
import net.rightpair.money.config.CountDownLatchManager;
import net.rightpair.money.domain.MemberMoney;
import net.rightpair.money.domain.MoneyChangingRequest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static net.rightpair.money.domain.MoneyChangingRequest.ChangingMoneyStatusType.FAILED;
import static net.rightpair.money.domain.MoneyChangingRequest.ChangingMoneyStatusType.REQUESTED;
import static net.rightpair.money.domain.MoneyChangingRequest.ChangingType.DECREASING;
import static net.rightpair.money.domain.MoneyChangingRequest.ChangingType.INCREASING;

@Slf4j
@UseCase
@Transactional
@RequiredArgsConstructor
public class MoneyChangingService
        implements IncreaseMoneyRequestUseCase,
        DecreaseMoneyRequestUseCase,
        AsyncIncreaseMoneyRequestUseCase {

    private final MoneyChangingRequestPort moneyChangingRequestPort;
    private final IncreaseMoneyPort increaseMoneyPort;
    private final DecreaseMoneyPort decreaseMoneyPort;
    private final MoneyChangingRequestMapper moneyChangingRequestMapper;
    private final SendRechargingMoneyTaskPort sendRechargingMoneyTaskPort;
    private final CountDownLatchManager countDownLatchManager;

    @Override
    public MoneyChangingRequest increaseMoneyRequest(IncreaseMoneyRequestCommand command) {
        validateMembershipAccountInfo();

        // 4. 증액을 위한 "기록". 요청 상태로 MoneyChangingRequest 를 생성한다. (MoneyChangingRequest)

        // 5. 펌뱅킹을 수행하고 (고객의 연동된 계좌 -> 패캠페이 법인 계좌) (뱅킹)

        // 6-1. 결과가 정상적이라면. 성공으로 MoneyChangingRequest 상태값을 변동 후에 리턴 성공 시에 멤버의 MemberMoney 값 증액이 필요해요
        return getMoneyChangingRequest(command);
    }

    @Override
    public MoneyChangingRequest decreaseMoneyRequest(DecreaseMoneyRequestCommand command) {
        validateMembershipAccountInfo();

        MemberMoneyJpaEntity memberMoneyJpaEntity = decreaseMoneyPort.decreaseMoney(
                new MemberMoney.MembershipId(command.getTargetMembershipId())
                ,command.getAmount());

        if(memberMoneyJpaEntity != null) {
            return moneyChangingRequestMapper.mapToDomainEntity(moneyChangingRequestPort.createMoneyChangingRequest(
                            new MoneyChangingRequest.TargetMembershipId(command.getTargetMembershipId()),
                            new MoneyChangingRequest.MoneyChangingType(DECREASING),
                            new MoneyChangingRequest.ChangingMoneyAmount(command.getAmount()),
                            new MoneyChangingRequest.MoneyChangingStatus(REQUESTED),
                            new MoneyChangingRequest.MoneyChangingUUID(UUID.randomUUID().toString())
                    )
            );
        }

        // 6-2. 결과가 실패라면, 실패라고 MoneyChangingRequest 상태값을 변동 후에 리턴
        return moneyChangingRequestMapper.mapToDomainEntity(moneyChangingRequestPort.createMoneyChangingRequest(
                        new MoneyChangingRequest.TargetMembershipId(command.getTargetMembershipId()),
                        new MoneyChangingRequest.MoneyChangingType(DECREASING),
                        new MoneyChangingRequest.ChangingMoneyAmount(command.getAmount()),
                        new MoneyChangingRequest.MoneyChangingStatus(FAILED),
                        new MoneyChangingRequest.MoneyChangingUUID(UUID.randomUUID().toString())
                )
        );
    }

    @Override
    public MoneyChangingRequest asyncIncreaseMoneyRequest(IncreaseMoneyRequestCommand command) {
        // Count 증가.
        countDownLatchManager.addCountDownLatch("rechargingMoneyTask");
        SubTask validateMembershipIdTask = SubTask.builder()
                .subTaskName("validateMembershipId")
                .membershipId(command.getTargetMembershipId())
                .taskType(SubTask.RechargingMoneyTaskType.MEMBERSHIP)
                .status(SubTask.RechargingMoneyTaskStatus.READY)
                .build();

        SubTask validateBankingTask = SubTask.builder()
                .subTaskName("validateBanking")
                .membershipId(command.getTargetMembershipId())
                .taskType(SubTask.RechargingMoneyTaskType.BANKING)
                .status(SubTask.RechargingMoneyTaskStatus.READY)
                .build();

        List<SubTask> subTaskList = new ArrayList<>();
        subTaskList.add(validateMembershipIdTask);
        subTaskList.add(validateBankingTask);

        RechargingMoneyTask task = RechargingMoneyTask.builder()
                .taskId(UUID.randomUUID().toString())
                .taskName("rechargingMoneyTask")
                .subtaskList(subTaskList)
                .moneyAmount(command.getAmount())
                .membershipId(command.getTargetMembershipId())
                .toBankName("bank") // 법인 계좌 은행 이름
                .build();

        // money increase 를 위한 task 생성, Produce
        sendRechargingMoneyTaskPort.sendRechargingMoneyTask(task);

        try {
            countDownLatchManager.getCountDownLatch("rechargingMoneyTask").await();
            String result = countDownLatchManager.getDataByKey(task.taskId());
            if (result.equals("success")) {
                log.info("success async Money Recharging");
                return getMoneyChangingRequest(command);
            } else {
                return null;
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Nullable
    private MoneyChangingRequest getMoneyChangingRequest(IncreaseMoneyRequestCommand command) {
        MemberMoneyJpaEntity memberMoneyJpaEntity = increaseMoneyPort.increaseMoney(
                new MemberMoney.MembershipId(command.getTargetMembershipId())
                ,command.getAmount());

        if(memberMoneyJpaEntity != null) {
            return moneyChangingRequestMapper.mapToDomainEntity(moneyChangingRequestPort.createMoneyChangingRequest(
                            new MoneyChangingRequest.TargetMembershipId(command.getTargetMembershipId()),
                            new MoneyChangingRequest.MoneyChangingType(INCREASING),
                            new MoneyChangingRequest.ChangingMoneyAmount(command.getAmount()),
                            new MoneyChangingRequest.MoneyChangingStatus(REQUESTED),
                            new MoneyChangingRequest.MoneyChangingUUID(UUID.randomUUID().toString())
                    )
            );
        }

        // 6-2. 결과가 실패라면, 실패라고 MoneyChangingRequest 상태값을 변동 후에 리턴
        return moneyChangingRequestMapper.mapToDomainEntity(moneyChangingRequestPort.createMoneyChangingRequest(
                        new MoneyChangingRequest.TargetMembershipId(command.getTargetMembershipId()),
                        new MoneyChangingRequest.MoneyChangingType(INCREASING),
                        new MoneyChangingRequest.ChangingMoneyAmount(command.getAmount()),
                        new MoneyChangingRequest.MoneyChangingStatus(FAILED),
                        new MoneyChangingRequest.MoneyChangingUUID(UUID.randomUUID().toString())
                )
        );
    }

    private void validateMembershipAccountInfo() {

        // 머니의 충전.증액이라는 과정
        // 1. 고객 정보가 정상인지 확인 (멤버)

        // 2. 고객의 연동된 계좌가 있는지, 고객의 연동된 계좌의 잔액이 충분한지도 확인 (뱅킹)

        // 3. 법인 계좌 상태도 정상인지 확인 (뱅킹)

    }
}
