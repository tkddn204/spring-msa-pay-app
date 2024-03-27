package net.rightpair.banking.adapter.in.web;

import lombok.RequiredArgsConstructor;
import net.rightpair.banking.adapter.axon.command.UpdateRequestFirmBankingCommand;
import net.rightpair.banking.adapter.in.web.endpoint.RequestFirmBankingEndPoint;
import net.rightpair.banking.application.port.in.usecase.RequestFirmBankingUsecase;
import net.rightpair.banking.application.port.in.command.RequestFirmBankingCommand;
import net.rightpair.banking.domain.FirmBankingRequest;
import net.rightpair.common.annotation.WebAdapter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequestMapping("/banking")
@RequiredArgsConstructor
public class RequestFirmBankingController implements RequestFirmBankingEndPoint {

    private final RequestFirmBankingUsecase requestFirmBankingUsecase;

    @Override
    @PostMapping("/firm/request")
    public ResponseEntity<FirmBankingRequest> requestFirmBanking(
            @RequestBody RequestFirmBankingRequest request
    ) {
        RequestFirmBankingCommand command = RequestFirmBankingCommand.builder()
                .toBankName(request.toBankName())
                .toBankAccountNumber(request.toBankAccountNumber())
                .fromBankName(request.fromBankName())
                .fromBankAccountNumber(request.fromBankAccountNumber())
                .moneyAmount(request.moneyAmount())
                .build();
        return ResponseEntity.ok(requestFirmBankingUsecase.requestFirmBanking(command));
    }

    @PostMapping(path = "/firm/eda/request")
    void requestFirmBankingByEvent(@RequestBody RequestFirmBankingRequest request) {
        RequestFirmBankingCommand command = RequestFirmBankingCommand.builder()
                .toBankName(request.toBankName())
                .toBankAccountNumber(request.toBankAccountNumber())
                .fromBankName(request.fromBankName())
                .fromBankAccountNumber(request.fromBankAccountNumber())
                .moneyAmount(request.moneyAmount())
                .build();

        requestFirmBankingUsecase.requestFirmBankingByEvent(command);
    }

    @PostMapping(path = "/firm/eda/update")
    void updateFirmBankingByEvent(@RequestBody UpdateFirmBankingRequest request) {
        UpdateRequestFirmBankingCommand command = UpdateRequestFirmBankingCommand.builder()
                .aggregateIdentifier(request.firmBankingAggregateIdentifier())
                .firmBankingStatus(request.status())
                .build();
        requestFirmBankingUsecase.updateFirmBankingByEvent(command);
    }
}
