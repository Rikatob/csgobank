package com.pg3402.csgobank.transactionValidator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class TransactionValidatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(TransactionValidatorApplication.class, args);
    }

}
