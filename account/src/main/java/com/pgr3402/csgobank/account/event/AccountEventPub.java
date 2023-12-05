package com.pgr3402.csgobank.account.event;

import com.pgr3402.csgobank.account.Account;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AccountEventPub {

    private final AmqpTemplate amqpTemplate;

    @Value("${amqp.exchange.accounts}")
    private String transactionTopicExchange;
    public AccountEventPub(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    public void publishAccountEvent(final Account account, AccountEventEnum eventType) {
        AccountEvent event = buildEvent(account);

        String routingKey = "account." + eventType.toString().toLowerCase();

        amqpTemplate.convertAndSend(transactionTopicExchange, routingKey, event);
    }

    private AccountEvent buildEvent(final Account account) {
        return new AccountEvent(account.getId());
    }
}
