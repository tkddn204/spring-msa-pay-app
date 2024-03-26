package net.rightpair.remittance.application.port.out.membership;

public interface MembershipPort {
    MembershipStatus getMembershipStatus(String membershipId);
}
