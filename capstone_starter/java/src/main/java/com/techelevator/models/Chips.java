package com.techelevator.models;

import java.math.BigDecimal;

public class Chips extends VendingMachineItem{

    private String sound;

    public Chips(String position, String productName, BigDecimal price) {
        super(position, productName, price);
        sound = "Crunch Crunch, Yum!";
    }

    @Override
    public String getSound() {
        return sound;
    }
}
