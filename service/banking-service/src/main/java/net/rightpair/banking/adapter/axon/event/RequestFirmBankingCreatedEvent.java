package net.rightpair.banking.adapter.axon.event;

public record RequestFirmBankingCreatedEvent(
        String fromBankName,
        String fromBankAccountNumber,
        String toBankName,
        String toBankAccountNumber,
        Integer moneyAmount
) {
}
