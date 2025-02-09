package kz.net.book_management.service.kafka.impl;

import kz.net.book_management.service.kafka.KafkaProducerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProducerService implements KafkaProducerService {
    @Override
    public void sendEvent() {

    }
}
