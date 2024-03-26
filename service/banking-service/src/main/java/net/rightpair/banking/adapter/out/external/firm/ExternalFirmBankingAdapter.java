package net.rightpair.banking.adapter.out.external.firm;

import lombok.RequiredArgsConstructor;
import net.rightpair.banking.application.port.out.RequestExternalFirmBankingPort;
import net.rightpair.common.CommonHttpClient;
import net.rightpair.common.annotation.ExternalSystemAdapter;

@ExternalSystemAdapter
@RequiredArgsConstructor
public class ExternalFirmBankingAdapter implements RequestExternalFirmBankingPort {
    @Override
    public FirmBankingResult requestExternalFirmBanking(ExternalFirmBankingRequest request) {
        // 실제로 외부 은행에 http 통신을 통해서
        // 펌뱅킹 요청을 하고
        String response = CommonHttpClient.fakeRequest();

        if (!response.startsWith("200")) {
            throw new RuntimeException("fake request fail");
        }

        // 그 결과를
        // 외부 은행의 실제 결과를 -> 패캠 페이의 FirmbankingResult 파싱
        return new FirmBankingResult(0);
    }
}
