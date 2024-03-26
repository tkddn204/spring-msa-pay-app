package net.rightpair.money.application.port.out;

import net.rightpair.common.kafka.task.RechargingMoneyTask;

public interface SendRechargingMoneyTaskPort {
    void sendRechargingMoneyTask(RechargingMoneyTask task);
}
