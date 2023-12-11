package com.pg3402.csgobank.transactionhistory.transaction;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Transaction {

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
