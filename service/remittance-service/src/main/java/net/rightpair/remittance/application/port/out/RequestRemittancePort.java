package net.rightpair.remittance.application.port.out;

import net.rightpair.remittance.adapter.out.persistence.RemittanceRequestJpaEntity;
import net.rightpair.remittance.application.port.in.RequestRemittanceCommand;

public interface RequestRemittancePort {
    RemittanceRequestJpaEntity createRemittanceRequestHistory(RequestRemittanceCommand command);
    boolean saveRemittanceRequestHistory(RemittanceRequestJpaEntity entity);
}
