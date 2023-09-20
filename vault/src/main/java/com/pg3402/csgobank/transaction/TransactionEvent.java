package com.pg3402.csgobank.transaction;

import lombok.Value;

import java.io.Serializable;

@Value
public class TransactionEvent implements Serializable {

    int skinID;
    String sellerID;
    String buyerID;
    boolean isComplete;
}
