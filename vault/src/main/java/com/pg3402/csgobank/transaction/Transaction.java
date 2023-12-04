package com.pg3402.csgobank.transaction;

import lombok.Data;


@Data
public class Transaction {
    private Long itemID;
    private Long fromVaultId;
    private Long toVaultId;
    private boolean validated;
    private boolean isCompleted;

}
