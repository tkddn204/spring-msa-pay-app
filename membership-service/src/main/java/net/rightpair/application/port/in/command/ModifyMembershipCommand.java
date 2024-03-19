package net.rightpair.application.port.in.command;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import net.rightpair.common.SelfValidating;

@Getter
@EqualsAndHashCode(callSuper = true)
public class ModifyMembershipCommand extends SelfValidating<ModifyMembershipCommand> {

    private final String membershipId;

    private final String name;

    private final String email;

    private final String address;

    private final Boolean isValid;

    private final Boolean isCorp;

    @Builder
    public ModifyMembershipCommand(String membershipId, String name, String email, String address, Boolean isValid, Boolean isCorp) {
        this.membershipId = membershipId;
        this.name = name;
        this.email = email;
        this.address = address;
        this.isValid = isValid;
        this.isCorp = isCorp;
        this.validateSelf();
    }
}
