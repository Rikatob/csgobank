package com.pgr3402.csgobank.account.configuration;

import jakarta.annotation.PostConstruct;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class ManualConfiguration {

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public ManualConfiguration(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @PostConstruct
    void setup() {
        this.rabbitTemplate.setObservationEnabled(true);
    }

}