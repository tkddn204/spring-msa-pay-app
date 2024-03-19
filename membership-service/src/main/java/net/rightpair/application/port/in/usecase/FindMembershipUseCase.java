package net.rightpair.application.port.in.usecase;

import net.rightpair.application.port.in.command.FindMembershipCommand;
import net.rightpair.domain.Membership;

public interface FindMembershipUseCase {
    Membership findMembership(FindMembershipCommand command);
}
