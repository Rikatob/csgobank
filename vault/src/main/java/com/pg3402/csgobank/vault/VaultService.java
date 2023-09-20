package com.pg3402.csgobank.vault;


import com.pg3402.csgobank.transaction.Transaction;
import com.pg3402.csgobank.transaction.TransactionEvent;
import com.pg3402.csgobank.transaction.TransactionEventPub;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@RequiredArgsConstructor
@Service
public class VaultService {

    private final TransactionEventPub transactionEventPub;


    // MONO single or no object.
    // FLUX list or no object.
    public Boolean validateTransaction() {
        // TODO: 9/18/2023 Should we take the url in as a parameter?
        // TODO: 9/18/2023 Need to check what happens if it fails to get validation, should be "standalone".

        String transactionUrl = "http://localhost:8081/transaction";

        WebClient.Builder builder = WebClient.builder();


        return builder.build()
                .get()
                .uri(transactionUrl)
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();
    }

    public void transferItem() {
        Transaction transaction = new Transaction();
        transaction.setItemID(100);
        transaction.setSellerID("Amund");
        transaction.setBuyerID("Fredrik");
        transaction.setValidated(validateTransaction());
        if (transaction.isValidated()) {
            transaction.setCompleted(true);
        } else{
            transaction.setCompleted(false);
        }

        transactionEventPub.transactionComplete(transaction);
    }
}
