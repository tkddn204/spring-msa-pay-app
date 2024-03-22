package net.rightpair.money.application.port.in.usecase;

import net.rightpair.money.application.port.in.command.DecreaseMoneyRequestCommand;
import net.rightpair.money.domain.MoneyChangingRequest;

public interface DecreaseMoneyRequestUseCase {
    MoneyChangingRequest decreaseMoneyRequest(DecreaseMoneyRequestCommand command);
}
