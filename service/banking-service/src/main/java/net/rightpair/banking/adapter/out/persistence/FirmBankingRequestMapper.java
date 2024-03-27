package net.rightpair.banking.adapter.out.persistence;

import net.rightpair.banking.domain.FirmBankingRequest;
import net.rightpair.common.annotation.Mapper;

import java.util.UUID;

@Mapper
public class FirmBankingRequestMapper {
    public FirmBankingRequest mapToDomainEntity(FirmBankingRequestJpaEntity entity, UUID uuid) {
        return FirmBankingRequest.generate(
                new FirmBankingRequest.FirmBankingRequestId(entity.getRequestFirmBankingId() + ""),
                new FirmBankingRequest.FromBankName(entity.getFromBankName()),
                new FirmBankingRequest.FromBankAccountNumber(entity.getFromBankAccountNumber()),
                new FirmBankingRequest.ToBankName(entity.getToBankName()),
                new FirmBankingRequest.ToBankAccountNumber(entity.getToBankAccountNumber()),
                new FirmBankingRequest.MoneyAmount(entity.getMoneyAmount()),
                new FirmBankingRequest.FirmBankingStatus(entity.getFirmBankingStatus()),
                new FirmBankingRequest.FirmBankingRequestUUID(uuid),
                new FirmBankingRequest.FirmBankingAggregateIdentifier(entity.getAggregateIdentifier())
        );
    }
}
