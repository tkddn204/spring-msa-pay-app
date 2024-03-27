package net.rightpair.banking.adapter.in.web;

import net.rightpair.banking.domain.type.FirmBankingStatusType;

public record UpdateFirmBankingRequest(
        String firmBankingAggregateIdentifier,
        FirmBankingStatusType status
) {
}
