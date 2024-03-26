package net.rightpair.remittance.application.port.out;

import net.rightpair.remittance.adapter.out.persistence.RemittanceRequestJpaEntity;
import net.rightpair.remittance.application.port.in.FindRemittanceCommand;

import java.util.List;

public interface FindRemittancePort {
    List<RemittanceRequestJpaEntity> findRemittanceHistory(FindRemittanceCommand command);
}
