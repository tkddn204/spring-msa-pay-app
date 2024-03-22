package net.rightpair.membership.application.port.in.usecase;

import net.rightpair.membership.application.port.in.command.RegisterMembershipCommand;
import net.rightpair.common.annotation.UseCase;
import net.rightpair.membership.domain.Membership;

@UseCase
public interface RegisterMembershipUseCase {
    Membership registerMembership(RegisterMembershipCommand command);
}
