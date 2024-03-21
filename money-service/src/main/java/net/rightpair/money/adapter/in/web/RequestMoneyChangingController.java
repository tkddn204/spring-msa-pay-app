package net.rightpair.money.adapter.in.web;

import lombok.RequiredArgsConstructor;
import net.rightpair.common.WebAdapter;
import net.rightpair.money.application.port.in.command.DecreaseMoneyRequestCommand;
import net.rightpair.money.application.port.in.command.IncreaseMoneyRequestCommand;
import net.rightpair.money.application.port.in.endpoint.RequestMoneyChangingEndpoint;
import net.rightpair.money.application.port.in.usecase.DecreaseMoneyRequestUseCase;
import net.rightpair.money.application.port.in.usecase.IncreaseMoneyRequestUseCase;
import net.rightpair.money.domain.MoneyChangingRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static net.rightpair.money.domain.MoneyChangingRequest.ChangingMoneyStatusType.REQUESTED;
import static net.rightpair.money.domain.MoneyChangingRequest.ChangingType.DECREASING;
import static net.rightpair.money.domain.MoneyChangingRequest.ChangingType.INCREASING;

@WebAdapter
@RestController
@RequestMapping("/money")
@RequiredArgsConstructor
public class RequestMoneyChangingController implements RequestMoneyChangingEndpoint {
    private final IncreaseMoneyRequestUseCase increaseMoneyRequestUseCase;
    private final DecreaseMoneyRequestUseCase decreaseMoneyRequestUsecase;

    @Override
    @PostMapping("/increase")
    public MoneyChangingResultDetail increaseMoneyChangingRequest(IncreaseMoneyChangingRequest request) {
        IncreaseMoneyRequestCommand command = IncreaseMoneyRequestCommand.builder()
                .targetMembershipId(request.targetMemberShipId())
                .amount(request.amount())
                .build();
        MoneyChangingRequest moneyChangingRequest = increaseMoneyRequestUseCase.increaseMoneyRequest(command);
        return MoneyChangingResultDetail.generate(
                moneyChangingRequest.moneyChangingRequestId(),
                INCREASING.ordinal(),
                REQUESTED.ordinal(),
                request.amount()
        );
    }

    @Override
    @PostMapping("/decrease")
    public MoneyChangingResultDetail decreaseMoneyChangingRequest(DecreaseMoneyChangingRequest request) {
        DecreaseMoneyRequestCommand command = DecreaseMoneyRequestCommand.builder()
                .targetMembershipId(request.targetMembershipId())
                .amount(request.amount())
                .build();
        MoneyChangingRequest moneyChangingRequest = decreaseMoneyRequestUsecase.decreaseMoneyRequest(command);
        return MoneyChangingResultDetail.generate(
                moneyChangingRequest.moneyChangingRequestId(),
                DECREASING.ordinal(),
                REQUESTED.ordinal(),
                request.amount()
        );
    }
}
