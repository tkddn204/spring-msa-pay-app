package net.rightpair.banking.adapter.axon.command;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import net.rightpair.banking.domain.type.FirmBankingStatusType;
import net.rightpair.common.SelfValidating;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Getter
@EqualsAndHashCode(callSuper = true)
@Builder
public class UpdateRequestFirmBankingCommand extends SelfValidating<UpdateRequestFirmBankingCommand> {
    @NotNull
    @TargetAggregateIdentifier
    private String aggregateIdentifier;
    private final FirmBankingStatusType firmBankingStatus;
}
