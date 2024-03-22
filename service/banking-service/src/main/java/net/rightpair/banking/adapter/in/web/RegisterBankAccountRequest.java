package net.rightpair.banking.adapter.in.web;

public record RegisterBankAccountRequest (
        String membershipId,
        String bankName,
        String bankAccountNumber,
        Boolean isValid
) { }
