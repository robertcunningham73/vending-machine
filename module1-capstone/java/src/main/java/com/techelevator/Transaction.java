package com.techelevator;

import java.math.BigDecimal;
import java.util.Scanner;

public class Transaction {

    //instance variables

    private int nickel = 5;
    private int dime = 10;
    private int quarter = 25;
    private int balance = 0;

    public int userMoney = 0;


    //getters


    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    //methods

    //TODO write to log
    public void userDeposit(){
        Scanner userDepositScanner = new Scanner(System.in);
        String userDepositString = userDepositScanner.nextLine();
        userMoney = Integer.parseInt(userDepositString);
    }

    public int feedMoney(){
        return balance += userMoney;

    }
    //TODO write to log
    public void dispenseChange() {


        System.out.println("Change dispensed: ");
    }
    //TODO write to log
    public void purchaseItem(){

    }

    public void writeToLog(){

    }

}



