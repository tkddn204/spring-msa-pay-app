package net.rightpair.remittance.adapter.in.web.endpoint;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.rightpair.remittance.domain.RemittanceRequest;

import java.util.List;

@Tag(name="송금")
public interface FindRemittanceHistoryEndPoint {
    @Operation(
            method = "GET",
            description = "송금 내역을 조회하는 엔드포인트"
    )
    List<RemittanceRequest> findRemittanceHistory(String membershipId);
}
