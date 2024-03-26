package net.rightpair.remittance.adapter.out.persistence;

import net.rightpair.common.annotation.Mapper;
import net.rightpair.remittance.domain.RemittanceRequest;

@Mapper
public class RemittanceRequestMapper {
    public RemittanceRequest mapToDomainEntity(RemittanceRequestJpaEntity remittanceRequestJpaEntity) {
        return RemittanceRequest.generate(
                new RemittanceRequest.RemittanceRequestId(remittanceRequestJpaEntity.getFromMembershipId()),
                new RemittanceRequest.RemittanceFromMembershipId(remittanceRequestJpaEntity.getFromMembershipId()),
                new RemittanceRequest.ToBankName(remittanceRequestJpaEntity.getToBankName()),
                new RemittanceRequest.ToBankAccountNumber(remittanceRequestJpaEntity.getToBankAccountNumber()),
                new RemittanceRequest.RemittanceTypeValue(remittanceRequestJpaEntity.getRemittanceType()),
                new RemittanceRequest.Amount(remittanceRequestJpaEntity.getAmount()),
                new RemittanceRequest.RemittanceStatus(remittanceRequestJpaEntity.getRemittanceStatus())
        );
    }
}