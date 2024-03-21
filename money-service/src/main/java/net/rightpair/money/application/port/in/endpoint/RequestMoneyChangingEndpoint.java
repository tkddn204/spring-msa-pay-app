package net.rightpair.money.application.port.in.endpoint;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.rightpair.money.adapter.in.web.DecreaseMoneyChangingRequest;
import net.rightpair.money.adapter.in.web.IncreaseMoneyChangingRequest;
import net.rightpair.money.adapter.in.web.MoneyChangingResultDetail;

@Tag(name = "입출금")
public interface RequestMoneyChangingEndpoint {

    @Operation(
            method = "POST",
            description = "계좌의 돈을 입금하는 엔드포인트"
    )
    MoneyChangingResultDetail increaseMoneyChangingRequest(IncreaseMoneyChangingRequest request);

    @Operation(
            method = "POST",
            description = "계좌의 돈을 출금하는 엔드포인트"
    )
    MoneyChangingResultDetail decreaseMoneyChangingRequest(DecreaseMoneyChangingRequest request);
}
