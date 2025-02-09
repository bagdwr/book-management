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
            id = "${kafka.consumer.audit-history.id}",
            topics = "${kafka.consumer.audit-history.name}",
            groupId = "${kafka.consumer.audit-history.group}",
            autoStartup = "${kafka.consumer.audit-history.auto-startup}",
            concurrency = "${kafka.consumer.audit-history.consumers}",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void readAuditHistory(String message) {

        log.info("XuFLWWe :: audit history: {}", message);

    }
}
