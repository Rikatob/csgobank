package com.pg3402.csgobank.item;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class Item {
    private long id;
    private String type;
    private String name;
    private int floatValue;
    private int price;
    private WearCategory wearCategory;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFloatValue() {
        return floatValue;
    }

    public void setFloatValue(int floatValue) {
        this.floatValue = floatValue;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public WearCategory getWearCategory() {
        return wearCategory;
    }

    public void setWearCategory(WearCategory wearCategory) {
        this.wearCategory = wearCategory;
    }
}
