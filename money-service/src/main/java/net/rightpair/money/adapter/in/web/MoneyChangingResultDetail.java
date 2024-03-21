package net.rightpair.money.adapter.in.web;

public record MoneyChangingResultDetail(
        String moneyChangingRequestId,
        Integer moneyChangingType, // 0: 증액, 1: 감액
        Integer moneyChangingResultStatus, // 0: 성공, 실패, 실패 - 잔액 부족, 실패 - 멤버십 없음, 실패 - 머니 변액 요청 없음
        Integer amount
) {
    public static MoneyChangingResultDetail generate(
            String moneyChangingRequestId,
            Integer moneyChangingType,
            Integer moneyChangingResultStatus,
            Integer amount
    ) {
        return new MoneyChangingResultDetail(
                moneyChangingRequestId,
                moneyChangingType,
                moneyChangingResultStatus,
                amount
        );
    }
}
