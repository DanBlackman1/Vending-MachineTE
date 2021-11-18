package com.techelevator.models;

import java.math.BigDecimal;

public class Candy extends VendingMachineItem{
    private String sound;
    public Candy(String position, String productName, BigDecimal price) {
        super(position, productName, price);
        sound = "Munch Munch, Yum!";
    }

    @Override
    public String getSound() {
        return sound;
    }
}
