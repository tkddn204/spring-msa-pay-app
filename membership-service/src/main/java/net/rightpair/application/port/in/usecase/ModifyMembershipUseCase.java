package net.rightpair.application.port.in.usecase;

import net.rightpair.application.port.in.command.ModifyMembershipCommand;
import net.rightpair.domain.Membership;

public interface ModifyMembershipUseCase {
    Membership modifyMembership(ModifyMembershipCommand command);
}
