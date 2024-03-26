package net.rightpair.membership.application.port.out;

import net.rightpair.membership.adapter.out.persistence.MembershipJpaEntity;
import net.rightpair.membership.domain.Membership;

public interface FindMembershipPort {
    MembershipJpaEntity findMembership(Membership.MembershipId membershipId);
}
