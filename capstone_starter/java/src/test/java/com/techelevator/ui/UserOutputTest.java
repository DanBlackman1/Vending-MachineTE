package com.techelevator.ui;

import com.techelevator.models.Chips;
import com.techelevator.models.VendingMachineItem;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class UserOutputTest {



    @Test
    public void displayList_if_stock_out() {
        List<VendingMachineItem> testList = new ArrayList<>();
        Chips testChip = new Chips("A1", "Testiest Chips!", new BigDecimal("1.00"));
        testChip.setStockAmount(0);
        testList.add(testChip);

    }

    @Test
    public void getChange_sets_Balance_to_Zero() {
        UserOutput test = new UserOutput();
        BigDecimal currentMoney = new BigDecimal("5.00");

        BigDecimal expected = new BigDecimal("0.00");
        BigDecimal actual = test.getChange(currentMoney);

        Assert.assertEquals(expected, actual);

    }

    @Test
    public void getLocalDateTime() {
    }
}