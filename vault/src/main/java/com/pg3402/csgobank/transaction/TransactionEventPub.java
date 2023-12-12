package com.pg3402.csgobank.transaction;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TransactionEventPub {

    private final AmqpTemplate amqpTemplate;

    @Value("${amqp.exchange.transactions}")
    private String transactionTopicExchange;
    public TransactionEventPub(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    public void publishTransaction(final Transaction transaction) {
        TransactionEvent event = buildEvent(transaction);

        String routingKey = "transaction." + (event.getState().toString().toLowerCase());
        log.info(routingKey);
        amqpTemplate.convertAndSend(transactionTopicExchange, routingKey, event);
    }

    public void publishTradeOffer(final Transaction transaction) {
        TransactionEvent event = buildEvent(transaction);

        String routingKey = "transaction.created";
        amqpTemplate.convertAndSend(transactionTopicExchange, routingKey, event);
    }

    private TransactionEvent buildEvent(final Transaction transaction) {
        TransactionEvent event = new TransactionEvent();

        event.setType(transaction.getType());

        event.setItemId(transaction.getItemId());
        event.setPrice(transaction.getPrice());

        event.setFromVaultId(transaction.getFromVaultId());
        event.setToVaultId(transaction.getToVaultId());

        event.setState(transaction.getState());

        event.setFromAccountId(transaction.getFromAccountId());
        event.setToAccountId(transaction.getToAccountId());

        return event;
    }
}
