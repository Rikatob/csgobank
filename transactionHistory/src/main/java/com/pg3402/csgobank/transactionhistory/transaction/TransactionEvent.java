package com.pg3402.csgobank.transactionhistory.transaction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionEvent implements Serializable {

    private long itemId;
    private long fromVaultId;
    private long toVaultId;
    private long fromAccountId;
    private long toAccountId;
    private int price;
    private TransactionType type;
    private TransactionState state;
}
