package com.pg3402.csgobank;

import com.pg3402.csgobank.transaction.TransactionEventPub;
import com.pg3402.csgobank.vault.VaultService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

public class VaultServiceTests {

    @Test
    void validateTransactionTest() {

        VaultService vaultService = new VaultService(new TransactionEventPub(new RabbitTemplate()));

        Boolean validation = vaultService.validateTransaction();

        Assertions.assertTrue(validation);
    }


}
