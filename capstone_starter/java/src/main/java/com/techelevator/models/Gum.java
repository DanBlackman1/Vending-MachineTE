package com.techelevator.models;

import java.math.BigDecimal;

public class Gum extends VendingMachineItem{

    private String sound;
    public Gum(String position, String productName, BigDecimal price) {
        super(position, productName, price);
        sound = "Chew Chew, Yum!";
    }

    @Override
    public String getSound() {
        return sound;
    }
}
