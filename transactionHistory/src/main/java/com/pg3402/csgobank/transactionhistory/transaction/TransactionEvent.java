package com.pg3402.csgobank.transactionhistory.transaction;

import lombok.Value;

import java.io.Serializable;

@Value
public class TransactionEvent implements Serializable {

    int itemID;
    String sellerID;
    String buyerID;
    boolean isCompleted;
}
