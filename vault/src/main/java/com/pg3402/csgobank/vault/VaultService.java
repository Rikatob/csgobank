package com.pg3402.csgobank.vault;

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

    @Autowired
    public VaultService(TransactionEventPub transactionEventPub, ItemRepository itemRepository, TransactionValidationClient transactionValidationClient, VaultRepository vaultRepository) {
        this.transactionEventPub = transactionEventPub;
        this.itemRepository = itemRepository;
        this.transactionValidationClient = transactionValidationClient;
        this.vaultRepository = vaultRepository;
    }


    /**
     * Starts a connection and sends a GET request to the validator.
     *
     * @return Answer from the GET request (true or false), returns false also if GET request fails.
     */
    public Boolean validateTransaction() {


//        WebClient.Builder builder = WebClient.builder();
//        try {
//
//            return builder.build()
//                    .get()
//                    .uri(transactionValidatorURL)
//                    .retrieve()
//                    .bodyToMono(Boolean.class)
//                    .block();
//        } catch (Exception e) {
//            log.warn("Could not validate transaction");
//            e.printStackTrace();
//            return false;
//        }
        log.info("Validating!!!");
        try {
            ResponseEntity<Boolean> temp = transactionValidationClient.validate();
            return temp.getBody();
        } catch (Exception e) {
            log.error("Something went wrong");
            log.error(e.getMessage());
            log.error(String.valueOf(e.getCause()));
            return false;
        }


    }

    /**
     * Create transaction.
     * Runs the validation.
     * Publish the event.
     */
    public void transferItem() {
        Transaction transaction = new Transaction();
        transaction.setItemID(100);
        transaction.setSellerID("Amund");
        transaction.setBuyerID("Fredrik");
        transaction.setValidated(validateTransaction());
        if (transaction.isValidated()) {
            transaction.setCompleted(true);
        } else {
            transaction.setCompleted(false);
        }

        transactionEventPub.transactionComplete(transaction);
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
}
