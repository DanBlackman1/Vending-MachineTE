package com.techelevator.application;

import com.techelevator.models.Chips;
import com.techelevator.models.Gum;
import com.techelevator.models.VendingMachineItem;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class VendingMachineTest {

    @Test
    public void selectProduct_if_enough_money_and_productCode_correct() {
        List<VendingMachineItem> testList = new ArrayList<>();
        Chips testChip = new Chips("A1", "Testiest Chips!", new BigDecimal("1.35"));
        testList.add(testChip);
        BigDecimal moneyTest = new BigDecimal("10.00");
        String productCode = "A1";

        BigDecimal expected = new BigDecimal("8.65");
        BigDecimal actual = VendingMachine.selectProduct(testList, moneyTest, productCode);

        Assert.assertEquals(expected, actual);

    }

    @Test
    public void selectProduct_if_not_enough_money_and_productCode_correct() {
        List<VendingMachineItem> testList = new ArrayList<>();
        Chips testChip = new Chips("A1", "Testiest Chips!", new BigDecimal("11.35"));
        testList.add(testChip);
        BigDecimal moneyTest = new BigDecimal("10.00");
        String productCode = "A1";

        BigDecimal expected = new BigDecimal("10.00");
        BigDecimal actual = VendingMachine.selectProduct(testList, moneyTest, productCode);

        Assert.assertEquals(expected, actual);

    }



    @Test
    public void loadList_loads_first_item_of_csv_file() {


        List<VendingMachineItem> testList = new ArrayList<>();

        Chips testChip = new Chips("A1","Potato Crisps", new BigDecimal("3.05"));
        Gum testGum = new Gum("D4", "Triplemint", new BigDecimal("0.75"));

        VendingMachine.loadList(testList);

        boolean expected = true;
        boolean actual = testList.get(0).getProductName().equals("Potato Crisps");

        Assert.assertEquals(expected, actual);



    }

    @Test
    public void addMoney_adds_1_if_given_string_1() {
        String testCashInput = "1";
        BigDecimal testMoney = new BigDecimal("0.00");

        BigDecimal expected = new BigDecimal("1.00");
        BigDecimal actual = VendingMachine.addMoney(testCashInput, testMoney);

        Assert.assertEquals(expected, actual);


    }

    @Test
    public void addMoney_does_not_add_if_given_string_23() {
        String testCashInput = "23";
        BigDecimal testMoney = new BigDecimal("0.00");

        BigDecimal expected = new BigDecimal("0.00");
        BigDecimal actual = VendingMachine.addMoney(testCashInput, testMoney);

        Assert.assertEquals(expected, actual);


    }


}