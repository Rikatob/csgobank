package com.pg3402.csgobank.transaction;

import lombok.Data;

import java.io.Serializable;


@Data
public class Transaction implements Serializable {
    private long itemId;
    private long fromVaultId;
    private long toVaultId;
    private long fromAccountId;
    private long toAccountId;
    private int price;
    private TransactionType type;
    private TransactionState state;

}
