package com.pg3402.csgobank.item;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private long id;

    @Column(name = "type")
    private String type;

    @Column(name = "name")
    private String name;

    @Column(name = "float_value")
    private String floatValue;

    @Column(name = "price")
    private int price;

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
