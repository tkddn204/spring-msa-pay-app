package net.rightpair.money.adapter.out.persistence;


import lombok.RequiredArgsConstructor;
import net.rightpair.common.annotation.PersistenceAdapter;
import net.rightpair.exception.MembershipNotFoundException;
import net.rightpair.money.application.port.out.DecreaseMoneyPort;
import net.rightpair.money.application.port.out.IncreaseMoneyPort;
import net.rightpair.money.application.port.out.MoneyChangingRequestPort;
import net.rightpair.money.domain.MemberMoney;
import net.rightpair.money.domain.MoneyChangingRequest;

import java.time.LocalDateTime;
import java.util.UUID;

@PersistenceAdapter
@RequiredArgsConstructor
public class MoneyChangingRequestPersistenceAdapter
        implements MoneyChangingRequestPort, IncreaseMoneyPort, DecreaseMoneyPort {

    private final SpringDataMoneyChangingRequestRepository moneyChangingRequestRepository;
    private final SpringDataMemberMoneyRepository memberMoneyRepository;

    @Override
    public MoneyChangingRequestJpaEntity createMoneyChangingRequest(
            MoneyChangingRequest.TargetMembershipId targetMembershipId,
            MoneyChangingRequest.MoneyChangingType moneyChangingType,
            MoneyChangingRequest.ChangingMoneyAmount changingMoneyAmount,
            MoneyChangingRequest.MoneyChangingStatus moneyChangingStatus,
            MoneyChangingRequest.MoneyChangingUUID uuid
    ) {
        return moneyChangingRequestRepository.save(
                MoneyChangingRequestJpaEntity.builder()
                        .targetMembershipId(targetMembershipId.targetMembershipId())
                        .moneyChangingType(moneyChangingType.changingType().ordinal())
                        .moneyAmount(changingMoneyAmount.changingMoneyAmount())
                        .timestamp(LocalDateTime.now())
                        .changingMoneyStatus(moneyChangingStatus.changingMoneyStatus().ordinal())
                        .uuid(UUID.randomUUID())
                        .build()
        );
    }

    @Override
    public MemberMoneyJpaEntity increaseMoney(MemberMoney.MembershipId memberId, int increaseMoneyAmount) {
        MemberMoneyJpaEntity entity = memberMoneyRepository.findByMembershipId(Long.parseLong(memberId.membershipId()))
                .orElseThrow(MembershipNotFoundException::new);
        entity.updateBalance(increaseMoneyAmount);
        return  memberMoneyRepository.save(entity);
    }

    @Override
    public MemberMoneyJpaEntity decreaseMoney(MemberMoney.MembershipId memberId, int decreaseMoneyAmount) {
        MemberMoneyJpaEntity entity = memberMoneyRepository.findByMembershipId(Long.parseLong(memberId.membershipId()))
                .orElseThrow(MembershipNotFoundException::new);
        entity.updateBalance(-decreaseMoneyAmount);
        return  memberMoneyRepository.save(entity);
    }
}
