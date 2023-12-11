package com.pg3402.csgobank.configuration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;
import org.springframework.messaging.handler.annotation.support.MessageHandlerMethodFactory;

@Configuration
public class AMQPConfiguration {



    @Value("${amqp.exchange.transactions}")
    private String transactionExchangeName;

    @Value("${amqp.exchange.account}")
    private String accountExchangeName;

    @Value("${amqp.queue.account}")
    private String accountQueueName;

    @Value("${amqp.routing.key.account.created}")
    private String accountCreatedKey;

    @Value("${amqp.routing.key.account.deleted}")
    private String accountDeletedKey;


    @Value("${amqp.queue.transaction}")
    private String transactionQueueName;

    @Value("${amqp.routing.key.transaction.accepted}")
    private String routingKey;


    @Bean
    public TopicExchange transactionsExchange() {
        return ExchangeBuilder.topicExchange(transactionExchangeName).durable(true).build();
    }
    @Bean
    public Queue transactionQueue() {
        return QueueBuilder.durable(transactionQueueName).build();
    }


    @Bean
    public Binding transactionAcceptedBinding() {
        return BindingBuilder
                .bind(transactionQueue())
                .to(transactionsExchange())
                .with(routingKey);
    }

    @Bean
    public Jackson2JsonMessageConverter producerJackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
    //Account

    @Bean
    public TopicExchange accountExchange() {
        return ExchangeBuilder.topicExchange(accountExchangeName).durable(true).build();

    }

    @Bean
    public Queue accountQueue() {
        return QueueBuilder.durable(accountQueueName).build();
    }


    @Bean
    public Binding accountCreatedBinding() {
        return BindingBuilder
                .bind(accountQueue())
                .to(accountExchange())
                .with(accountCreatedKey);
    }

    @Bean
    public Binding accountDeletedBinding() {
        return BindingBuilder
                .bind(accountQueue())
                .to(accountExchange())
                .with(accountDeletedKey);
    }

    @Bean
    public MessageHandlerMethodFactory messageHandlerMethodFactory() {
        DefaultMessageHandlerMethodFactory factory = new DefaultMessageHandlerMethodFactory();
        final MappingJackson2MessageConverter jsonConverter = new MappingJackson2MessageConverter();
        jsonConverter.getObjectMapper().registerModule(new ParameterNamesModule(JsonCreator.Mode.PROPERTIES));
        factory.setMessageConverter(jsonConverter);
        return factory;
    }
    @Bean
    public RabbitListenerConfigurer rabbitListenerConfigurer(final MessageHandlerMethodFactory messageHandlerMethodFactory) {
        return (c) -> c.setMessageHandlerMethodFactory(messageHandlerMethodFactory);
    }
}