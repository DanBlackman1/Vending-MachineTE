package com.techelevator.application;

import com.techelevator.models.VendingMachineItem;
import com.techelevator.ui.UserInput;
import com.techelevator.ui.UserOutput;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VendingMachine 
{
    public void run()
    {
        while(true)
        {
            BigDecimal currentMoney = new BigDecimal("0.00");
            List<VendingMachineItem> vendingMachineItemList = new ArrayList<>();
            UserInput.loadList(vendingMachineItemList);
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
                    currentMoney = UserInput.addMoney(purchaseChoice);

                } else if (purchaseChoice.equals("Select Product")) {



                } else if (purchaseChoice.equals("Finish Transaction")) {

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
