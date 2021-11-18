package com.techelevator.models;

import java.math.BigDecimal;

public abstract class VendingMachineItem {
    private String productName;
    private BigDecimal price;
    private String sound;
    private String position;
    private int stockAmount;

    public VendingMachineItem(String position, String productName, BigDecimal price){
        this.position = position;
        this.productName = productName;
        this.price = price;
        stockAmount = 5;

    }


    public abstract String getSound();
}
