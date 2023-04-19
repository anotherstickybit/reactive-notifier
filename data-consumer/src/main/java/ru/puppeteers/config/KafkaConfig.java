package ru.puppeteers.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.reactive.ReactiveKafkaConsumerTemplate;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverOptions;
import reactor.kafka.receiver.internals.ConsumerFactory;
import reactor.kafka.receiver.internals.DefaultKafkaReceiver;

import java.util.Collections;
import java.util.List;

@Configuration
public class KafkaConfig {

    @Value("${spring.kafka.consumer.bootstrap-servers}")
    private String bootstrapServer;

    @Bean
    public ReceiverOptions<String, String> kafkaReceiverOptions(KafkaProperties kafkaProperties) {
        ReceiverOptions<String, String> basicReceiverOptions =
                ReceiverOptions.create(kafkaProperties.buildConsumerProperties());
        return basicReceiverOptions.subscription(Collections.singletonList(bootstrapServer));
    }

    @Bean
    public ReactiveKafkaConsumerTemplate<String, String> reactiveKafkaConsumerTemplate(
            ReceiverOptions<String, String> kafkaReceiverOptions) {
        return new ReactiveKafkaConsumerTemplate<>(kafkaReceiverOptions);
    }

//    @Bean
//    public KafkaReceiver<String, String> kafkaReceiver(KafkaProperties kafkaProperties) {
//        kafkaProperties.setBootstrapServers(List.of(bootstrapServer));
//        kafkaProperties.getConsumer().setGroupId("group1");
//        ReceiverOptions<String, String> basicReceiverOptions =
//                ReceiverOptions.create(kafkaProperties.buildConsumerProperties());
//        basicReceiverOptions.subscription(Collections.singletonList("balance_change"));
//
//        return new DefaultKafkaReceiver<>(ConsumerFactory.INSTANCE, basicReceiverOptions);
//    }
}
