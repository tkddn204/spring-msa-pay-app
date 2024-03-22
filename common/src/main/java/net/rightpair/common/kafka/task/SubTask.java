package net.rightpair.common.kafka.task;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Builder
public record SubTask (
        String membershipId,
        String subTaskName,
        RechargingMoneyTaskType taskType, // banking, membership
        RechargingMoneyTaskStatus status // success, fail, ready
) {
    @Getter
    @RequiredArgsConstructor
    public enum RechargingMoneyTaskType {
        BANKING("banking"), MEMBERSHIP("membership");
        private final String value;
    }

    @Getter
    @RequiredArgsConstructor
    public enum RechargingMoneyTaskStatus {
        SUCCESS("success"), FAIL("fail"), READY("ready");
        private final String value;
    }

    public static SubTask toSuccess(SubTask subTask) {
        return new SubTask(
                subTask.membershipId,
                subTask.subTaskName,
                subTask.taskType,
                RechargingMoneyTaskStatus.SUCCESS
        );
    }
}
