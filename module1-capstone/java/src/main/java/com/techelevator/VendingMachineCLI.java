package com.techelevator;

import com.techelevator.view.Menu;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.Scanner;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT };
	private static final String PURCHASE_MENU_OPTION_FEED_MONEY = "Feed Money";
	private static final String PURCHASE_MENU_OPTION_SELECT_PRODUCT = "Select Product";
	private static final String PURCHASE_MENU_OPTION_FINISH_TRANSACTION = "Finish Transaction";
	private static final String[] PURCHASE_MENU_OPTIONS = {PURCHASE_MENU_OPTION_FEED_MONEY, PURCHASE_MENU_OPTION_SELECT_PRODUCT, PURCHASE_MENU_OPTION_FINISH_TRANSACTION };

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
				displayPurchaseMenuOptions();


			} else if (choice.equals(MAIN_MENU_OPTION_EXIT)){
				System.out.println("***Thank you, come again!***");
				System.exit(0);

			}
		}
	}

	public static String userDepositString = "";
	public static BigDecimal userDepositInput;

	public void displayPurchaseMenuOptions() {

		System.out.println("\nCurrent Money Provided: \\$" + Transaction.getBalance());

		String choice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);

		if(choice.equals(PURCHASE_MENU_OPTION_FEED_MONEY)) {
			Scanner userDepositScanner = new Scanner(System.in);

			System.out.println("\n***This machine only accepts $1.00, $2.00, $5.00, and $10.00 bills***");
			System.out.print("Please enter money: ");

			userDepositString = userDepositScanner.nextLine();
			userDepositInput = new BigDecimal(userDepositString);

			Transaction.setBalance(userDepositInput.add(Transaction.getBalance()));
			Transaction.userDeposit();
			displayPurchaseMenuOptions();
		} else if(choice.equals(PURCHASE_MENU_OPTION_SELECT_PRODUCT)) {

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

		Item.itemQuantity = 5; //restocks to item quantity to 5

		cli.run();

		//TODO Item.scanFile();
		//TODO System.out.println(Item.itemLocation.get("A1"));
	}
}
