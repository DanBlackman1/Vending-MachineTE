package com.techelevator.application;

import com.techelevator.models.Logger;
import com.techelevator.models.VendingMachineItem;
import com.techelevator.ui.UserInput;
import com.techelevator.ui.UserOutput;

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
        UserInput.loadList(vendingMachineItemList);
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
                    currentMoney = UserInput.addMoney();
                    logger.write(" \\$" + currentMoney);

                } else if (purchaseChoice.equals("Select Product")) {

                    UserOutput.displayList(vendingMachineItemList);
                    System.out.println();

                    currentMoney =  UserInput.selectProduct(vendingMachineItemList, currentMoney);




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
    
}
