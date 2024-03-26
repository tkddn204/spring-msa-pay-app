package net.rightpair.banking.application.port.out;

public record MembershipStatus(
        String membershipId,
        boolean isValid
) {
}
