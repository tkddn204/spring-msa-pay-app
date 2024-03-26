package net.rightpair.banking.application.port.out;

import net.rightpair.banking.adapter.out.external.bank.GetBankAccountRequest;
import net.rightpair.banking.domain.ExternalBankAccount;

public interface RequestExternalBankAccountInfoPort {
    ExternalBankAccount getBankAccountInfo(String membershipId, GetBankAccountRequest request);
}
