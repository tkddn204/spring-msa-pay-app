package net.rightpair.remittance.adapter.in.web;

import lombok.RequiredArgsConstructor;
import net.rightpair.common.annotation.WebAdapter;
import net.rightpair.remittance.adapter.in.web.endpoint.FindRemittanceHistoryEndPoint;
import net.rightpair.remittance.application.port.in.FindRemittanceCommand;
import net.rightpair.remittance.application.port.in.FindRemittanceUseCase;
import net.rightpair.remittance.domain.RemittanceRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@WebAdapter
@RestController
@RequestMapping("/remittance")
@RequiredArgsConstructor
public class FindRemittanceHistoryController implements FindRemittanceHistoryEndPoint {

    private final FindRemittanceUseCase findRemittanceUseCase;

    @Override
    @GetMapping("/{membershipId}")
    public List<RemittanceRequest> findRemittanceHistory(
            @PathVariable  String membershipId
    ) {
        FindRemittanceCommand command = FindRemittanceCommand.builder()
                .membershipId(membershipId)
                .build();
        return findRemittanceUseCase.findRemittanceHistory(command);
    }
}
