package ru.puppeteers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.puppeteers.service.BalanceChangeProducer;

@SpringBootApplication
public class DataProducerApplication implements CommandLineRunner {

    @Autowired
    private BalanceChangeProducer producer;

    public static void main(String[] args) {
        SpringApplication.run(DataProducerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
      producer.sendMessage();
    }
}