package net.rightpair.money.adapter.in.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import net.rightpair.common.kafka.logging.LoggingProducer;
import net.rightpair.common.kafka.task.RechargingMoneyTask;
import net.rightpair.common.kafka.task.SubTask;
import net.rightpair.money.config.CountDownLatchManager;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

@Slf4j
@Component
public class RechargingMoneyResultConsumer {
    private final KafkaConsumer<String, String> consumer;
    private final LoggingProducer loggingProducer;

    @NotNull
    private final CountDownLatchManager countDownLatchManager;

    public RechargingMoneyResultConsumer(@Value("${kafka.clusters.bootstrapservers}") String bootstrapServers,
                                         @Value("${task.result.topic}")String topic,
                                         LoggingProducer loggingProducer,
                                         CountDownLatchManager countDownLatchManager) {
        this.loggingProducer = loggingProducer;
        this.countDownLatchManager = countDownLatchManager;

        Properties props = new Properties();
        props.put("bootstrap.servers", bootstrapServers);
        props.put("group.id", "my-group");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        this.consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Collections.singletonList(topic));

        new ConsumerThread().run();
    }

    private class ConsumerThread implements Runnable {
        @Override
        public void run() {
            try {
                ObjectMapper mapper = new ObjectMapper();
                while (true) {
                    ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(1));
                    for (ConsumerRecord<String, String> record : records) {
                        System.out.println("Received message: " + record.key()  + " / "+  record.value());

                        Thread.sleep(3000);
                        RechargingMoneyTask task = mapper.readValue(record.value(), RechargingMoneyTask.class);

                        // 모두 정상적으로 성공했다면
                        String taskResultMessage = allTaskDone(task.subtaskList()) ? "success" : "failed";
                        loggingProducer.sendMessage(task.taskId(), "task " + taskResultMessage);
                        countDownLatchManager.setDataByKey(task.taskId(), taskResultMessage);

                        countDownLatchManager.getCountDownLatch("rechargingMoneyTask").countDown();
                    }
                }
            } catch (JsonProcessingException | InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                consumer.close();
            }
        }

        private static boolean allTaskDone(List<SubTask> subTaskList) {
            // validation membership
            // validation banking
            for (SubTask subTask : subTaskList) {
                // 한번만 실패해도 실패한 task 로 간주.
                if (subTask.status().equals(SubTask.RechargingMoneyTaskStatus.FAIL)) {
                    return false;
                }
            }
            return true;
        }
    }

}
