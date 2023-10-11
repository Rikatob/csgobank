package com.pg3402.csgobank.vault;

import com.pg3402.csgobank.transaction.Transaction;
import com.pg3402.csgobank.transaction.TransactionEventPub;
import com.pg3402.csgobank.transaction.TransactionValidationClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@Service
public class VaultService {

    public VaultService(TransactionEventPub transactionEventPub) {
        this.transactionEventPub = transactionEventPub;
    }

    private final TransactionEventPub transactionEventPub;


    @Autowired
    TransactionValidationClient transactionValidationClient;

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
}
