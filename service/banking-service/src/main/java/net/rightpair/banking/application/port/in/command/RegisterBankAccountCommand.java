package net.rightpair.banking.application.port.in.command;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import net.rightpair.common.SelfValidating;

@Getter
@EqualsAndHashCode(callSuper = true)
public class RegisterBankAccountCommand extends SelfValidating<RegisterBankAccountCommand> {

    @NotNull
    private final String membershipId;

    @NotNull
    private final String bankName;

    @NotNull
    private final String bankAccountNumber;

    private final Boolean isValid;

    @Builder
    public RegisterBankAccountCommand(String membershipId, String bankName, String bankAccountNumber, Boolean isValid) {
        this.membershipId = membershipId;
        this.bankName = bankName;
        this.bankAccountNumber = bankAccountNumber;
        this.isValid = isValid == null || isValid;
        this.validateSelf();
    }
}
