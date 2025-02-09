package kz.net.book_management.service.kafka;

public interface KafkaConsumerService {

    void readAuditHistory(String message);

}
