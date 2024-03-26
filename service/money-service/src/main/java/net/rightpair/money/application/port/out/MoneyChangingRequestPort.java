package net.rightpair.money.application.port.out;

import net.rightpair.money.adapter.out.persistence.MoneyChangingRequestJpaEntity;
import net.rightpair.money.domain.MoneyChangingRequest;

public interface MoneyChangingRequestPort {
    MoneyChangingRequestJpaEntity createMoneyChangingRequest(
            MoneyChangingRequest.TargetMembershipId targetMembershipId,
            MoneyChangingRequest.MoneyChangingType moneyChangingType,
            MoneyChangingRequest.ChangingMoneyAmount changingMoneyAmount,
            MoneyChangingRequest.MoneyChangingStatus moneyChangingStatus,
            MoneyChangingRequest.MoneyChangingUUID uuid
    );
}
