package net.rightpair.banking.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RegisteredBankAccount {
    private final String MembershipId;
    private final String bankName;
    private final String bankAccountNumber;

    @JsonProperty("valid")
    private final Boolean isValid;

    public static RegisteredBankAccount generate(
            MembershipId membershipId,
            BankName bankName,
            BankAccountNumber bankAccountNumber,
            BankAccountIsValid bankAccountIsValid

    ) {
        return new RegisteredBankAccount(
                membershipId.membershipId,
                bankName.bankName,
                bankAccountNumber.bankAccountNumber,
                bankAccountIsValid.isValid
        );
    }

    public record MembershipId(String membershipId) { }
    public record BankName(String bankName) { }
    public record BankAccountNumber(String bankAccountNumber) { }
    public record BankAccountIsValid(Boolean isValid) { }
}
