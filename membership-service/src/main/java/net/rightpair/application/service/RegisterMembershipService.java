package net.rightpair.application.service;

import lombok.RequiredArgsConstructor;
import net.rightpair.adapter.out.persistence.MembershipJpaEntity;
import net.rightpair.adapter.out.persistence.MembershipMapper;
import net.rightpair.application.port.in.command.RegisterMembershipCommand;
import net.rightpair.application.port.in.usecase.RegisterMembershipUseCase;
import net.rightpair.application.port.out.RegisterMembershipPort;
import net.rightpair.domain.Membership;
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
