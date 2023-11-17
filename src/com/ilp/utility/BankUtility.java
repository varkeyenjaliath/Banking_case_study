package com.ilp.utility;

import java.util.ArrayList;
import java.util.Scanner;

import com.ilp.entity.Customer;
import com.ilp.entity.Product;
import com.ilp.entity.Service;
import com.ilp.service.AccountConfiguration;
import com.ilp.service.ManageAccountService;
import com.ilp.service.ProductConfiguration;

public class BankUtility {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Service> serviceList = null;
		ArrayList<Product> productList = null;
		Customer customer = null;

		Scanner scanner = new Scanner(System.in);
		char continueChoice;
		do {
			System.out.println("************Welcome To Bank************");
			System.out.println("1.Create Service");
			System.out.println("2.Create Product");
			System.out.println("3.Create Account(s)");
			System.out.println("4.Display Account(s)");
			System.out.println("5.Manage accounts");
			System.out.println("6.Transaction Bill");
			System.out.println("7.Exit");
			System.out.println("Enter your choice :");
			int menuChoice = scanner.nextInt();
			scanner.nextLine();
			switch (menuChoice) {
			case 1:
				serviceList = ProductConfiguration.createService();
				break;
			case 2:
				if (serviceList == null) {
					System.out.println("NO SERVICES ADDED!!!!!");
					break;
				}
				productList = ProductConfiguration.createProduct(serviceList);
				break;
			case 3:
				if (productList == null) {
					System.out.println("NO PRODUCTS ADDED!!!!!");
					break;
				}
				customer = AccountConfiguration.createCustomerAccount(customer, productList);
				break;
			case 4:
				if (customer == null) {
					System.out.println("NO CUSTOMERS ADDED!!!!!");
					break;
				}
				AccountConfiguration.displayCustomer(customer);
				break;
			case 5:
				customer = ManageAccountService.manageAccounts(customer);
				break;
			case 6:
				ManageAccountService.transactionBill(customer);
				break;
			case 7:
				System.out.println("!!!!!GOODBYE!!!!!");
				System.exit(0);
			}

			System.out.println("Do you want to go to main menu?(y/n): ");
			continueChoice = scanner.next().charAt(0);
			scanner.nextLine();
		} while (continueChoice == 'y');

	}

}
