package com.pg3402.csgobank.transactionValidator.transaction;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;
@Slf4j
@Service
public class TransactionService {


    private final VaultClient vaultClient;

    @Autowired
    public TransactionService(VaultClient vaultClient) {
        this.vaultClient = vaultClient;
    }

    public Transaction validateTransaction(Transaction transaction) {
        log.info("Validating transaction");

        Long itemId = transaction.getItemID();
        Long fromVaultId = transaction.getFromVaultId();
        Long toVaultId = transaction.getToVaultId();

        ResponseEntity<Boolean> fromVaultExistsResponse = vaultClient.checkIfVaultExists(fromVaultId);
        ResponseEntity<Boolean> toVaultExistsResponse = vaultClient.checkIfVaultExists(toVaultId);
        ResponseEntity<Long> itemOwnerResponse = vaultClient.getVaultId(itemId);

        // Vault fromVault dont exists.
        if (Objects.equals(fromVaultExistsResponse.getBody(), false)) {
            transaction.setValidated(false);
            log.info("Could not validate, fromVault dont exist");
            return transaction;
        }
        // Vault toVault dont exists.
        if (Objects.equals(toVaultExistsResponse.getBody(), false)) {
            transaction.setValidated(false);
            log.info("Could not validate, toVault dont exist");
            return transaction;
        }
        // Request to get vaultId from itemId fails.
        if (itemOwnerResponse.getStatusCode() != HttpStatus.OK) {
            transaction.setValidated(false);
            log.info("Could not validate, request failed");
            return transaction;
        }
        // Item is not present in the vault.
        if (!Objects.equals(itemOwnerResponse.getBody(), fromVaultId)) {
            transaction.setValidated(false);
            log.info("Could not validate, fromVault dont have item");
            return transaction;
        }

        transaction.setValidated(true);
        log.info("Transaction successfully validated");
        return transaction;
    }

    private boolean accountExists(Long buyer) {
        return true;
    }
}
