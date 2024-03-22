package net.rightpair.common.kafka.task;

import lombok.Builder;

import java.util.List;

@Builder
public record RechargingMoneyTask (
        String taskId,
        String taskName,
        String membershipId,
        List<SubTask> subtaskList,
        // 법인 계좌
        String toBankName,
        // 법인 계좌 번호
        String toBankAccountNumber,
        Integer moneyAmount
) {
}
