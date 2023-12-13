package com.pg3402.csgobank.transactionValidator.transaction;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
public class Transaction implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private long transactionId;

    @Column
    private long itemId;
    @Column
    private long fromVaultId;
    @Column
    private long toVaultId;
    @Column
    private long fromAccountId;
    @Column
    private long toAccountId;
    @Column
    private int price;

    @Column
    @Enumerated(EnumType.STRING)
    private TransactionState state;

    @Column
    @Enumerated(EnumType.STRING)
    private TransactionType type;


}
