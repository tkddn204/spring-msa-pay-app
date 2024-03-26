package net.rightpair.remittance.application.service;

import lombok.RequiredArgsConstructor;
import net.rightpair.common.annotation.UseCase;
import net.rightpair.remittance.adapter.out.persistence.RemittanceRequestMapper;
import net.rightpair.remittance.application.port.in.FindRemittanceCommand;
import net.rightpair.remittance.application.port.in.FindRemittanceUseCase;
import net.rightpair.remittance.application.port.out.FindRemittancePort;
import net.rightpair.remittance.domain.RemittanceRequest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@UseCase
@Transactional
@RequiredArgsConstructor
public class FindRemittanceService implements FindRemittanceUseCase {

    private final FindRemittancePort findRemittancePort;
    private final RemittanceRequestMapper remittanceRequestMapper;

    @Override
    public List<RemittanceRequest> findRemittanceHistory(FindRemittanceCommand command) {
        return findRemittancePort.findRemittanceHistory(command)
                .stream().map(remittanceRequestMapper::mapToDomainEntity)
                .toList();
    }
}
