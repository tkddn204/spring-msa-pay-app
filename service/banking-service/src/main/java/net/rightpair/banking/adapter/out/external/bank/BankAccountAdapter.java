package net.rightpair.banking.adapter.out.external.bank;

import lombok.RequiredArgsConstructor;
import net.rightpair.banking.application.port.out.RequestExternalBankAccountInfoPort;
import net.rightpair.banking.domain.ExternalBankAccount;
import net.rightpair.common.CommonHttpClient;
import net.rightpair.common.annotation.ExternalSystemAdapter;

@ExternalSystemAdapter
@RequiredArgsConstructor
public class BankAccountAdapter implements RequestExternalBankAccountInfoPort {

    @Override
    public ExternalBankAccount getBankAccountInfo(
            String membershipId,
            GetBankAccountRequest request) {

        // 실제로는 외부 은행에 http를 통해서 은행 계좌 정보를 가져옴
        // 실제 은행 계좌 -> BankAccount
        String response = CommonHttpClient.fakeRequest();

        if (!response.startsWith("200")) {
            throw new RuntimeException("fake request fail");
        }

        return ExternalBankAccount.generate(
                new ExternalBankAccount.BankName(request.bankName()),
                new ExternalBankAccount.BankAccountNumber(request.bankAccountNumber()),
                new ExternalBankAccount.BankAccountIsValid(true)
        );
    }
}
