package net.rightpair.money.application.port.out;

import net.rightpair.money.adapter.out.persistence.MemberMoneyJpaEntity;
import net.rightpair.money.domain.MemberMoney;

public interface DecreaseMoneyPort {
    MemberMoneyJpaEntity decreaseMoney(
            MemberMoney.MembershipId memberId,
            int decreaseMoneyAmount
    );
}
