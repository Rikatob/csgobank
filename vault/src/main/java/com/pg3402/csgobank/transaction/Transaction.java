package com.pg3402.csgobank.transaction;

import lombok.Data;
@Data
public class Transaction {
    private int itemID;
    private String sellerID;
    private String buyerID;
    private boolean validated;
    private boolean isCompleted;


}
