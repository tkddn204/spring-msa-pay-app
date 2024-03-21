package net.rightpair.money.application.port.in.command;


import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import net.rightpair.common.SelfValidating;

@Getter
@EqualsAndHashCode(callSuper = true)
public class DecreaseMoneyRequestCommand extends SelfValidating<DecreaseMoneyRequestCommand> {

    @NotNull
    private final String targetMembershipId;

    @NotNull
    private final int amount;


    @Builder
    public DecreaseMoneyRequestCommand(String targetMembershipId, int amount) {
        this.targetMembershipId = targetMembershipId;
        this.amount = amount;
        this.validateSelf();
    }
}
