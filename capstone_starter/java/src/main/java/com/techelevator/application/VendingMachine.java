package com.techelevator.application;

import com.techelevator.models.*;
import com.techelevator.ui.UserInput;
import com.techelevator.ui.UserOutput;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VendingMachine 
{
    public void run()
    {
        BigDecimal currentMoney = new BigDecimal("0.00");
        List<VendingMachineItem> vendingMachineItemList = new ArrayList<>();
        loadList(vendingMachineItemList);
        while(true)
        {

            UserOutput.displayHomeScreen();
            String choice = UserInput.getHomeScreenOption();

            if(choice.equals("display"))
            {
                // display the vending machine slots
                UserOutput.displayList(vendingMachineItemList);

            }
            else if(choice.equals("purchase"))
            {
                String purchaseChoice = UserInput.getPurchaseScreen(currentMoney);

                if (purchaseChoice.equals("Feed Money")) {

                    Logger logger = new Logger("vendingLog.txt");
                    logger.writeSameLine(">" + UserOutput.getLocalDateTime() +" " + purchaseChoice.toUpperCase() + ": \\$" +  currentMoney);
                    currentMoney = UserInput.addMoneyLoop();
                    logger.write(" \\$" + currentMoney);

                } else if (purchaseChoice.equals("Select Product")) {

                    UserOutput.displayList(vendingMachineItemList);
                    System.out.println();
                    String productCode = UserInput.selectProductOption();
                    currentMoney =  selectProduct(vendingMachineItemList, currentMoney, productCode);




                } else if (purchaseChoice.equals("Finish Transaction")) {

                    Logger logger = new Logger("vendingLog.txt");
                    logger.writeSameLine(">" + UserOutput.getLocalDateTime() +" " + "GIVE CHANGE" + ": \\$" +  currentMoney);
                    currentMoney = UserOutput.getChange(currentMoney);
                    logger.write(" \\$" + currentMoney);
                }

            }
            else if(choice.equals("exit"))
            {
                System.out.println("Good Bye!");
                break;
            }

        }
    }

    public static BigDecimal selectProduct(List<VendingMachineItem> vendingMachineItemList, BigDecimal currentMoney, String productCode){

        boolean isFound = false;
        for (int i = 0; i < vendingMachineItemList.size() && !isFound; i++) {
            if(productCode.toLowerCase().equals(vendingMachineItemList.get(i).getPosition().toLowerCase())){
                if(productCode.toLowerCase().equals(vendingMachineItemList.get(i).getPosition().toLowerCase()) && vendingMachineItemList.get(i).getStockAmount() == 0){
                    System.out.println("Item is sold out!");
                    System.out.println();
                    UserInput.getPurchaseScreen(currentMoney);
                    isFound = true;
                } else if(productCode.toLowerCase().equals(vendingMachineItemList.get(i).getPosition().toLowerCase()) &&
                        currentMoney.compareTo(vendingMachineItemList.get(i).getPrice()) >= 0){
                    vendingMachineItemList.get(i).oneLessStockAmount();
                    Logger logger = new Logger("vendingLog.txt");
                    logger.writeSameLine(">" + UserOutput.getLocalDateTime() +" " +
                            vendingMachineItemList.get(i).getProductName() + " " + vendingMachineItemList.get(i).getPosition() + " \\$" +  currentMoney);
                    currentMoney = currentMoney.subtract(vendingMachineItemList.get(i).getPrice());
                    logger.write(" \\$" + currentMoney);
                    System.out.println(vendingMachineItemList.get(i).getProductName() + " " + vendingMachineItemList.get(i).getPrice() + " " + currentMoney );
                    System.out.println(vendingMachineItemList.get(i).getSound());
                    isFound = true;
                } else if (productCode.toLowerCase().equals(vendingMachineItemList.get(i).getPosition().toLowerCase()) &&
                        currentMoney.compareTo(vendingMachineItemList.get(i).getPrice()) < 0) {
                    System.out.println("Please return to Feed Money to increase your balance, then try again.");
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

    public static BigDecimal addMoney(String cashAmount, BigDecimal currentMoney){

        boolean isDone = false;


            BigDecimal one = new BigDecimal("1");
            BigDecimal two = new BigDecimal("2");
            BigDecimal five = new BigDecimal("5");
            BigDecimal ten = new BigDecimal("10");
            BigDecimal enteredMoney = new BigDecimal(cashAmount);
            if (enteredMoney.equals(one) || enteredMoney.equals(two) ||
                    enteredMoney.equals(five) || enteredMoney.equals(ten)) {
                currentMoney = currentMoney.add(enteredMoney);
                System.out.println("Your current total is: " + currentMoney);




        }
        return currentMoney;
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
}
