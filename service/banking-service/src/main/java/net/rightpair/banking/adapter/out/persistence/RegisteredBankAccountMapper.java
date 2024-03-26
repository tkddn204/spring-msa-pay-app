package net.rightpair.banking.adapter.out.persistence;

import net.rightpair.banking.domain.RegisteredBankAccount;
import net.rightpair.common.annotation.Mapper;

@Mapper
public class RegisteredBankAccountMapper {
    public RegisteredBankAccount mapToDomainEntity(RegisteredBankAccountJpaEntity entity) {
        return RegisteredBankAccount.generate(
                new RegisteredBankAccount.MembershipId(entity.getMembershipId()),
                new RegisteredBankAccount.BankName(entity.getBankName()),
                new RegisteredBankAccount.BankAccountNumber(entity.getBankAccountNumber()),
                new RegisteredBankAccount.BankAccountIsValid(entity.getIsValid())
        );
    }
}
