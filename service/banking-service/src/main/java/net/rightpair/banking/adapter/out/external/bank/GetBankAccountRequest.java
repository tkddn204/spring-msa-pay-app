package net.rightpair.banking.adapter.out.external.bank;

public record GetBankAccountRequest (
        String bankName,
        String bankAccountNumber
) {
}
