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
    private Long transactionId;

    @Column(name = "item_id")
    private Long itemID;

    @Column(name = "from_vault_id")
    private Long fromVaultId;

    @Column(name = "to_vault_id")
    private Long toVaultId;

    @Column(name = "completed")
    private boolean completed;

}
