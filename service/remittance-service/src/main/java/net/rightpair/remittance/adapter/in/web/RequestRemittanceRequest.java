package net.rightpair.remittance.adapter.in.web;

public record RequestRemittanceRequest (
        String fromMembershipId,
        String toMembershipId,

        String toBankName,
        String toBankAccountNumber,

        Integer remittanceType,
        Integer amount
) {
}
