package net.rightpair.membership.application.service;

import lombok.RequiredArgsConstructor;
import net.rightpair.membership.adapter.out.persistence.MembershipJpaEntity;
import net.rightpair.membership.adapter.out.persistence.MembershipMapper;
import net.rightpair.membership.application.port.in.command.RegisterMembershipCommand;
import net.rightpair.membership.application.port.in.usecase.RegisterMembershipUseCase;
import net.rightpair.membership.application.port.out.RegisterMembershipPort;
import net.rightpair.membership.domain.Membership;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class RegisterMembershipService implements RegisterMembershipUseCase {

    private final RegisterMembershipPort registerMembershipPort;
    private final MembershipMapper membershipMapper;

    @Override
    public Membership registerMembership(RegisterMembershipCommand command) {
        MembershipJpaEntity entity = registerMembershipPort.createMembership(
                new Membership.MembershipName(command.getName()),
                new Membership.MembershipEmail(command.getEmail()),
                new Membership.MembershipAddress(command.getAddress()),
                new Membership.MembershipIsValid(command.isValid()),
                new Membership.MembershipIsCorp(command.isCorp())
        );

        return membershipMapper.mapToDomainEntity(entity);
    }
}
