package net.rightpair.money.adapter.out.persistence;

import net.rightpair.common.annotation.Mapper;
import net.rightpair.money.domain.MoneyChangingRequest;

@Mapper
public class MoneyChangingRequestMapper {
    public MoneyChangingRequest mapToDomainEntity(MoneyChangingRequestJpaEntity moneyChangingRequestJpaEntity) {
        return MoneyChangingRequest.generate(
                new MoneyChangingRequest.MoneyChangingRequestId(moneyChangingRequestJpaEntity.getMoneyChangingRequestId()+""),
                new MoneyChangingRequest.TargetMembershipId(moneyChangingRequestJpaEntity.getTargetMembershipId()),
                MoneyChangingRequest.MoneyChangingType.generate(moneyChangingRequestJpaEntity.getMoneyChangingType()),
                new MoneyChangingRequest.ChangingMoneyAmount(moneyChangingRequestJpaEntity.getMoneyAmount()),
                MoneyChangingRequest.MoneyChangingStatus.generate(moneyChangingRequestJpaEntity.getChangingMoneyStatus()),
                moneyChangingRequestJpaEntity.getUuid()
        );
    }
}
