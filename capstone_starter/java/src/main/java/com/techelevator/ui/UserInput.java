package com.techelevator.ui;

import com.techelevator.application.VendingMachine;
import com.techelevator.models.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

/**
 * Responsibilities: This class should handle receiving ALL input from the User
 * 
 * Dependencies: None
 */
public class UserInput
{
    private static Scanner scanner = new Scanner(System.in);

    public static String getHomeScreenOption()
    {
        System.out.println("What would you like to do?");
        System.out.println();

        System.out.println("1) Display Vending Machine Items");
        System.out.println("2) Purchase");
        System.out.println("3) Exit");

        System.out.println();
        System.out.print("Please select an option: ");

        String selectedOption = scanner.nextLine();
        String option = selectedOption.trim().toLowerCase();

        switch (option) {
            case "1":
                return "display";
            case "2":
                return "purchase";
            case "3":
                return "exit";
            default:
                System.out.println("Incorrect input, please try again.");
                return "";
        }

    }

    public static String getPurchaseScreen(BigDecimal currentMoney)
    {
        System.out.println("What would you like to do?");
        System.out.println();

        System.out.println("1) Feed Money");
        System.out.println("2) Select Product");
        System.out.println("3) Finish Transaction");



        System.out.println();
        System.out.println("Current Money Provided: " + currentMoney);

        System.out.print("Please select an option: " );
        String selectedOption = scanner.nextLine();
        String option = selectedOption.trim().toLowerCase();

        switch (option) {
            case "1":
                return "Feed Money";
            case "2":
                return "Select Product";
            case "3":
                return "Finish Transaction";
            default:
                System.out.println("Incorrect input, please try again");
                return "";
        }

    }



    public static String addMoneyOption() {
        System.out.println("Please place cash into machine, $1, $2, $5, " +
                "and $10 increments ONLY: ");
        String purchaseInput = scanner.nextLine();

        return purchaseInput;
    }

    public static String selectProductOption(){
        System.out.print("Please enter the product code of what you want: ");
        String productCode = scanner.nextLine();

        return productCode;
    }

    public static BigDecimal addMoneyLoop(){
        boolean isDone = false;
        String exitOrNot = "";
        BigDecimal currentMoney = new BigDecimal("0.00");
        while(!isDone) {
            String cashAmount = UserInput.addMoneyOption();
            currentMoney = VendingMachine.addMoney(cashAmount, currentMoney);
            System.out.println("Are you done entering money? (Y/N)");
            exitOrNot = scanner.nextLine();
            if(exitOrNot.toLowerCase().equals("y")){
                isDone = true;
            }
        }
        return currentMoney;
    }



}
