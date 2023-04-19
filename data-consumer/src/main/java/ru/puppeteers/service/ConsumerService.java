package ru.puppeteers.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.core.reactive.ReactiveKafkaConsumerTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.kafka.receiver.KafkaReceiver;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConsumerService {

    private final ReactiveKafkaConsumerTemplate<String, String> kafkaConsumerTemplate;

    public Flux<String> consume() {
        return kafkaConsumerTemplate.receiveAutoAck()
                .doOnNext(consumerRecord -> log.info("Consumed message {}", consumerRecord.value()))
                .map(ConsumerRecord::value);
//        return kafkaReceiver.receive()
//                .map(ConsumerRecord::value)
//                .doOnEach(message -> log.info("Consumed message {}", message))
//                .onErrorResume(e -> Mono.just("Exception"));
    }
}
