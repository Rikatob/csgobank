package com.pg3402.csgobank.vault;

import com.pg3402.csgobank.transaction.Transaction;
import com.pg3402.csgobank.transaction.TransactionEventPub;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@Service
public class VaultService {

    public VaultService(TransactionEventPub transactionEventPub) {
        this.transactionEventPub = transactionEventPub;
    }

    private final TransactionEventPub transactionEventPub;
    @Value("${transaction.validator.url}")
    private String transactionValidatorURL;


    /**
     * Starts a connection and sends a GET request to the validator.
     *
     * @return Answer from the GET request (true or false), returns false also if GET request fails.
     */
    public Boolean validateTransaction() {

        log.warn("Start to validate transaction. (validateTransaction)");
        WebClient.Builder builder = WebClient.builder();
        try {

            return builder.build()
                    .get()
                    .uri(transactionValidatorURL)
                    .retrieve()
                    .bodyToMono(Boolean.class)
                    .block();
        } catch (Exception e) {
            log.warn("Could not validate transaction");
            e.printStackTrace();
            return false;
        }

    }

    /**
     * Create transaction.
     * Runs the validation.
     * Publish the event.
     */
    public void transferItem() {
        log.warn("Starting to transfer Item. (transferItem)");
        Transaction transaction = new Transaction();
        transaction.setItemID(100);
        transaction.setSellerID("Amund");
        transaction.setBuyerID("Fredrik");
        transaction.setValidated(validateTransaction());
        if (transaction.isValidated()) {
            transaction.setCompleted(true);
            log.warn("Transaction is validated. (transferItem)");
        } else {
            transaction.setCompleted(false);
            log.warn("Transaction is not validated. (transferItem)");
        }

        transactionEventPub.transactionComplete(transaction);

        log.warn("Transaction is done. (transferItem)");
    }
}
