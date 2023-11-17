package com.ilp.service;

import java.util.ArrayList;
import java.util.Scanner;

import com.ilp.entity.Account;
import com.ilp.entity.Customer;
import com.ilp.entity.Product;
import com.ilp.entity.Service;

public class AccountConfiguration {

	public static Customer createCustomerAccount(Customer customer, ArrayList<Product> productList) {
		// TODO Auto-generated method stub
		Account account = null;
		if (customer == null) {
			Scanner scanner = new Scanner(System.in);
			System.out.println("Enter the Customer Code: ");
			String customerCode = scanner.nextLine();
			System.out.println("Enter the Customer Name: ");
			String customerName = scanner.nextLine();
			ArrayList<Account> accountList = new ArrayList<Account>();
			account = AccountConfiguration.createAccount(productList);
			accountList.add(account);
			customer = new Customer(customerCode, customerName, accountList);
			successfulAccountCreation(customer, account);
		} else {
			ArrayList<Account> accountList = customer.getAccountList();
			account = AccountConfiguration.createAccount(productList);
			accountList.add(account);
			customer.setAccountList(accountList);
			successfulAccountCreation(customer, account);
		}

		return customer;
	}

	private static Account createAccount(ArrayList<Product> productList) {
		// TODO Auto-generated method stub
		System.out.println("Create a new Account");
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter your AccountNo:");
		String accountNo = scanner.nextLine();
		System.out.println("Enter the Balance :");
		double accountBalance = scanner.nextDouble();
		scanner.nextLine();
		Product accountProduct = null;
		System.out.println("***********Products Available***********");
		for (Product product : productList) {
			System.out.println(product.getProductCode() + ". " + product.getProductName());
		}
		System.out.println("Enter the ID of the product you want: ");
		String productChoice = scanner.nextLine();
		for (Product product : productList) {
			if (productChoice.equalsIgnoreCase(product.getProductCode())) {
				accountProduct = product;
			}
		}

		return new Account(accountNo, accountBalance, accountProduct);
	}

	public static void successfulAccountCreation(Customer customer, Account account) {
		System.out.println("\n");
		System.out.println(account.getProduct().getProductName() + " created for " + customer.getCustomerName()
				+ " with the following Services");
		for (Service service : account.getProduct().getServiceList()) {
			System.out.println(service.getServiceCode() + ". " + service.getServiceName());
		}
		System.out.println("\nAccount is active.!!!!!!");
	}

	public static void displayCustomer(Customer customer) {
		// TODO Auto-generated method stub
		System.out.println("*************************Customer-Account Details*************************");
		System.out.println("CustomerId" + "	" + "CustomerName" + "		" + "AccountType" + "		" + "Balance");
		System.out.println("**************************************************************************");
		for (Account account : customer.getAccountList()) {
			System.out.println(customer.getCustomerCode() + " 		" + customer.getCustomerName() + " 		"
					+ account.getProduct().getProductName() + "		" + account.getBalance());
			Product product = account.getProduct();
			System.out.println("Services for " + product.getProductName());
			for (Service service : product.getServiceList()) {
				System.out.println(service.getServiceCode() + ". " + service.getServiceName());
			}
		}
	}



}
