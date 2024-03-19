package net.rightpair.application.service;

import lombok.RequiredArgsConstructor;
import net.rightpair.adapter.out.persistence.MembershipJpaEntity;
import net.rightpair.adapter.out.persistence.MembershipMapper;
import net.rightpair.application.port.in.command.FindMembershipCommand;
import net.rightpair.application.port.in.usecase.FindMembershipUseCase;
import net.rightpair.application.port.out.FindMembershipPort;
import net.rightpair.common.UseCase;
import net.rightpair.domain.Membership;
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
