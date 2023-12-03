package com.pg3402.csgobank.transaction;

import lombok.Data;


@Data
public class Transaction {
    private Long itemID;
    private Long sellerID;
    private Long buyerID;
    private boolean validated;
    private boolean isCompleted;

}
