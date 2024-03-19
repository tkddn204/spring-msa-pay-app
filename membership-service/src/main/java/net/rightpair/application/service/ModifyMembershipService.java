package net.rightpair.application.service;

import lombok.RequiredArgsConstructor;
import net.rightpair.adapter.out.persistence.MembershipJpaEntity;
import net.rightpair.adapter.out.persistence.MembershipMapper;
import net.rightpair.application.port.in.command.ModifyMembershipCommand;
import net.rightpair.application.port.in.usecase.ModifyMembershipUseCase;
import net.rightpair.application.port.out.ModifyMembershipPort;
import net.rightpair.domain.Membership;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ModifyMembershipService implements ModifyMembershipUseCase {

    private final ModifyMembershipPort modifyMembershipPort;
    private final MembershipMapper membershipMapper;

    @Override
    public Membership modifyMembership(ModifyMembershipCommand command) {
        MembershipJpaEntity entity = modifyMembershipPort.modifyMembership(
                new Membership.MembershipId(command.getMembershipId()),
                new Membership.MembershipName(command.getName()),
                new Membership.MembershipEmail(command.getEmail()),
                new Membership.MembershipAddress(command.getAddress()),
                new Membership.MembershipIsValid(command.getIsValid()),
                new Membership.MembershipIsCorp(command.getIsCorp())
        );

        return membershipMapper.mapToDomainEntity(entity);
    }
}
