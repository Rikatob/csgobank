package com.pg3402.csgobank.transactionhistory.history;

import com.pg3402.csgobank.transactionhistory.configuration.AMQPConfiguration;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Runner implements CommandLineRunner {


    @Value("${amqp.exchange.transactions}")
    String ost;

    private final RabbitTemplate rabbitTemplate;
    private final TransactionHistoryEventHandler receiver;

    public Runner(TransactionHistoryEventHandler receiver, RabbitTemplate rabbitTemplate) {
        this.receiver = receiver;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Sending message...");
        rabbitTemplate.convertAndSend(ost, "transaction.complete", "Hello from RabbitMQ!");
    }

}
