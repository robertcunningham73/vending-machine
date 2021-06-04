package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Transaction {

    //instance variables

    private BigDecimal balance = new BigDecimal("0.00");
    private BigDecimal quarter = new BigDecimal("0.25");
    private BigDecimal dime = new BigDecimal("0.10");
    private BigDecimal nickel = new BigDecimal(".05");
    private BigDecimal zero = new BigDecimal("0.00");

    //public BigDecimal userMoney;

    private String transaction = "";
    private BigDecimal previousBalance;


    //getters


    public BigDecimal getBalance() {
        return balance;
    }

    /*public void setBalance(int balance) {
        this.balance = balance;
    }*/

    //methods

    //TODO write to log
    public void userDeposit(){
        Scanner userDepositScanner = new Scanner(System.in);
        String userDepositString = userDepositScanner.nextLine();
        //userMoney = Integer.parseInt(userDepositString);
        BigDecimal userMoney = new BigDecimal(userDepositString);
        //balance += userMoney;
        balance = balance.add(userMoney);

    }

    //TODO write to log
    public String dispenseChange(BigDecimal userMoney) {

        previousBalance = balance;

        int quarterCount = 0;
        int dimeCount = 0;
        int nickelCount = 0;

        while (balance.subtract(userMoney).compareTo(zero) < 0) {
            if ((userMoney.divide(quarter, 2, RoundingMode.CEILING).compareTo(zero) > 0 ) && balance.add(quarter).compareTo(userMoney) <= 0) {
                balance = balance.add(quarter);
                quarterCount += 1;
            }
            else if (userMoney.divide(dime, 2, RoundingMode.CEILING).compareTo(zero)  > 0 && balance.add(dime).compareTo(userMoney) <= 0) {
                balance = balance.add(dime);
                dimeCount += 1;
            }
            else if (userMoney.divide(nickel, 2, RoundingMode.CEILING).compareTo(zero) > 0 && balance.add(nickel).compareTo(userMoney) <= 0) {
                balance = balance.add(nickel);
                nickelCount += 1;
            }

      //before switching to BigDecimal
      /*  while (balance < userMoney) {
            if (userMoney / 25 > 0 && balance + 25 <= userMoney) {
                balance += 25;
                quarterCount += 1;
            }
            else if (userMoney / 10 > 0 && balance + 10 <= userMoney) {
                balance += 10;
                dimeCount += 1;
            }
            else if (userMoney / 5 > 0 && balance + 5 <= userMoney) {
                balance += 5;
                nickelCount += 1;
            }*/
        }

        String changeToDispense = "";

        if(quarterCount > 1) {
            changeToDispense += quarterCount + " quarters";
        } else if(quarterCount == 1) {
            changeToDispense += "1 quarter";
        }

        if(dimeCount > 1) {
            if(!changeToDispense.isEmpty()) { changeToDispense += ", "; }
            changeToDispense += dimeCount + " dimes";
        } else if(dimeCount == 1) {
            if(!changeToDispense.isEmpty()) { changeToDispense += ", "; }
            changeToDispense += "1 dime";
        }

        if(nickelCount > 1) {
            if(!changeToDispense.isEmpty()) { changeToDispense += ", "; }
            changeToDispense += nickelCount + " nickels";
        } else if(nickelCount == 1) {
            if(!changeToDispense.isEmpty()) { changeToDispense += ", "; }
            changeToDispense += "1 nickel";
        }

        System.out.println("Change dispensed: " + changeToDispense);

        writeToLog("GIVE CHANGE", balance, balance);

        return changeToDispense;
    }
    //TODO write to log
    public void purchaseItem(){

    }

    public void writeToLog(String transaction, BigDecimal previousBalance, BigDecimal balance){

        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a");
        LocalDateTime dateTime = LocalDateTime.now();

        try(PrintWriter audit = new PrintWriter(new FileOutputStream(new File("Log.txt"), true))) {

            audit.println(dateTimeFormat.format(dateTime) + " " + transaction + " \\" + previousBalance + " \\" + balance);

        } catch(FileNotFoundException fnfE) {
            System.out.println("File not found.");
        }

    }

}



