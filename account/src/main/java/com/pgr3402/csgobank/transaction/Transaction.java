package com.pgr3402.csgobank.transaction;


import lombok.Data;

@Data
public class Transaction {


    private long transactionId;

    private long itemId;

    private long fromVaultId;

    private long toVaultId;

    private long fromAccountId;

    private long toAccountId;

    private int price;

    private TransactionState state;


    private TransactionType type;
}
