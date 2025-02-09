package kz.net.book_management.service.kafka.impl;

import kz.net.book_management.service.kafka.KafkaConsumerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ConsumerService implements KafkaConsumerService {


    @Override
    @KafkaListener(
            id = "${kafka.consumer.audited-events.id}",
            topics = "${kafka.consumer.audited-events.name}",
            groupId = "${kafka.consumer.audited-events.group}",
            autoStartup = "${kafka.consumer.audited-events.auto-startup}",
            concurrency = "${kafka.consumer.audited-events.consumers}",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void readAuditedEvents(String message) {
        log.info("XuFLWWe :: message: {}", message);
    }
}
