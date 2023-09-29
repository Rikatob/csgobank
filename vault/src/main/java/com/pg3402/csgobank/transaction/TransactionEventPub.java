package com.pg3402.csgobank.transaction;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
@Slf4j
@Service
public class TransactionEventPub {

    private final AmqpTemplate amqpTemplate;

    @Value("${amqp.exchange.transactions}")
    private String transactionTopicExchange;
    public TransactionEventPub(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    public void transactionComplete(final Transaction transaction) {
        log.warn("starting to publish event to rabbitMQ. (transactionComplete)");

        TransactionEvent event = buildEvent(transaction);

        String routingKey = "transaction." + (event.isCompleted() ? "complete" : "failed");
        amqpTemplate.convertAndSend(transactionTopicExchange, routingKey, event);
    }

    private TransactionEvent buildEvent(final Transaction transaction) {
        return new TransactionEvent(transaction.getItemID(), transaction.getSellerID(), transaction.getBuyerID(), transaction.isCompleted());
    }
}
