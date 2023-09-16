package com.pg3402.csgobank.transactionhistory.history;

import lombok.Value;

import java.io.Serializable;

@Value
public class TransactionHistoryEvent implements Serializable {

    int skinID;
    String sellerID;
    String buyerID;


}
