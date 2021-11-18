package com.techelevator.models;

import java.math.BigDecimal;

public class Beverage extends VendingMachineItem{

    private String sound;

    public Beverage(String position, String productName, BigDecimal price) {
        super(position, productName, price);
        sound = "Glug Glug, Yum!";
    }

    @Override
    public String getSound() {
        return sound;
    }


}
