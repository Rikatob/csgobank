package com.pg3402.csgobank.transaction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionEvent implements Serializable {

    Long itemId;
    Long fromVaultId;
    Long toVaultId;
    private int price;
    private TransactionType type;
    boolean completed;
}
