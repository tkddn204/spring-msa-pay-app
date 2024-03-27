package net.rightpair.money.adapter.axon.event;

public record IncreaseMoneyRequestEvent(
        String aggregateIdentifier,
        String targetMembershipId,
        Integer amount
) {
}
