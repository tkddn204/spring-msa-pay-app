package net.rightpair.remittance.application.service;

import lombok.RequiredArgsConstructor;
import net.rightpair.common.annotation.UseCase;
import net.rightpair.remittance.adapter.out.persistence.RemittanceRequestJpaEntity;
import net.rightpair.remittance.adapter.out.persistence.RemittanceRequestMapper;
import net.rightpair.remittance.application.port.in.RequestRemittanceCommand;
import net.rightpair.remittance.application.port.in.RequestRemittanceUseCase;
import net.rightpair.remittance.application.port.out.RequestRemittancePort;
import net.rightpair.remittance.application.port.out.banking.BankingPort;
import net.rightpair.remittance.application.port.out.membership.MembershipPort;
import net.rightpair.remittance.application.port.out.membership.MembershipStatus;
import net.rightpair.remittance.application.port.out.money.MoneyInfo;
import net.rightpair.remittance.application.port.out.money.MoneyPort;
import net.rightpair.remittance.domain.RemittanceRequest;
import org.springframework.transaction.annotation.Transactional;

import static net.rightpair.remittance.domain.RemittanceRequest.RemittanceType.BANK;
import static net.rightpair.remittance.domain.RemittanceRequest.RemittanceType.MEMBERSHIP;

@UseCase
@Transactional
@RequiredArgsConstructor
public class RequestRemittanceService implements RequestRemittanceUseCase {

    private final RequestRemittancePort requestRemittancePort;
    private final RemittanceRequestMapper mapper;
    private final MembershipPort membershipPort;
    private final MoneyPort moneyPort;
    private final BankingPort bankingPort;

    @Override
    public RemittanceRequest requestRemittance(RequestRemittanceCommand command) {

        // 0. 송금 요청 상태를 시작 상태로 기록
        RemittanceRequestJpaEntity entity = requestRemittancePort.createRemittanceRequestHistory(command);

        // 1. from 멤버십 상태 확인 (멤버쉽 서비스)
        MembershipStatus membershipStatus = membershipPort.getMembershipStatus(command.getFromMembershipId());
        if (!membershipStatus.isValid()) {
            return null;
        }

        // 2. 잔액 존재하는지 확인 (머니 서비스)
        MoneyInfo moneyInfo = moneyPort.getMoneyInfo(command.getFromMembershipId());

        // 잔액이 충분치 않은 경우. -> 충전이 필요한 경우
        if(moneyInfo.balance() < command.getAmount()) {
            // command.getAmount() - moneyInfo.getBalance()
            int rechargeAmount = (int) Math.ceil((command.getAmount() - moneyInfo.balance()) / 10000.0) * 10000;

            // 2-1. 잔액이 충분하지 않다면, 충전 요청 (머니 서비스)
            boolean moneyResult = moneyPort.requestMoneyRecharging(command.getFromMembershipId(), rechargeAmount);
            if (!moneyResult){
                return null;
            }
        }

        // 3. 송금 타입 (고객/은행)
        if (command.getRemittanceType().equals(MEMBERSHIP)) {
            // 3-1. 내부 고객일 경우
            // from 고객 머니 감액, to 고객 머니 증액 (money svc)
            boolean isRequestMoneyDecrease = moneyPort.requestMoneyDecrease(command.getFromMembershipId(), command.getAmount());
            boolean isRequestMoneyIncrease = moneyPort.requestMoneyIncrease(command.getToMembershipId(), command.getAmount());
            if (!isRequestMoneyDecrease || !isRequestMoneyIncrease) {
                return null;
            }
        } else if (command.getRemittanceType().equals(BANK)) {
            // 3-2. 외부 은행 계좌
            // 외부 은행 계좌가 적절한지 확인 (banking svc)
            // 법인계좌 -> 외부 은행 계좌 펌뱅킹 요청 (banking svc)
            boolean isRequestFirmBanking = bankingPort.requestFirmBanking(command.getToBankName(), command.getToBankAccountNumber(), command.getAmount());
            if (!isRequestFirmBanking) {
                return null;
            }
        }

        // 4. 송금 요청 상태를 성공으로 기록 (persistence layer)
        entity.updateRemittanceStatus("success");
        boolean result = requestRemittancePort.saveRemittanceRequestHistory(entity);
        if (result) {
            return mapper.mapToDomainEntity(entity);
        }
        return null;
    }
}
