package net.rightpair.banking.application.port.out;

public interface GetMembershipPort {
    MembershipStatus getMembershipStatus(String membershipId);
}
