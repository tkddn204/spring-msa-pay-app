package net.rightpair.banking.application.port.in.usecase;

import net.rightpair.banking.application.port.in.command.RequestFirmBankingCommand;
import net.rightpair.banking.domain.FirmBankingRequest;

public interface RequestFirmBankingUsecase {
    FirmBankingRequest requestFirmBanking(RequestFirmBankingCommand command);
}
