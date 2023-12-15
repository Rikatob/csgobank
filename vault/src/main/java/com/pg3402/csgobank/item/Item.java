package com.pg3402.csgobank.item;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pg3402.csgobank.vault.Vault;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Item implements Serializable {
    @Id
    @Column()
    private long id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "vault_id",nullable = false)
    private Vault vault;

    @Column(name = "type")
    private String type;

    @Column(name = "name")
    private String name;

    @Column(name = "float_value")
    private String floatValue;

    @Column(name = "price")
    private int price;

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", floatValue='" + floatValue + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return getId() == item.getId() && getPrice() == item.getPrice() && Objects.equals(getType(), item.getType()) && Objects.equals(getName(), item.getName()) && Objects.equals(getFloatValue(), item.getFloatValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getType(), getName(), getFloatValue(), getPrice());
    }

    /*
            -- FN 0.00 -> 0.07
            -- MW 0.07 -> 0.15
            -- FT 0.15 -> 0.37
            -- WW 0.37 -> 0.44
            -- BS 0.44 -> 1.00
           */
    public WearCategory getWearCategory() {
        float floatV = Float.parseFloat(floatValue);

        if(floatV < 0.07){
            return WearCategory.FACTORY_NEW;
        }
        if (floatV < 0.15) {
            return WearCategory.MINIMAL_WEAR;
        }
        if (floatV < 0.37) {
            return WearCategory.FIELD_TESTED;
        }
        if (floatV < 0.44) {
            return WearCategory.WELL_WORN;
        }

        return WearCategory.BATTLE_SCARRED;
    }
}




