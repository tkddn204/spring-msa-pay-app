package net.rightpair.application.port.in;

import net.rightpair.common.UseCase;
import net.rightpair.domain.Membership;

@UseCase
public interface RegisterMembershipUseCase {
    Membership registerMembership(RegisterMembershipCommand command);
}
