package net.rightpair.remittance.application.port.in;

import net.rightpair.remittance.domain.RemittanceRequest;

import java.util.List;

public interface FindRemittanceUseCase {
    List<RemittanceRequest> findRemittanceHistory(FindRemittanceCommand command);
}
