package ru.puppeteers.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
@RequiredArgsConstructor
public class BalanceChangeProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage() throws InterruptedException {
        int i = 0;
        while (i < 20) {
            i++;
            String message = "message " + i;
            kafkaTemplate.send("balance_change", message);
            log.info("Message {} was sent", message);
            TimeUnit.SECONDS.sleep(5);
        }
    }
}
