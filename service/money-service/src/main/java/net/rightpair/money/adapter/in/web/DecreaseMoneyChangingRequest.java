package net.rightpair.money.adapter.in.web;

public record DecreaseMoneyChangingRequest(
        String targetMembershipId,
        Integer amount
) {
}
