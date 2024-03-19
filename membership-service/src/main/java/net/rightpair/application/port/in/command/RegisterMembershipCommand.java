package net.rightpair.application.port.in.command;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import net.rightpair.common.SelfValidating;

@Getter
@EqualsAndHashCode(callSuper = true)
public class RegisterMembershipCommand extends SelfValidating<RegisterMembershipCommand> {

    @NotBlank
    private final String name;

    @NotBlank
    private final String email;

    @NotBlank
    private final String address;

    private final boolean isValid;

    private final boolean isCorp;

    @Builder
    public RegisterMembershipCommand(String name, String email, String address, boolean isCorp) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.isValid = true;
        this.isCorp = isCorp;
        this.validateSelf();
    }
}
