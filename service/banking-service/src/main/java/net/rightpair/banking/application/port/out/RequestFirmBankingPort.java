package net.rightpair.banking.application.port.out;

import net.rightpair.banking.adapter.out.persistence.FirmBankingRequestJpaEntity;
import net.rightpair.banking.domain.FirmBankingRequest;

public interface RequestFirmBankingPort {

    FirmBankingRequestJpaEntity createFirmBankingRequest(
            FirmBankingRequest.FromBankName fromBankName,
            FirmBankingRequest.FromBankAccountNumber fromBankAccountNumber,
            FirmBankingRequest.ToBankName toBankName,
            FirmBankingRequest.ToBankAccountNumber toBankAccountNumber,
            FirmBankingRequest.MoneyAmount moneyAmount,
            FirmBankingRequest.FirmBankingStatus firmBankingStatus
    );

    FirmBankingRequestJpaEntity modifyFirmBankingRequest(
            FirmBankingRequestJpaEntity entity
    );
}
