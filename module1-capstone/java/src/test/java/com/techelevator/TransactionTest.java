package com.techelevator;

import org.junit.Assert;
import org.junit.Test;

public class TransactionTest {

    @Test
    public void dispenseChange_validValues() {
        Transaction change = new Transaction();
        String expected = "";

        String result = change.dispenseChange(140);

        Assert.assertEquals(expected, result);
    }
}
