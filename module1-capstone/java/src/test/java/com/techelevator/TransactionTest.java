package com.techelevator;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class TransactionTest {

    @Test
    public void dispenseChange_validValues() {
        Transaction change = new Transaction();
        String expected = "";

        String result = change.dispenseChange(BigDecimal.valueOf(5.15));

        Assert.assertEquals(expected, result);
    }
}
