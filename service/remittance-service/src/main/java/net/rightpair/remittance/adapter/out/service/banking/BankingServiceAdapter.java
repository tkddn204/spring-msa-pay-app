package net.rightpair.remittance.adapter.out.service.banking;

import lombok.RequiredArgsConstructor;
import net.rightpair.common.CommonHttpClient;
import net.rightpair.common.annotation.ExternalSystemAdapter;
import net.rightpair.remittance.application.port.out.banking.BankingInfo;
import net.rightpair.remittance.application.port.out.banking.BankingPort;
import org.springframework.beans.factory.annotation.Value;

@ExternalSystemAdapter
@RequiredArgsConstructor
public class BankingServiceAdapter implements BankingPort {

    private final CommonHttpClient httpClient;

    @Value("${service.banking.url}")
    private String bankingServiceEndpoint;

    @Override
    public BankingInfo getMembershipBankingInfo(String bankName, String bankAccountNumber) {
        String response = httpClient.fakeRequest();
        if (!response.startsWith("200")) {
            throw new RuntimeException();
        }
        return new BankingInfo(bankName, bankAccountNumber, true);
    }

    @Override
    public boolean requestFirmBanking(String bankName, String bankAccountNumber, int amount) {
        return true;
    }
}
