package kz.net.book_management.service.kafka;

public interface KafkaConsumerService {

    void readAuditedEvents(String message);

}
