package net.rightpair.remittance.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import net.rightpair.common.annotation.PersistenceAdapter;
import net.rightpair.remittance.application.port.in.FindRemittanceCommand;
import net.rightpair.remittance.application.port.in.RequestRemittanceCommand;
import net.rightpair.remittance.application.port.out.FindRemittancePort;
import net.rightpair.remittance.application.port.out.RequestRemittancePort;
import net.rightpair.remittance.domain.RemittanceRequest;

import java.util.List;

@PersistenceAdapter
@RequiredArgsConstructor
public class RemittanceRequestPersistenceAdapter implements FindRemittancePort, RequestRemittancePort {

    private final SpringDataRemittanceRequestRepository remittanceRequestRepository;

    @Override
    public List<RemittanceRequestJpaEntity> findRemittanceHistory(FindRemittanceCommand command) {
        return remittanceRequestRepository.findAllByMembershipId(command.getMembershipId());
    }

    @Override
    public RemittanceRequestJpaEntity createRemittanceRequestHistory(RequestRemittanceCommand command) {
        return remittanceRequestRepository.save(RemittanceRequestJpaEntity.builder()
                .fromMembershipId(command.getFromMembershipId())
                .toMembershipId(command.getToMembershipId())
                .toBankName(command.getToBankName())
                .toBankAccountNumber(command.getToBankAccountNumber())
                .amount(command.getAmount())
                .remittanceType(RemittanceRequest.RemittanceType.values()[command.getRemittanceType()])
                .build());
    }

    @Override
    public boolean saveRemittanceRequestHistory(RemittanceRequestJpaEntity entity) {
        remittanceRequestRepository.save(entity);
        return true;
    }
}
