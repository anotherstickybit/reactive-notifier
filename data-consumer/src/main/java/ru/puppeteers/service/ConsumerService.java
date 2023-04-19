package ru.puppeteers.service;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverOptions;
import reactor.kafka.receiver.ReceiverRecord;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class ConsumerService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void consume() {
        ReceiverOptions<String, String> receiverOptions = ReceiverOptions.create();
        receiverOptions.subscription(Collections.singletonList("balance_change"));

        KafkaReceiver<String, String> kafkaReceiver = KafkaReceiver.create(receiverOptions);
        Flux<ReceiverRecord<String, String>> received = kafkaReceiver.receive();
        received.doOnEach(System.out::println).subscribe();
    }
}
