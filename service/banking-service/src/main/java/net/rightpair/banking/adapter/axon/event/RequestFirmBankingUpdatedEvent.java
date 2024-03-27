package net.rightpair.banking.adapter.axon.event;

import net.rightpair.banking.domain.type.FirmBankingStatusType;

public record RequestFirmBankingUpdatedEvent(
        FirmBankingStatusType firmBankingStatus
) {
}
