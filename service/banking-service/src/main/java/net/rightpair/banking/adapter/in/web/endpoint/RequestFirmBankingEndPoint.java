package net.rightpair.banking.adapter.in.web.endpoint;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.rightpair.banking.adapter.in.web.RequestFirmBankingRequest;
import net.rightpair.banking.domain.FirmBankingRequest;
import org.springframework.http.ResponseEntity;

@Tag(name = "펌뱅킹")
public interface RequestFirmBankingEndPoint {
    @Operation(
            method = "POST",
            description = "펌뱅킹을 요청하는 엔드포인트"
    )
    ResponseEntity<FirmBankingRequest> requestFirmBanking(
            RequestFirmBankingRequest request
    );
}
