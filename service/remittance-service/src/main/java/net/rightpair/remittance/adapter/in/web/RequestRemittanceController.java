package net.rightpair.remittance.adapter.in.web;

import lombok.RequiredArgsConstructor;
import net.rightpair.common.annotation.WebAdapter;
import net.rightpair.remittance.adapter.in.web.endpoint.RequestRemittanceEndPoint;
import net.rightpair.remittance.application.port.in.RequestRemittanceCommand;
import net.rightpair.remittance.application.port.in.RequestRemittanceUseCase;
import net.rightpair.remittance.domain.RemittanceRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequestMapping("/remittance")
@RequiredArgsConstructor
public class RequestRemittanceController implements RequestRemittanceEndPoint {

    private final RequestRemittanceUseCase requestRemittanceUseCase;

    @Override
    @PostMapping("/request")
    public RemittanceRequest requestRemittance(
            @RequestBody RequestRemittanceRequest request
    ) {
        RequestRemittanceCommand command = RequestRemittanceCommand.builder()
                .fromMembershipId(request.fromMembershipId())
                .toMembershipId(request.toMembershipId())
                .toBankName(request.toBankName())
                .toBankAccountNumber(request.toBankAccountNumber())
                .amount(request.amount())
                .remittanceType(RemittanceRequest.RemittanceType.values()[request.remittanceType()])
                .build();
        return requestRemittanceUseCase.requestRemittance(command);
    }
}
