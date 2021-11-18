package com.techelevator.ui;

import com.techelevator.application.VendingMachine;
import com.techelevator.models.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
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
        System.out.print("Current Money Provided: " + currentMoney);

        System.out.print("Please select an option:" );
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
                return "";
        }

    }

    public static void loadList(List<VendingMachineItem> vendingMachineItemList){
        File inputFile = new File("vendingmachine.csv");
        try(Scanner inputScanner = new Scanner(inputFile)) {
            while(inputScanner.hasNextLine()){
                String line = inputScanner.nextLine();
                String[] lineArray = line.split("\\|");
                if(lineArray[3].contains("Chip")){
                    Chips chips = new Chips(lineArray[0], lineArray[1], new BigDecimal (lineArray[2]));
                    vendingMachineItemList.add(chips);
                } else if(lineArray[3].equals("Candy")){
                    Candy candy = new Candy(lineArray[0], lineArray[1], new BigDecimal (lineArray[2]));
                    vendingMachineItemList.add(candy);
                } else if(lineArray[3].equals("Drink")){
                    Beverage beverage = new Beverage(lineArray[0], lineArray[1], new BigDecimal(lineArray[2]));
                    vendingMachineItemList.add(beverage);
                } else if(lineArray[3].equals("Gum")){
                    Gum gum = new Gum(lineArray[0], lineArray[1], new BigDecimal(lineArray[2]));
                    vendingMachineItemList.add(gum);
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("Error reading file!");
            System.exit(1);
        }
    }

    public static BigDecimal addMoney(String purchaseChoice) {
        boolean isDone = false;
        BigDecimal currentMoney = new BigDecimal("0.00");
        while (!isDone) {
            System.out.println("Please place cash into machine, $1, $2, $5, " +
                    "and $10 increments ONLY: ");
            Scanner purchaseInput = new Scanner(System.in);
            BigDecimal one = new BigDecimal("1");
            BigDecimal two = new BigDecimal("2");
            BigDecimal five = new BigDecimal("5");
            BigDecimal ten = new BigDecimal("10");
            BigDecimal enteredMoney = new BigDecimal(purchaseInput.nextLine());
            if (enteredMoney.equals(one) || enteredMoney.equals(two) ||
                    enteredMoney.equals(five) || enteredMoney.equals(ten)) {
                currentMoney = currentMoney.add(enteredMoney);
                System.out.println("Your current total is: " + currentMoney);
                System.out.println("Are you done entering money? (Y/N)");
                String exitOrNot = purchaseInput.nextLine();
                if (exitOrNot.toLowerCase().equals("y")) {
                    isDone = true;
                }

            }


        }
        return currentMoney;
    }

    public static BigDecimal selectProduct(List<VendingMachineItem> vendingMachineItemList, BigDecimal currentMoney){

        System.out.print("Please enter the product code of what you want: ");
        Scanner userInput = new Scanner(System.in);
        String productCode = userInput.nextLine();
        boolean isFound = false;
        for (int i = 0; i < vendingMachineItemList.size() && !isFound; i++) {
            if(productCode.equals(vendingMachineItemList.get(i).getPosition())){
                if(productCode.equals(vendingMachineItemList.get(i).getPosition()) && vendingMachineItemList.get(i).getStockAmount() == 0){
                    System.out.print("Item is sold out!");
                    UserInput.getPurchaseScreen(currentMoney);
                    isFound = true;
                } else if(productCode.equals(vendingMachineItemList.get(i).getPosition())){
                    vendingMachineItemList.get(i).oneLessStockAmount();
                    currentMoney = currentMoney.subtract(vendingMachineItemList.get(i).getPrice());
                    System.out.println(vendingMachineItemList.get(i).getProductName() + " " + vendingMachineItemList.get(i).getPrice() + " " + currentMoney );
                    System.out.println(vendingMachineItemList.get(i).getSound());
                    isFound = true;
                }
            }
        }

        if(isFound == false){
            System.out.println("Product code does not exist!");
            UserInput.getPurchaseScreen(currentMoney);
        }


        return currentMoney;
    }

}
