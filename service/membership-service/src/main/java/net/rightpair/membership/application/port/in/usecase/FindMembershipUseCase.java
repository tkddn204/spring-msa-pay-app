package net.rightpair.membership.application.port.in.usecase;

import net.rightpair.membership.application.port.in.command.FindMembershipCommand;
import net.rightpair.membership.domain.Membership;

public interface FindMembershipUseCase {
    Membership findMembership(FindMembershipCommand command);
}
