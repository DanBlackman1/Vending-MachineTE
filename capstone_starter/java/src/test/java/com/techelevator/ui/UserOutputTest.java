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
    public void getChange_sets_Balance_to_Zero() {
        UserOutput test = new UserOutput();
        BigDecimal currentMoney = new BigDecimal("5.00");

        BigDecimal expected = new BigDecimal("0.00");
        BigDecimal actual = test.getChange(currentMoney);

        Assert.assertEquals(expected, actual);

    }

    @Test
    public void getChange_sets_Balance_to_odd_ammount_if_not_divisible_by_5() {
        UserOutput test = new UserOutput();
        BigDecimal currentMoney = new BigDecimal("5.37");

        BigDecimal expected = new BigDecimal("0.02");
        BigDecimal actual = test.getChange(currentMoney);

        Assert.assertEquals(expected, actual);

    }

}