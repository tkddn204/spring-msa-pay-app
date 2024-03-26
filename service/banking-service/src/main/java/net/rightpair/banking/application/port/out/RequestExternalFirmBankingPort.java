package net.rightpair.banking.application.port.out;

import net.rightpair.banking.adapter.out.external.firm.ExternalFirmBankingRequest;
import net.rightpair.banking.adapter.out.external.firm.FirmBankingResult;

public interface RequestExternalFirmBankingPort {
    FirmBankingResult requestExternalFirmBanking(ExternalFirmBankingRequest request);
}
