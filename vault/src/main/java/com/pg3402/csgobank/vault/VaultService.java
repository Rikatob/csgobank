package com.pg3402.csgobank.vault;

import com.pg3402.csgobank.item.ItemService;
import com.pg3402.csgobank.transaction.TransactionState;
import com.pg3402.csgobank.vaultAccount.AccountClient;
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
    private final AccountClient accountClient;

    @Autowired
    public VaultService(TransactionEventPub transactionEventPub, ItemRepository itemRepository, TransactionValidationClient transactionValidationClient, VaultRepository vaultRepository, VaultAccountRepository vaultAccountRepository, ItemService itemService, AccountClient accountClient) {
        this.transactionEventPub = transactionEventPub;
        this.itemRepository = itemRepository;
        this.transactionValidationClient = transactionValidationClient;
        this.vaultRepository = vaultRepository;
        this.vaultAccountRepository = vaultAccountRepository;
        this.itemService = itemService;
        this.accountClient = accountClient;
    }




    /**
     * Starts a connection and sends a GET request to the validator.
     *
     * @return Answer from the GET request (true or false), returns false also if GET request fails.
     */
    public Transaction validateTransaction(Transaction transaction) {

        log.info("Sending transaction to validation");
        ResponseEntity<Transaction> validation = transactionValidationClient.validate(transaction);
        return validation.getBody();
    }

    /**
     * Takes in a transaction.
     * Validate the transaction.
     * Publish the event.
     */
    public Transaction transferItem(Transaction transaction) {

        setAccounts(transaction);

        transaction = validateTransaction(transaction);

        Optional<Item> optionalItem = itemRepository.findById(transaction.getItemId());
        Optional<Vault> optionalVault = vaultRepository.findById(transaction.getToVaultId());

        if (transaction.getState().equals(TransactionState.VALIDATED) && optionalItem.isPresent() && optionalVault.isPresent()) {

            optionalItem.get().setVault(optionalVault.get());
            itemRepository.save(optionalItem.get());
            transaction.setState(TransactionState.COMPLETE);

        } else {
            transaction.setState(TransactionState.FAILED);
        }

        transactionEventPub.publishTransaction(transaction);

        return transaction;
    }

    public void handleTransaction(Transaction transaction) {

        Optional<Item> optionalItem = itemRepository.findById(transaction.getItemId());
        Optional<Vault> optionalVault = vaultRepository.findById(transaction.getToVaultId());

        if (transaction.getState().equals(TransactionState.ACCEPTED) && optionalItem.isPresent() && optionalVault.isPresent()) {


            ResponseEntity<Transaction> response = accountClient.transferCredits(transaction);
            log.info("response" + response.toString());

            if (response.getBody() == null) {
                transaction.setState(TransactionState.FAILED);
            } else {
                transaction = response.getBody();
                transaction.setState(TransactionState.COMPLETE);
                optionalItem.get().setVault(optionalVault.get());
                itemRepository.save(optionalItem.get());
                log.info("transaction completed successfully" + transaction);
            }
        } else {
            transaction.setState(TransactionState.FAILED);
        }

        transactionEventPub.publishTransaction(transaction);
    }


    public Transaction createTradeOffer(Transaction transaction) {
        setAccounts(transaction);
        log.info(transaction.toString());
        transaction.setState(TransactionState.CREATED);
        transactionEventPub.publishTradeOffer(transaction);

        return transaction;
    }
    public Iterable<Vault> getAllVaults() {
        return vaultRepository.findAll();
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


    /**
     * Gets the item from item-service and save it in vault.
     * Returns an empty optional IF
     * -> Vault is not found.
     * -> Item is not found.
     * -> Item is already existing in vault.
     *
     * @param vaultId
     * @param itemId
     * @return Optional with item requested from Item-Service.
     */
    public Optional<Item> depositItem(long vaultId, long itemId) {
        log.info("Starting to deposit item[" + itemId + "] into vault [" + vaultId + "]");
        Optional<Item> optionalItem = itemService.getItem(itemId);
        Optional<Item> optionalVaultItem = itemRepository.findById(itemId);
        Optional<Vault> optionalVault = vaultRepository.findById(vaultId);

        if (optionalItem.isEmpty() || optionalVault.isEmpty() || optionalVaultItem.isPresent()) {
            log.info("Deposit of itemID [" + itemId + "]" + " failed");
            return Optional.empty();
        }
        Item item = optionalItem.get();

        item.setVault(optionalVault.get());
        itemRepository.save(item);
        log.info("Deposit of itemID [" + itemId + "]" + " succeeded");
        return optionalItem;
    }

// TODO Withdraw does not need to get the item from itemService....

    /**
     * Gets the item from item(vault) and delete it from the vault.
     * Returns an empty optional IF
     * -> Vault is not found.
     * -> Item is not found.
     * -> Vault do not contain item.
     *
     * @param itemId
     * @param vaultId
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

        if (!item.getVault().equals(optionalVault.get())) {
            log.info("Withdraw of itemID [" + itemId + "]" + " failed");
            return Optional.empty();
        }

        itemRepository.delete(item);
        log.info("Withdraw of itemID [" + itemId + "]" + " succeeded");
        return optionalItem;

    }


    private void setAccounts(Transaction transaction) {
        if (transaction.getFromAccountId() == 0) {
            Optional<Vault> optionalVault = vaultRepository.findById(transaction.getFromVaultId());
            optionalVault.ifPresent(vault -> transaction.setFromAccountId(vault.getVaultAccount().getId()));
        }

        if (transaction.getToAccountId() == 0) {
            Optional<Vault> optionalVault = vaultRepository.findById(transaction.getToVaultId());
            optionalVault.ifPresent(vault -> transaction.setToAccountId(vault.getVaultAccount().getId()));
        }
    }


    public void deleteVault(long vaultId) {
        vaultRepository.deleteById(vaultId);

    }
}
