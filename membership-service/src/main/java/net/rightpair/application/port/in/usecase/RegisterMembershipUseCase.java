package net.rightpair.application.port.in.usecase;

import net.rightpair.application.port.in.command.RegisterMembershipCommand;
import net.rightpair.common.UseCase;
import net.rightpair.domain.Membership;

@UseCase
public interface RegisterMembershipUseCase {
    Membership registerMembership(RegisterMembershipCommand command);
}
