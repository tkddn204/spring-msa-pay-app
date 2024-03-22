package net.rightpair.taskconsumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

@Slf4j
@Component
public class TaskResultProducer {
    private final KafkaProducer<String, String> producer;

    private final String topic;

    public TaskResultProducer(@Value("${kafka.clusters.bootstrapservers}") String bootstrapServers,
                           @Value("${logging.topic}") String topic) {

        // Producer Initialization ';'
        Properties props = new Properties();

        // kafka:29092
        props.put("bootstrap.servers", bootstrapServers);

        // "key:value"
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        this.producer = new KafkaProducer<>(props);
        this.topic = topic;
    }

    public void sendMessage(String key, Object task) {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonStringToProduce;
        try {
            jsonStringToProduce = objectMapper.writeValueAsString(task);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        ProducerRecord<String, String> record = new ProducerRecord<>(topic, key, jsonStringToProduce);
        producer.send(record, (metadata, exception) -> {
            String dateStr = new SimpleDateFormat("yyyyMMdd-HH:mm:ss").format(new Date(metadata.timestamp()));
            if (exception != null) {
                log.error("[kafka] ERROR : ", exception);
                return;
            }
            log.info("[kafka] (%s) %s".formatted(dateStr, record.toString()));
        });
    }
}
