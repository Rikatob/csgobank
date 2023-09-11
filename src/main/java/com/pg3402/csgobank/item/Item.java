package com.pg3402.csgobank.item;

import com.pg3402.csgobank.vault.Vault;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Item implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "item_id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "vault_id",nullable = false)
    private Vault vault;

    @Column(name = "type", columnDefinition = "varchar(20)")
    private String type;
    @Column(name = "name", columnDefinition = "varchar(20)")
    private String name;
    @Column(name = "float_value", columnDefinition = "int")
    private int floatValue;
    @Column(name = "price", columnDefinition = "int")
    private int price;

    @Enumerated
    @Column(name = "wear_category")
    private WearCategory wearCategory;

}
