package net.rightpair.membership.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import net.rightpair.exception.MembershipNotFoundException;
import net.rightpair.membership.application.port.out.FindMembershipPort;
import net.rightpair.membership.application.port.out.ModifyMembershipPort;
import net.rightpair.membership.application.port.out.RegisterMembershipPort;
import net.rightpair.common.annotation.PersistenceAdapter;
import net.rightpair.membership.domain.Membership;

@PersistenceAdapter
@RequiredArgsConstructor
public class MembershipPersistenceAdapter implements RegisterMembershipPort, FindMembershipPort, ModifyMembershipPort {

    private final SpringDataMembershipRepository membershipRepository;

    @Override
    public MembershipJpaEntity createMembership(
            Membership.MembershipName membershipName,
            Membership.MembershipEmail membershipEmail,
            Membership.MembershipAddress membershipAddress,
            Membership.MembershipIsValid membershipIsValid,
            Membership.MembershipIsCorp membershipIsCorp
    ) {
        return membershipRepository.save(
                MembershipJpaEntity.builder()
                        .name(membershipName.membershipName())
                        .email(membershipEmail.membershipEmail())
                        .address(membershipAddress.membershipAddress())
                        .isValid(membershipIsValid.membershipIsValid())
                        .isCorp(membershipIsCorp.membershipIsCorp())
                        .build()
        );
    }

    @Override
    public MembershipJpaEntity findMembership(Membership.MembershipId membershipId) {
        return membershipRepository.findById(Long.parseLong(membershipId.membershipId()))
                .orElseThrow(MembershipNotFoundException::new);
    }

    @Override
    public MembershipJpaEntity modifyMembership(
            Membership.MembershipId membershipId,
            Membership.MembershipName membershipName,
            Membership.MembershipEmail membershipEmail,
            Membership.MembershipAddress membershipAddress,
            Membership.MembershipIsValid membershipIsValid,
            Membership.MembershipIsCorp membershipIsCorp
    ) {
        MembershipJpaEntity entity = membershipRepository.findById(Long.parseLong(membershipId.membershipId()))
                .orElseThrow(MembershipNotFoundException::new);

        entity.update(
                membershipName.membershipName(),
                membershipEmail.membershipEmail(),
                membershipAddress.membershipAddress(),
                membershipIsValid.membershipIsValid(),
                membershipIsCorp.membershipIsCorp()
        );

        return entity;
    }
}
