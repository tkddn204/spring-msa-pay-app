package net.rightpair.remittance.application.port.in;

import net.rightpair.remittance.domain.RemittanceRequest;

public interface RequestRemittanceUseCase {
    RemittanceRequest requestRemittance(RequestRemittanceCommand command);
}
