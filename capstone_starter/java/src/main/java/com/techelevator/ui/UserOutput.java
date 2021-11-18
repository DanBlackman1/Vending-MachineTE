package com.techelevator.ui;

import com.techelevator.models.VendingMachineItem;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


/**
 * Responsibilities: This class should handle formatting and displaying ALL
 * messages to the user
 * 
 * Dependencies: None
 */
public class UserOutput
{

    public static void displayMessage(String message)
    {
        System.out.println();
        System.out.println(message);
        System.out.println();
    }

    public static void displayHomeScreen()
    {
        System.out.println();
        System.out.println("***************************************************");
        System.out.println("                      Home");
        System.out.println("***************************************************");
        System.out.println();
    }

    public static void displayList(List<VendingMachineItem> vendingMachineItemList) {
        for (VendingMachineItem item : vendingMachineItemList) {
            if (item.getStockAmount() > 0) {
                System.out.printf("%-15s %-13s %11s %1.2f %15s %15s\n", item.getPosition(),
                        item.getProductName(), "$", item.getPrice(), item.getStockAmount(), "Available");

            } else {
                System.out.printf("%-15s %-13s %11s %1.2f %15s\n", item.getPosition(),
                        item.getProductName(), "$", item.getPrice(), "SOLD OUT");
            }
        }
    }

    //public static void

}
