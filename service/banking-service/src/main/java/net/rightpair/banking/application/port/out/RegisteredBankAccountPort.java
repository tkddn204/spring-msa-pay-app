package net.rightpair.banking.application.port.out;

import net.rightpair.banking.adapter.out.persistence.RegisteredBankAccountJpaEntity;
import net.rightpair.banking.domain.ExternalBankAccount;
import net.rightpair.banking.domain.RegisteredBankAccount;

public interface RegisteredBankAccountPort {
    RegisteredBankAccountJpaEntity createRegisteredBankAccount(
            RegisteredBankAccount.MembershipId membershipId,
            ExternalBankAccount externalBankAccount
    );
}
