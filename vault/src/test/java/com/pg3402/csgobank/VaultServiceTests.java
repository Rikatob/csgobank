package com.pg3402.csgobank;

import com.pg3402.csgobank.vault.VaultService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class VaultServiceTests {

    @Test
    void validateTransactionTest() {

        VaultService vaultService = new VaultService();

        Boolean validation = vaultService.validateTransaction();

        Assertions.assertTrue(validation);
    }
}
