package com.techelevator.models;

import java.math.BigDecimal;

public abstract class VendingMachineItem {
    private String productName;
    private BigDecimal price;
    private String sound;
    private String position;
    private int stockAmount;
    private int totalSold;

    public VendingMachineItem(String position, String productName, BigDecimal price){
        this.position = position;
        this.productName = productName;
        this.price = price;
        stockAmount = 5;
        totalSold = 0;

    }

    public int getTotalSold() {
        return totalSold;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setTotalSold(int totalSold) {
        this.totalSold = totalSold;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getStockAmount() {
        return stockAmount;
    }

    public void setStockAmount(int stockAmount) {
        this.stockAmount = stockAmount;
    }

    public void oneLessStockAmount(){
        this.stockAmount = this.stockAmount - 1;
    }

    public abstract String getSound();
}
