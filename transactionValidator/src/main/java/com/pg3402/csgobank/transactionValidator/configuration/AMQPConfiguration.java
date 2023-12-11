package com.pg3402.csgobank.transactionValidator.configuration;

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

    @Value("${amqp.queue.transaction}")
    private String queueName;
    @Value("${amqp.exchange.transactions}")
    private String exchangeName;
    @Value("${amqp.routing.key}")
    private String routingKey;

    @Bean
    public TopicExchange transactionsExchange() {
        return ExchangeBuilder.topicExchange(exchangeName).durable(true).build();
    }

    @Bean
    public Queue transactionQueue() {
        return QueueBuilder.durable(queueName).build();
    }

    @Bean
    public Binding transactionCreatedBinding() {
        return BindingBuilder
                .bind(transactionQueue())
                .to(transactionsExchange())
                .with(routingKey);
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

    @Bean
    public Jackson2JsonMessageConverter producerJackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
