package net.rightpair.money.adapter.in.web;

public record IncreaseMoneyChangingRequest(
        String targetMemberShipId,
        Integer amount
) {
}
