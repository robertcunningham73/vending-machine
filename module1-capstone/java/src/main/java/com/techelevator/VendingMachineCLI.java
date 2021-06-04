package com.techelevator;

import com.techelevator.view.Menu;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT };

	private Menu menu;
	private Object Scanner;

	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}

	public void run() throws FileNotFoundException {
		while (true) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				displayVendingMachineOptions();
				System.out.println();
			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				//
			} else if (choice.equals(MAIN_MENU_OPTION_EXIT)){
				System.out.println("***Thank you, come again!***");
				System.exit(0);

			}
		}
	}
	//TODO figure out remaining inventory
	public void displayVendingMachineOptions() throws FileNotFoundException {
		File inventoryFile = new File("vendingmachine.csv");
		try (java.util.Scanner inventoryScanner = new Scanner(inventoryFile)) {
			while (inventoryScanner.hasNextLine()) {
				String lineToSearch = inventoryScanner.nextLine();
				System.out.println(lineToSearch + "|" + Item.itemQuantity);
			}
		}
	}

	public static void main(String[] args) throws FileNotFoundException  {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();

		//TODO Item.scanFile();
		//TODO System.out.println(Item.itemLocation.get("A1"));
	}
}
