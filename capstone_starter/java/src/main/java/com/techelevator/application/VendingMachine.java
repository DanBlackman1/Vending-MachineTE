package com.techelevator.application;

import com.techelevator.ui.UserInput;
import com.techelevator.ui.UserOutput;

public class VendingMachine 
{
    public void run()
    {
        while(true)
        {
            UserOutput.displayHomeScreen();
            String choice = UserInput.getHomeScreenOption();

            if(choice.equals("display"))
            {
                // display the vending machine slots
                System.out.println("display");
            }
            else if(choice.equals("purchase"))
            {
                System.out.println("purchase");
            }
            else if(choice.equals("exit"))
            {
                System.out.println("Good Bye!");
                break;
            }
        }
    }
    
}
