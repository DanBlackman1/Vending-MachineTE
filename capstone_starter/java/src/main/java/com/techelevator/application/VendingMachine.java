package com.techelevator.application;

import com.techelevator.models.VendingMachineItem;
import com.techelevator.ui.UserInput;
import com.techelevator.ui.UserOutput;

import java.util.ArrayList;
import java.util.List;

public class VendingMachine 
{
    public void run()
    {
        while(true)
        {
            List<VendingMachineItem> vendingMachineItemList = new ArrayList<>();
            UserInput.loadList(vendingMachineItemList);
            UserOutput.displayHomeScreen();
            String choice = UserInput.getHomeScreenOption();

            if(choice.equals("display"))
            {
                // display the vending machine slots
                UserOutput.displayList(vendingMachineItemList);


                System.out.println("display");
            }
            else if(choice.equals("purchase"))
            {
                UserInput.getPurchaseScreen();
            }
            else if(choice.equals("exit"))
            {
                System.out.println("Good Bye!");
                break;
            }
        }
    }
    
}
