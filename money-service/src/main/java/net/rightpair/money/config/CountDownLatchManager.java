package net.rightpair.money.config;

import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

@Configuration
public class CountDownLatchManager {
    private final Map<String, CountDownLatch> countDownLatchMap = new HashMap<>();
    private final Map<String, String> strMap = new HashMap<>();

    public void addCountDownLatch(String key) {
        this.countDownLatchMap.put(key, new CountDownLatch(1));
    }

    public CountDownLatch getCountDownLatch(String key) {
        return this.countDownLatchMap.get(key);
    }

    public void setDataByKey(String key, String data) {
        this.strMap.put(key, data);
    }

    public String getDataByKey(String key) {
        return this.strMap.get(key);
    }

}
