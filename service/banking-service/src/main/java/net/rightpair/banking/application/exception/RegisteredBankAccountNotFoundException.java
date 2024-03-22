package net.rightpair.banking.application.exception;

public class RegisteredBankAccountNotFoundException extends RuntimeException {
    public RegisteredBankAccountNotFoundException() {
        super("해당 은행 계정이 존재하지 않습니다.");
    }
}
