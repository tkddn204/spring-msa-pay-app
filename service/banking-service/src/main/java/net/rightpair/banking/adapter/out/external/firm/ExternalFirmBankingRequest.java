package net.rightpair.banking.adapter.out.external.firm;

public record ExternalFirmBankingRequest(
        String fromBankName,
        String fromBankAccountNumber,
        String toBankName,
        String toBankAccountNumber
) {
}
