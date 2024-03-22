package net.rightpair.money.adapter.in.web;

import lombok.RequiredArgsConstructor;
import net.rightpair.common.annotation.WebAdapter;
import net.rightpair.money.adapter.in.web.endpoint.RequestMoneyChangingEndpoint;
import net.rightpair.money.application.port.in.command.DecreaseMoneyRequestCommand;
import net.rightpair.money.application.port.in.command.IncreaseMoneyRequestCommand;
import net.rightpair.money.application.port.in.usecase.AsyncIncreaseMoneyRequestUseCase;
import net.rightpair.money.application.port.in.usecase.DecreaseMoneyRequestUseCase;
import net.rightpair.money.domain.MoneyChangingRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static net.rightpair.money.domain.MoneyChangingRequest.ChangingMoneyStatusType.REQUESTED;
import static net.rightpair.money.domain.MoneyChangingRequest.ChangingType.DECREASING;
import static net.rightpair.money.domain.MoneyChangingRequest.ChangingType.INCREASING;

@WebAdapter
@RestController
@RequestMapping("/money")
@RequiredArgsConstructor
public class AsyncRequestMoneyChangingController implements RequestMoneyChangingEndpoint {
    private final AsyncIncreaseMoneyRequestUseCase increaseMoneyRequestUseCase;
    private final DecreaseMoneyRequestUseCase decreaseMoneyRequestUsecase;

    @Override
    @PostMapping("/increase/async")
    public MoneyChangingResultDetail increaseMoneyChangingRequest(@RequestBody IncreaseMoneyChangingRequest request) {
        IncreaseMoneyRequestCommand command = IncreaseMoneyRequestCommand.builder()
                .targetMembershipId(request.targetMemberShipId())
                .amount(request.amount())
                .build();
        MoneyChangingRequest moneyChangingRequest = increaseMoneyRequestUseCase.asyncIncreaseMoneyRequest(command);
        return MoneyChangingResultDetail.generate(
                moneyChangingRequest.moneyChangingRequestId(),
                INCREASING.ordinal(),
                REQUESTED.ordinal(),
                request.amount()
        );
    }

    @Override
    @PostMapping("/decrease")
    public MoneyChangingResultDetail decreaseMoneyChangingRequest(@RequestBody DecreaseMoneyChangingRequest request) {
        // async 미구현
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
