package com.pg3402.csgobank.transactionValidator.configuration;

import jakarta.annotation.PostConstruct;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class ManualConfiguration {

    @Autowired
    RabbitTemplate rabbitTemplate;


   /* @PostConstruct
    void setup(){
        this.rabbitTemplate.setObservationEnabled(true);
    }*/
}
