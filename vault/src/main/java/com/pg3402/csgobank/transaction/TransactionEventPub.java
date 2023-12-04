package com.pg3402.csgobank.transaction;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TransactionEventPub {

    private final AmqpTemplate amqpTemplate;

    @Value("${amqp.exchange.transactions}")
    private String transactionTopicExchange;
    public TransactionEventPub(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    public void publishTransaction(final Transaction transaction) {
        TransactionEvent event = buildEvent(transaction);

        String routingKey = "transaction." + (event.isCompleted() ? "complete" : "failed");
        amqpTemplate.convertAndSend(transactionTopicExchange, routingKey, event);
    }

    private TransactionEvent buildEvent(final Transaction transaction) {
        return new TransactionEvent(transaction.getItemID(), transaction.getFromVaultId(), transaction.getToVaultId(), transaction.isCompleted());
    }
}
