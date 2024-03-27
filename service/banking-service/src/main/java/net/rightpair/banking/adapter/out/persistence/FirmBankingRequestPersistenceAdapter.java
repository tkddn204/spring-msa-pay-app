package net.rightpair.banking.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import net.rightpair.banking.application.port.out.RequestFirmBankingPort;
import net.rightpair.banking.domain.FirmBankingRequest;
import net.rightpair.common.annotation.PersistenceAdapter;
import net.rightpair.exception.FirmBankingRequestNotFoundException;

import java.util.UUID;

@PersistenceAdapter
@RequiredArgsConstructor
public class FirmBankingRequestPersistenceAdapter implements RequestFirmBankingPort {

    private final SpringDataFirmBankingRequestRepository firmBankingRequestRepository;

    @Override
    public FirmBankingRequestJpaEntity getFirmBankingRequest(FirmBankingRequest.FirmBankingAggregateIdentifier firmBankingAggregateIdentifier) {
        return firmBankingRequestRepository.findByAggregateIdentifier(firmBankingAggregateIdentifier.firmBankingAggregateIdentifier())
                .orElseThrow(FirmBankingRequestNotFoundException::new);
    }

    @Override
    public FirmBankingRequestJpaEntity createFirmBankingRequest(
            FirmBankingRequest.FromBankName fromBankName,
            FirmBankingRequest.FromBankAccountNumber fromBankAccountNumber,
            FirmBankingRequest.ToBankName toBankName,
            FirmBankingRequest.ToBankAccountNumber toBankAccountNumber,
            FirmBankingRequest.MoneyAmount moneyAmount,
            FirmBankingRequest.FirmBankingStatus firmBankingStatus,
            FirmBankingRequest.FirmBankingAggregateIdentifier firmBankingAggregateIdentifier
    ) {
        return firmBankingRequestRepository.save(FirmBankingRequestJpaEntity.builder()
                .fromBankName(fromBankName.fromBankName())
                .fromBankAccountNumber(fromBankAccountNumber.fromBankAccountNumber())
                .toBankName(toBankName.toBankName())
                .toBankAccountNumber(toBankAccountNumber.toBankAccountNumber())
                .moneyAmount(moneyAmount.moneyAmount())
                .firmBankingStatus(firmBankingStatus.firmBankingStatus())
                .uuid(UUID.randomUUID())
                .aggregateIdentifier(firmBankingAggregateIdentifier.firmBankingAggregateIdentifier())
                .build()
        );
    }

    @Override
    public FirmBankingRequestJpaEntity modifyFirmBankingRequest(FirmBankingRequestJpaEntity entity) {
        return firmBankingRequestRepository.save(entity);
    }
}
