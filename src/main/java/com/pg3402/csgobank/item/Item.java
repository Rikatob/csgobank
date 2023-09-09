package com.pg3402.csgobank.item;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

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
