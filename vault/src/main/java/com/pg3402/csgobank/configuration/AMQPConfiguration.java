package com.pg3402.csgobank.configuration;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AMQPConfiguration {

    @Value("${amqp.queue.transactionHistory}")
    private String queueName;
    @Value("${amqp.exchange.transactions}")
    private String exchangeName;
    @Value("${amqp.routing.key}")
    private String routingKey;

    @Bean
    public TopicExchange transactionHistoryExchange() {
        return ExchangeBuilder.topicExchange(exchangeName).durable(true).build();
    }


}