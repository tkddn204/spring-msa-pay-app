package net.rightpair.banking.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import net.rightpair.banking.application.port.out.RegisteredBankAccountPort;
import net.rightpair.banking.domain.ExternalBankAccount;
import net.rightpair.banking.domain.RegisteredBankAccount;
import net.rightpair.common.annotation.PersistenceAdapter;

@PersistenceAdapter
@RequiredArgsConstructor
public class RegisteredBankAccountPersistenceAdapter implements RegisteredBankAccountPort {
    private final SpringDataRegisteredBankAccountRepository registeredBankAccountRepository;

    @Override
    public RegisteredBankAccountJpaEntity createRegisteredBankAccount(
            RegisteredBankAccount.MembershipId membershipId,
            ExternalBankAccount externalBankAccount
    ) {
        return registeredBankAccountRepository.save(
                RegisteredBankAccountJpaEntity.builder()
                        .membershipId(membershipId.membershipId())
                        .bankName(externalBankAccount.getBankName())
                        .bankAccountNumber(externalBankAccount.getBankAccountNumber())
                        .isValid(true)
                        .build()
        );
    }
}
