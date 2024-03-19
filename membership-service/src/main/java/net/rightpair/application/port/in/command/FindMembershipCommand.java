package net.rightpair.application.port.in.command;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import net.rightpair.common.SelfValidating;

@Getter
@EqualsAndHashCode(callSuper = true)
public class FindMembershipCommand extends SelfValidating<FindMembershipCommand> {
    private final String membershipId;

    @Builder
    public FindMembershipCommand(String membershipId) {
        this.membershipId = membershipId;
    }
}
