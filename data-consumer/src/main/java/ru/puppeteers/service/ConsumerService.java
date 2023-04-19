package ru.puppeteers.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.core.reactive.ReactiveKafkaConsumerTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConsumerService {

    private final ReactiveKafkaConsumerTemplate<String, String> kafkaConsumerTemplate;

    public Flux<String> consume() {
        return kafkaConsumerTemplate.receiveAutoAck()
                .doOnNext(consumerRecord -> log.info("Consumed message {}", consumerRecord.value()))
                .map(ConsumerRecord::value);
    }
}
