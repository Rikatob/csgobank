package com.pg3402.csgobank.vault;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pg3402.csgobank.vaultAccount.VaultAccount;
import com.pg3402.csgobank.item.Item;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Vault implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vault_id")
    private long id;


    @OneToMany
    @JsonIgnore
    private List<Item> items;

    @Column(name = "total_value")
    private int totalValue;

    @ManyToOne(optional = false)
    @JoinColumn(name = "account_id")
    private VaultAccount vaultAccount;

    public void updateTotalValue(String operation, int itemValue) {
        if (operation.equalsIgnoreCase("deposit")) {
            totalValue += itemValue;
        } else if (operation.equalsIgnoreCase("withdraw")) {
            totalValue -= itemValue;
        }
    }
}
