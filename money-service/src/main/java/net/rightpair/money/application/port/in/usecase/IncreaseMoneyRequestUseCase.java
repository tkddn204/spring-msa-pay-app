package net.rightpair.money.application.port.in.usecase;

import net.rightpair.money.application.port.in.command.IncreaseMoneyRequestCommand;
import net.rightpair.money.domain.MoneyChangingRequest;

public interface IncreaseMoneyRequestUseCase {
    MoneyChangingRequest increaseMoneyRequest(IncreaseMoneyRequestCommand command);
}
