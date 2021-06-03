package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.Scanner;

public class Item {

    //scan inventory sheet

    static File inventoryFile = new File("vendingmachine.csv");

    String slotLocation;
    String productName;
    BigDecimal price;
    String productType;

    static String [] productArray = new String[4];

    public static void scanFile() {

        try (Scanner inventoryScanner = new Scanner(inventoryFile)) {
            while (inventoryScanner.hasNextLine()) {

                String lineToSearch = inventoryScanner.nextLine();

                productArray = lineToSearch.split("\\|");

            }
        } catch (FileNotFoundException fnfE) {
            System.out.println("File not found.");
        }
    }


    //split inventory into subclasses
}
