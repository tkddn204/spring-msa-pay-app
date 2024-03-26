package net.rightpair.membership.adapter.out.persistence;

import net.rightpair.common.annotation.Mapper;
import net.rightpair.membership.domain.Membership;

@Mapper
public class MembershipMapper {
    public Membership mapToDomainEntity(MembershipJpaEntity membershipJpaEntity) {
        return Membership.generate(
                new Membership.MembershipId(String.valueOf(membershipJpaEntity.getMembershipId())),
                new Membership.MembershipName(membershipJpaEntity.getName()),
                new Membership.MembershipEmail(membershipJpaEntity.getEmail()),
                new Membership.MembershipAddress(membershipJpaEntity.getAddress()),
                new Membership.MembershipIsValid(membershipJpaEntity.isValid()),
                new Membership.MembershipIsCorp(membershipJpaEntity.isCorp())
        );
    }
}
