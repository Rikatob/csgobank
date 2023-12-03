package com.pg3402.csgobank.vault;

import com.pg3402.csgobank.account.Account;
import com.pg3402.csgobank.account.AccountRepository;
import com.pg3402.csgobank.item.Item;
import com.pg3402.csgobank.item.ItemRepository;
import com.pg3402.csgobank.transaction.Transaction;
import com.pg3402.csgobank.transaction.TransactionEventPub;
import com.pg3402.csgobank.transaction.TransactionValidationClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class VaultService {

    private final TransactionEventPub transactionEventPub;

    private final TransactionValidationClient transactionValidationClient;

    private final ItemRepository itemRepository;

    private final VaultRepository vaultRepository;

    private final AccountRepository accountRepository;

    @Autowired
    public VaultService(TransactionEventPub transactionEventPub, ItemRepository itemRepository, TransactionValidationClient transactionValidationClient, VaultRepository vaultRepository, AccountRepository accountRepository) {
        this.transactionEventPub = transactionEventPub;
        this.itemRepository = itemRepository;
        this.transactionValidationClient = transactionValidationClient;
        this.vaultRepository = vaultRepository;
        this.accountRepository = accountRepository;
    }


    /**
     * Starts a connection and sends a GET request to the validator.
     *
     * @return Answer from the GET request (true or false), returns false also if GET request fails.
     */
    public Boolean validateTransaction() {


        log.info("Validating transaction");
        try {
            ResponseEntity<Boolean> validation = transactionValidationClient.validate();
            return validation.getBody();
        } catch (Exception e) {
            log.error("Error when validating transaction");
            log.error(e.getMessage());
            log.error(String.valueOf(e.getCause()));
            return false;
        }


    }

    /**
     * Takes in a transaction.
     * Validate the transaction.
     * Publish the event.
     */
    public Transaction transferItem(Transaction transaction) {

        transaction.setValidated(validateTransaction());

        Optional<Item> optionalItem = itemRepository.findById(transaction.getItemID());
        Optional<Vault> optionalVault = vaultRepository.findById(transaction.getBuyerID());

        if (transaction.isValidated() && optionalItem.isPresent() && optionalVault.isPresent()) {

            optionalItem.get().setVault(optionalVault.get());
            itemRepository.save(optionalItem.get());
            transaction.setCompleted(true);

        } else {
            transaction.setCompleted(false);
        }

        transactionEventPub.publishTransaction(transaction);

        return transaction;
    }

    public Iterable<Item> getAllItems(Long vaultId) {
        return itemRepository.findAllByVaultId(vaultId);
    }

    public boolean exists(long vaultId) {
        return vaultRepository.existsById(vaultId);
    }

    public Optional<Vault> findById(long vaultId) {
        return vaultRepository.findById(vaultId);
    }


    public Optional<Vault> createVault(Long accountId) {
        Optional<Account> optionalAccount = accountRepository.findById(accountId);

        if (optionalAccount.isEmpty()) {
            return Optional.empty();
        }
        Vault vault = new Vault();
        vault.setAccount(optionalAccount.get());

        return Optional.of(vaultRepository.save(vault));
    }

    public Optional<Account> getOwnerOfItem(long itemId) {
        Optional<Item> optionalItem = itemRepository.findById(itemId);
        return optionalItem.map(item -> item.getVault().getAccount());
    }
}
