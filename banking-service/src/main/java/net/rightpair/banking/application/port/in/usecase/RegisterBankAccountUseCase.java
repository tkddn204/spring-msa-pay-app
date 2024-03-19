package net.rightpair.banking.application.port.in.usecase;

import net.rightpair.banking.application.port.in.command.RegisterBankAccountCommand;
import net.rightpair.banking.domain.RegisteredBankAccount;

public interface RegisterBankAccountUseCase {
    /**
     * // 은행 계좌를 등록하는 비즈니스 로직
     * @param command 은행 계좌 관련 정보
     * @return 등록되어 있는 은행 계좌
     */
    RegisteredBankAccount registerBankAccount(RegisterBankAccountCommand command);
}
