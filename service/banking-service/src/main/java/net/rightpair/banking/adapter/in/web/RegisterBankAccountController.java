package net.rightpair.banking.adapter.in.web;

import lombok.RequiredArgsConstructor;
import net.rightpair.banking.application.port.in.command.RegisterBankAccountCommand;
import net.rightpair.banking.adapter.in.web.endpoint.RegisterBankAccountEndPoint;
import net.rightpair.banking.application.port.in.usecase.RegisterBankAccountUseCase;
import net.rightpair.banking.domain.RegisteredBankAccount;
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
public class RegisterBankAccountController implements RegisterBankAccountEndPoint {

    private final RegisterBankAccountUseCase registerBankAccountUseCase;

    @Override
    @PostMapping("/register/account")
    public ResponseEntity<RegisteredBankAccount> registerBankAccount(
            @RequestBody RegisterBankAccountRequest request
    ) {
        RegisterBankAccountCommand command = RegisterBankAccountCommand.builder()
                .membershipId(request.membershipId())
                .bankName(request.bankName())
                .bankAccountNumber(request.bankAccountNumber())
                .isValid(request.isValid())
                .build();
        return ResponseEntity.ok(registerBankAccountUseCase.registerBankAccount(command));
    }
}
