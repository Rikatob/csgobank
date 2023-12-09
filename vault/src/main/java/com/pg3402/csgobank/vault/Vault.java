package com.pg3402.csgobank.vault;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pg3402.csgobank.vaultAccount.VaultAccount;
import com.pg3402.csgobank.item.Item;
import jakarta.persistence.*;
import jakarta.validation.constraints.Null;
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


    @OneToMany(mappedBy = "vault")
    @JsonIgnore
    private List<Item> items;


    @ManyToOne(optional = false)
    @JoinColumn(name = "account_id")
    private VaultAccount vaultAccount;



    public long getTotalValue() {
        if(items == null){
            return 0;
        }
        long totalValue = 0;
        for (Item item : items) {
            totalValue += item.getPrice();
        }
        return totalValue;
    }
}
