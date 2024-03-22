package net.rightpair.banking.adapter.in.web.endpoint;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.rightpair.banking.adapter.in.web.RegisterBankAccountRequest;
import net.rightpair.banking.domain.RegisteredBankAccount;
import org.springframework.http.ResponseEntity;

@Tag(name = "뱅킹")
public interface RegisterBankAccountEndPoint {
    @Operation(
            method = "POST",
            description = "은행 계좌를 등록하는 엔드포인트"
    )
    ResponseEntity<RegisteredBankAccount> registerBankAccount(
            RegisterBankAccountRequest request
    );
}
