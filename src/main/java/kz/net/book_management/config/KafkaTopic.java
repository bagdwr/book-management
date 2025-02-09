package kz.net.book_management.config;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaTopic {
    @Autowired
    private Environment env;

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, env.getProperty("kafka.bootstrap-servers"));
        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic auditedEvents() {
        return TopicBuilder
                .name(env.getProperty("kafka.producer.audited-events.name"))
                .partitions(Integer.parseInt(env.getProperty("kafka.producer.audited-events.partitions")))
                .replicas(Integer.parseInt(env.getProperty("kafka.producer.audited-events.replication-factor")))
                .build();
    }

}