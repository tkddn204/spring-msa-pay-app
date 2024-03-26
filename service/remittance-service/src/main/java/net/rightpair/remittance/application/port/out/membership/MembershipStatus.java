package net.rightpair.remittance.application.port.out.membership;

/**
 * 송금 서비스에서 필요한 은행 정보
 */
public record MembershipStatus(
        String membershipId,
        Boolean isValid
) {
}
