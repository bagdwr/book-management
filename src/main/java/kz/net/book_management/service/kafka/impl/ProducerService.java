package kz.net.book_management.service.kafka.impl;

import kz.net.book_management.service.kafka.KafkaProducerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class ProducerService implements KafkaProducerService {

    @Autowired
    private Environment env;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void auditHistory(String value) {

        String topic = env.getProperty("kafka.producer.audit-history.name");

        String key = UUID.randomUUID().toString();

        sendToTopic(topic, key, value);

    }

    private void sendToTopic(String topic, String key, String value) {

        log.debug("Sending event to topic {}, key: {}, value: {}", topic, key, value);

        kafkaTemplate.send(topic, key, value);

    }
}
