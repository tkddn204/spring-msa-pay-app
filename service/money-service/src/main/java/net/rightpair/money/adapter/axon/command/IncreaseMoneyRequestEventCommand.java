package net.rightpair.money.adapter.axon.command;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import net.rightpair.common.SelfValidating;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Getter
@EqualsAndHashCode(callSuper = true)
@ToString
public class IncreaseMoneyRequestEventCommand extends SelfValidating<IncreaseMoneyRequestEventCommand> {
    @NotNull
    @TargetAggregateIdentifier
    private final String aggregateIdentifier;

    @NotNull
    private final String targetMembershipId;

    @NotNull
    private final Integer amount;

    @Builder

    public IncreaseMoneyRequestEventCommand(String aggregateIdentifier, String targetMembershipId, Integer amount) {
        this.aggregateIdentifier = aggregateIdentifier;
        this.targetMembershipId = targetMembershipId;
        this.amount = amount;

        this.validateSelf();
    }
}
