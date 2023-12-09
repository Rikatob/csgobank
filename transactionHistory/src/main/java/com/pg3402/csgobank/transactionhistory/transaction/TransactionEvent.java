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

    private Long itemId;
    private Long fromVaultId;
    private Long toVaultId;
    private int price;
    private TransactionType type;
    boolean completed;
}
