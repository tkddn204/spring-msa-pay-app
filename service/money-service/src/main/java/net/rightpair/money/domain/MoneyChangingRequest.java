package net.rightpair.money.domain;

import java.time.LocalDateTime;

/**
 * @param targetMembershipId  요청한 고객의 멤버 정보
 * @param changingType        그 요청이 증액 요청인지 / 감액 요청인지 enum. 0: 증액, 1: 감액
 * @param changingMoneyAmount 증액 또는 감액 요청의 금액
 */
public record MoneyChangingRequest(
        String moneyChangingRequestId,
        String targetMembershipId,
        ChangingType changingType,
        Integer changingMoneyAmount,
        ChangingMoneyStatusType changingMoneyStatus,
        String uuid, LocalDateTime createdAt
) {
    public enum ChangingType {
        INCREASING, DECREASING
    }

    public enum ChangingMoneyStatusType {
        REQUESTED, SUCCEEDED, FAILED, CANCELLED
    }

    public static MoneyChangingRequest generate(MoneyChangingRequestId moneyChangingRequestId, TargetMembershipId targetMembershipId, MoneyChangingType moneyChangingType, ChangingMoneyAmount changingMoneyAmount, MoneyChangingStatus moneyChangingStatus, String uuid) {
        return new MoneyChangingRequest(
                moneyChangingRequestId.moneyChangingRequestId,
                targetMembershipId.targetMembershipId,
                moneyChangingType.changingType,
                changingMoneyAmount.changingMoneyAmount,
                moneyChangingStatus.changingMoneyStatus,
                uuid,
                LocalDateTime.now()
        );
    }

    public record MoneyChangingRequestId(String moneyChangingRequestId) {
    }

    public record TargetMembershipId(String targetMembershipId) {
    }

    public record MoneyChangingType(ChangingType changingType) {
        public static MoneyChangingType generate(int order) {
            return new MoneyChangingType(ChangingType.values()[order]);
        }

        @Override
        public String toString() {
            return changingType.ordinal() + "";
        }
    }

    public record ChangingMoneyAmount(Integer changingMoneyAmount) {
    }

    public record MoneyChangingStatus(ChangingMoneyStatusType changingMoneyStatus) {
        public static MoneyChangingStatus generate(int order) {
            return new MoneyChangingStatus(ChangingMoneyStatusType.values()[order]);
        }

        @Override
        public String toString() {
            return changingMoneyStatus.ordinal() + "";
        }
    }

    public record MoneyChangingUUID(String uuid) {
    }

    public record MoneyChangingCreatedAt(LocalDateTime createdAt) {
    }

}
