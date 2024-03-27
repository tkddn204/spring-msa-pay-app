package net.rightpair.banking.adapter.axon.command;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import net.rightpair.common.SelfValidating;

@Getter
@EqualsAndHashCode(callSuper = true)
@Builder
public class CreateRequestFirmBankingCommand extends SelfValidating<CreateRequestFirmBankingCommand> {
    private final String fromBankName;
    private final String fromBankAccountNumber;
    private final String toBankName;
    private final String toBankAccountNumber;
    private final Integer moneyAmount;
}
