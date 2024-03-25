package net.rightpair.taskconsumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.rightpair.common.kafka.task.RechargingMoneyTask;
import net.rightpair.common.kafka.task.SubTask;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Value;

import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import static net.rightpair.common.kafka.task.SubTask.RechargingMoneyTaskStatus.READY;

public class TaskConsumer {
    private final KafkaConsumer<String, String> consumer;
    private final TaskResultProducer taskResultProducer;
    public TaskConsumer(@Value("${kafka.clusters.bootstrapservers}")String bootstrapServers,
                           @Value("${logging.topic}")String topic,
                        TaskResultProducer taskResultProducer) {
        this.taskResultProducer = taskResultProducer;

        Properties props = new Properties();

        props.put("bootstrap.servers", bootstrapServers);

        // consumer group
        props.put("group.id", "my-group");

        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        this.consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Collections.singletonList(topic));
        Thread consumerThread = new Thread(() -> {
            try {
                while (true) {
                    ObjectMapper objectMapper = new ObjectMapper();
                    ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(1));
                    for (ConsumerRecord<String, String> record : records) {
                        System.out.println("Received message: " + record.value());

                        try {
                            RechargingMoneyTask tasks = objectMapper.readValue(record.value(), RechargingMoneyTask.class);
                            List<SubTask> subTaskList = tasks.subtaskList();

                            // validation membership
                            // validation banking
                            for (int i = 0; i < subTaskList.size(); i++) {
                                SubTask subTask = subTaskList.get(i);
                                if (subTask.status().equals(READY)) {
                                    subTaskList.set(i, SubTask.toSuccess(subTask));
                                }
                            }

                            this.taskResultProducer.sendMessage(tasks.taskId(), tasks);

                        } catch (JsonProcessingException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            } finally {
                consumer.close();
            }
        });
        consumerThread.start();
    }
}
