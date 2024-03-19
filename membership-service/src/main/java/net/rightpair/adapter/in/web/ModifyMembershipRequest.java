package net.rightpair.adapter.in.web;

public record ModifyMembershipRequest (
        String name,
        String address,
        String email,
        Boolean isValid,
        Boolean isCorp
) {
}
