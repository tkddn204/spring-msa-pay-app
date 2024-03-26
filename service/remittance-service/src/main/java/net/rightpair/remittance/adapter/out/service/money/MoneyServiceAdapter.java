package net.rightpair.remittance.adapter.out.service.money;

import lombok.RequiredArgsConstructor;
import net.rightpair.common.CommonHttpClient;
import net.rightpair.common.annotation.ExternalSystemAdapter;
import net.rightpair.remittance.application.port.out.money.MoneyInfo;
import net.rightpair.remittance.application.port.out.money.MoneyPort;
import org.springframework.beans.factory.annotation.Value;

@ExternalSystemAdapter
@RequiredArgsConstructor
public class MoneyServiceAdapter implements MoneyPort {

    private final CommonHttpClient httpClient;

    @Value("${service.money.url}")
    private String moneyServiceEndpoint;

    @Override
    public MoneyInfo getMoneyInfo(String membershipId) {
        return null;
    }

    @Override
    public boolean requestMoneyRecharging(String membershipId, int amount) {
        return false;
    }

    @Override
    public boolean requestMoneyIncrease(String membershipId, int amount) {
        return false;
    }

    @Override
    public boolean requestMoneyDecrease(String membershipId, int amount) {
        return false;
    }
}
