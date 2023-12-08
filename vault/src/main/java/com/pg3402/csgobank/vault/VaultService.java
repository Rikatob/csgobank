package com.pg3402.csgobank.vault;

import com.pg3402.csgobank.item.ItemService;
import com.pg3402.csgobank.vaultAccount.VaultAccount;
import com.pg3402.csgobank.vaultAccount.VaultAccountRepository;
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

    private final VaultAccountRepository vaultAccountRepository;
    private final ItemService itemService;

    @Autowired
    public VaultService(TransactionEventPub transactionEventPub, ItemRepository itemRepository, TransactionValidationClient transactionValidationClient, VaultRepository vaultRepository, VaultAccountRepository vaultAccountRepository, ItemService itemService) {
        this.transactionEventPub = transactionEventPub;
        this.itemRepository = itemRepository;
        this.transactionValidationClient = transactionValidationClient;
        this.vaultRepository = vaultRepository;
        this.vaultAccountRepository = vaultAccountRepository;
        this.itemService = itemService;
    }


    /**
     * Starts a connection and sends a GET request to the validator.
     *
     * @return Answer from the GET request (true or false), returns false also if GET request fails.
     */
    public Transaction validateTransaction(Transaction transaction) {

        log.info("Validating transaction");
        ResponseEntity<Transaction> validation = transactionValidationClient.validate(transaction);
        return validation.getBody();
    }

    /**
     * Takes in a transaction.
     * Validate the transaction.
     * Publish the event.
     */
    public Transaction transferItem(Transaction transaction) {

        transaction = validateTransaction(transaction);

        Optional<Item> optionalItem = itemRepository.findById(transaction.getItemID());
        Optional<Vault> optionalVault = vaultRepository.findById(transaction.getToVaultId());

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
        Optional<VaultAccount> optionalAccount = vaultAccountRepository.findById(accountId);

        if (optionalAccount.isEmpty()) {
            return Optional.empty();
        }
        Vault vault = new Vault();
        vault.setVaultAccount(optionalAccount.get());

        return Optional.of(vaultRepository.save(vault));
    }

    public Optional<VaultAccount> getOwnerOfItem(long itemId) {
        Optional<Item> optionalItem = itemRepository.findById(itemId);
        return optionalItem.map(item -> item.getVault().getVaultAccount());
    }

    public Optional<Item> depositItem(long vaultId, long itemId) {
        Optional<Item> optionalItem = itemService.getItem(itemId);
        Optional<Vault> optionalVault = vaultRepository.findById(vaultId);

        if (optionalItem.isEmpty() || optionalVault.isEmpty()) {
            return Optional.empty();
        }
        Item item = optionalItem.get();
        Vault vault = optionalVault.get();

        vault.updateTotalValue("deposit", item.getPrice());
        vaultRepository.save(vault);

        item.setVault(optionalVault.get());
        itemRepository.save(item);
        return optionalItem;
    }


    /**
     * Deletes item from item(vault) db.
     *
     * @param itemId
     * @return Optional with item requested from Item-Service.
     */
    public Optional<Item> withdrawItem(long vaultId, long itemId) {
        Optional<Vault> optionalVault = vaultRepository.findById(vaultId);
        Optional<Item> optionalItem = itemRepository.findById(itemId);

        if (optionalItem.isEmpty() || optionalVault.isEmpty()) {
            log.info("Withdraw of itemID [" + itemId + "]" + " failed");
            return Optional.empty();
        }

        Item item = optionalItem.get();
        optionalVault.get().updateTotalValue("withdraw", item.getPrice());

        itemRepository.delete(item);
        log.info("Withdraw of itemID [" + itemId + "]" + " succeeded");
        return optionalItem;

    }
}
