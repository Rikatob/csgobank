package com.pg3402.csgobank.vault;


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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "vault_id")
    private long id;
    @Column(name = "owner_name", columnDefinition = "varchar(50)")
    private String ownerName;
    /*@OneToMany
    @Column(name = "items")
    private List<Item> items;*/
    @Column(name = "total_value")
    private int totalValue;


}
