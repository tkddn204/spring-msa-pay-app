package net.rightpair.remittance.application.port.in;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import net.rightpair.common.SelfValidating;

@Getter
public class FindRemittanceCommand extends SelfValidating<FindRemittanceCommand> {
    @NotNull
    private String membershipId;

    @Builder
    public FindRemittanceCommand(String membershipId) {
        this.membershipId = membershipId;
        this.validateSelf();
    }
}
