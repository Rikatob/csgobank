package com.pg3402.csgobank.transactionValidator.transaction;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
public class TransactionService {


    private final VaultClient vaultClient;
    private final AccountClient accountClient;
    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionService(VaultClient vaultClient, AccountClient accountClient, TransactionRepository transactionRepository) {
        this.vaultClient = vaultClient;
        this.accountClient = accountClient;
        this.transactionRepository = transactionRepository;
    }

    public Transaction validateTransaction(Transaction transaction) {
        log.info("Validating transaction");
        if (checkTransaction(transaction)) {
            transaction.setState(TransactionState.VALIDATED);
        }
        return transaction;
    }

    public Transaction validateOffer(Transaction transaction) {
        if (checkTransaction(transaction)) {
            transaction.setState(TransactionState.PENDING);
        }
        return transaction;
    }

    private boolean checkTransaction(Transaction transaction) {

        long itemId = transaction.getItemId();
        long fromVaultId = transaction.getFromVaultId();
        long toVaultId = transaction.getToVaultId();

        ResponseEntity<Boolean> fromVaultExistsResponse = vaultClient.checkIfVaultExists(fromVaultId);
        ResponseEntity<Boolean> toVaultExistsResponse = vaultClient.checkIfVaultExists(toVaultId);
        ResponseEntity<Long> itemOwnerResponse = vaultClient.getVaultId(itemId);

        if (transaction.getType() == TransactionType.TRADE) {
            ResponseEntity<Integer> accountCreditResponse = accountClient.getAccountCredit(transaction.getFromAccountId());
            Integer accountCredits = accountCreditResponse.getBody();
            if (accountCredits == null) {
                log.info("Something went wrong credits not found");
                return false;
            }
            if (accountCredits < transaction.getPrice()) {
                log.info("Not enough credits");
                return false;
            }

        }
        // Vault fromVault dont exists.
        if (Objects.equals(fromVaultExistsResponse.getBody(), false)) {

            log.info("Could not validate, fromVault dont exist");
            return false;
        }
        // Vault toVault dont exists.
        if (Objects.equals(toVaultExistsResponse.getBody(), false)) {

            log.info("Could not validate, toVault dont exist");
            return false;
        }
        // Request to get vaultId from itemId fails.
        if (itemOwnerResponse.getStatusCode() != HttpStatus.OK) {

            log.info("Could not validate, request failed");
            return false;
        }
        // Item is not present in the vault.
        if (!Objects.equals(itemOwnerResponse.getBody(), fromVaultId)) {

            log.info("Could not validate, fromVault dont have item");
            return false;
        }


        log.info("Transaction successfully validated");
        return true;
    }

    private boolean accountExists(Long buyer) {
        return true;
    }

    public Optional<List<Transaction>> getIncomingTransaction(long id) {

        return transactionRepository.findAllByToAccountId(id);
    }

    public Optional<List<Transaction>> getOutgoingTransaction(long id) {

        return transactionRepository.findAllByFromAccountId(id);
    }
}
