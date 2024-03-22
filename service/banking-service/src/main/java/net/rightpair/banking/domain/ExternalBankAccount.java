package net.rightpair.banking.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ExternalBankAccount {
    private final String bankName;
    private final String bankAccountNumber;
    private Boolean isValid;

    public static ExternalBankAccount generate(
            BankName bankName,
            BankAccountNumber bankAccountNumber,
            BankAccountIsValid bankAccountIsValid
    ) {
        return new ExternalBankAccount(
                bankName.bankName,
                bankAccountNumber.bankAccountNumber,
                bankAccountIsValid.isValid
        );
    }

    public record BankName(String bankName) { }
    public record BankAccountNumber(String bankAccountNumber) { }
    public record BankAccountIsValid(Boolean isValid) { }
}
