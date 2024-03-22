package net.rightpair.membership.application.port.in.usecase;

import net.rightpair.membership.application.port.in.command.ModifyMembershipCommand;
import net.rightpair.membership.domain.Membership;

public interface ModifyMembershipUseCase {
    Membership modifyMembership(ModifyMembershipCommand command);
}
