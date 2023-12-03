package com.pg3402.csgobank.transactionValidator.transaction;

import org.springframework.stereotype.Service;

@Service
public class TransactionService {


    public boolean validateTransaction(Transaction transaction) {


        Long buyer = transaction.getBuyerID();
        Long seller = transaction.getSellerID();
        Long item = transaction.getItemID();

        return accountExists(buyer);
    }

    private boolean accountExists(Long buyer) {
    return true;
    }
}
