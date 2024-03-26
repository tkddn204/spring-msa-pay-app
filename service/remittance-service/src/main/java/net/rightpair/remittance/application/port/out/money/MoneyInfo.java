package net.rightpair.remittance.application.port.out.money;

/**
 * 송금 서비스에서 필요한 은행 정보
 */
public record MoneyInfo(
        String membershipId,
        Integer balance
) {
}
