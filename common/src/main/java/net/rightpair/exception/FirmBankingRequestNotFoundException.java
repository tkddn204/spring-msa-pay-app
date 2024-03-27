package net.rightpair.exception;

public class FirmBankingRequestNotFoundException extends RuntimeException {
    public FirmBankingRequestNotFoundException() {
        super("해당 펌뱅킹 요청 항목이 존재하지 않습니다.");
    }
}
