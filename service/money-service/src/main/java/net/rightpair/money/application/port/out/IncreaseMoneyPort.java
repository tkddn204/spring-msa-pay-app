package net.rightpair.money.application.port.out;

import net.rightpair.money.adapter.out.persistence.MemberMoneyJpaEntity;
import net.rightpair.money.domain.MemberMoney;

public interface IncreaseMoneyPort {
    MemberMoneyJpaEntity increaseMoney(
            MemberMoney.MembershipId memberId,
            int increaseMoneyAmount
    );
}
