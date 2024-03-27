package net.rightpair.remittance.adapter.in.web.endpoint;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.rightpair.remittance.adapter.in.web.RequestRemittanceRequest;
import net.rightpair.remittance.domain.RemittanceRequest;

@Tag(name="송금")
public interface RequestRemittanceEndPoint {
    @Operation(
            method = "POST",
            description = "송금을 요청하는 엔드포인트"
    )
    RemittanceRequest requestRemittance(RequestRemittanceRequest request);
}
