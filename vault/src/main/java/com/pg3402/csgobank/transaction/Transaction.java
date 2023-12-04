package com.pg3402.csgobank.transaction;

import lombok.Data;

import java.io.Serializable;


@Data
public class Transaction implements Serializable {
    private Long itemID;
    private Long fromVaultId;
    private Long toVaultId;
    private boolean validated;
    private boolean isCompleted;

}
