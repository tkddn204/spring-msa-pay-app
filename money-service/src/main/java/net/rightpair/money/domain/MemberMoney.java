package net.rightpair.money.domain;

import lombok.Builder;

public record MemberMoney(String memberMoneyId, String membershipId, Integer balance) {
    @Builder
    public MemberMoney { }

    public record MemberMoneyId(String memberMoneyId) { }

    public record MembershipId(String membershipId) { }

    public record MoneyBalance(Integer balance) { }
}
