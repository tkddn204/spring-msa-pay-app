package net.rightpair.remittance.application.port.out.banking;

/**
 * 송금 서비스에서 필요한 은행 정보
 */
public record BankingInfo(
        String bankName,
        String bankAccountNumber,
        Boolean isValid
) {
}
