package net.rightpair.membership.application.service;

import lombok.RequiredArgsConstructor;
import net.rightpair.membership.adapter.out.persistence.MembershipJpaEntity;
import net.rightpair.membership.adapter.out.persistence.MembershipMapper;
import net.rightpair.membership.application.port.in.command.FindMembershipCommand;
import net.rightpair.membership.application.port.in.usecase.FindMembershipUseCase;
import net.rightpair.membership.application.port.out.FindMembershipPort;
import net.rightpair.common.UseCase;
import net.rightpair.membership.domain.Membership;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@Transactional
@RequiredArgsConstructor
public class FindMembershipService implements FindMembershipUseCase {

    private final FindMembershipPort findMembershipPort;

    private final MembershipMapper membershipMapper;

    @Override
    public Membership findMembership(FindMembershipCommand command) {
        MembershipJpaEntity entity = findMembershipPort.findMembership(
                new Membership.MembershipId(command.getMembershipId())
        );
        return membershipMapper.mapToDomainEntity(entity);
    }
}
