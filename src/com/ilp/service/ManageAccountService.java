package com.ilp.service;

import java.util.Scanner;

import com.ilp.entity.Account;
import com.ilp.entity.Customer;
import com.ilp.entity.LoanAccount;
import com.ilp.entity.Product;
import com.ilp.entity.SavingsMaxAccount;
import com.ilp.entity.Service;

public class ManageAccountService {
	public static void transactionBill(Customer customer) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the customer Id :");
		String customerCode = scanner.nextLine();
		if (!(customerCode.equalsIgnoreCase(customer.getCustomerCode()))) {
			System.out.println("Customer not found!!!!!");
		} else {
			System.out.println(customer.getCustomerName() + " has the following accounts");
			for (Account account : customer.getAccountList()) {
				System.out.println(account.getAccountNo() + ". " + account.getProduct().getProductName());
			}

			System.out.println("Enter the account no you want to operate on: ");
			String accountChoice = scanner.nextLine();
			for (Account account : customer.getAccountList()) {
				if (accountChoice.equalsIgnoreCase(account.getAccountNo())) {
					Product product = account.getProduct();
					System.out.println("Choose the Service you want to use");
					for (Service service : product.getServiceList()) {
						System.out.println(service.getServiceCode() + ". " + service.getServiceName());
					}

					System.out.println("Enter the service ID you want to use: ");
					String serviceChoice = scanner.nextLine();
					for (Service service : product.getServiceList()) {
						if (serviceChoice.equalsIgnoreCase(service.getServiceCode())) {
							System.out.println("Rate of service = Rs." + service.getRate());
							System.out.println("Enter the number of transactions: ");
							double noOfTransactions = scanner.nextDouble();
							scanner.nextLine();
							System.out.println("Total Cost = Rs." + (noOfTransactions * service.getRate()));
						}
					}
				}
			}
		}

	}

	public static Customer manageAccounts(Customer customer) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the customer Id :");
		String customerCode = scanner.nextLine();
		if (!(customerCode.equalsIgnoreCase(customer.getCustomerCode()))) {
			System.out.println("Customer not found!!!!!");
		} else {
			System.out.println(customer.getCustomerName() + " has the following accounts");
			for (Account account : customer.getAccountList()) {
				System.out.println(account.getAccountNo() + ". " + account.getProduct().getProductName());
			}

			System.out.println("Enter the account no you want to operate on: ");
			String accountChoice = scanner.nextLine();
			for (Account account : customer.getAccountList()) {
				if (accountChoice.equalsIgnoreCase(account.getAccountNo())) {
					char operationMenuChoice;
					do {
						System.out.println("Choose the operation you want to perform");
						System.out.println("1. Deposit");
						System.out.println("2. Withdraw");
						System.out.println("3. Display balance");
						System.out.println("Enter your choice: ");
						int operationChoice = scanner.nextInt();
						scanner.nextLine();
						switch (operationChoice) {
						case 1:
							account = depositMoney(account);
							break;
						case 2:
							account = withdrawMoney(account);
							break;
						case 3:
							displayAccount(customer, account);
							break;
						}
						System.out.println("Do you want to perform another operation?(y/n): ");
						operationMenuChoice = scanner.next().charAt(0);
						scanner.nextLine();
					} while (operationMenuChoice == 'y');

				}
			}

		}

		return customer;
	}

	private static Account depositMoney(Account account) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the amount to be deposited:");
		double depositAmount = scanner.nextDouble();
		scanner.nextLine();
		if (account.getProduct() instanceof LoanAccount) {
			LoanAccount loanAccount = (LoanAccount) account.getProduct();
			System.out.println("Cheque deposit?(y/n): ");
			char chequeDeposit = scanner.next().charAt(0);
			scanner.nextLine();
			if (chequeDeposit == 'y') {
				double chequeCharge = loanAccount.getChequeDepositCharge() * depositAmount;
				System.out.println("Rs." + chequeCharge + " has been charged for cheque deposit");
				account.setBalance(account.getBalance() + depositAmount - chequeCharge);
			}
		} else {
			account.setBalance(account.getBalance() + depositAmount);
		}
		return account;
	}

	private static Account withdrawMoney(Account account) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the amount to be withdrawn:");
		double withdrawalAmount = scanner.nextDouble();
		scanner.nextLine();
		if (account.getProduct() instanceof SavingsMaxAccount) {
			SavingsMaxAccount savingsMaxAccount = (SavingsMaxAccount) account.getProduct();
			System.out.println("SAVINGS LOOP");
			if (account.getBalance() >= savingsMaxAccount.getMinimumBalance() + withdrawalAmount) {
				account.setBalance(account.getBalance() - withdrawalAmount);
			} else {
				System.out.println("WITHDRAWAL DOES NOT SATISFY MINIMUM BALANCE REQUIREMENT");
			}
		} else if (account.getBalance() < withdrawalAmount) {
			System.out.println("INSUFFICIENT BALANCE!!!!!");
		} else {
			account.setBalance(account.getBalance() - withdrawalAmount);
		}
		return account;

	}

	private static void displayAccount(Customer customer, Account account) {
		// TODO Auto-generated method stub
		System.out.println("*************************Customer-Account Details*************************");
		System.out.println("CustomerId" + "	" + "CustomerName" + "		" + "AccountType" + "		" + "Balance");
		System.out.println("**************************************************************************");
		System.out.println(customer.getCustomerCode() + " 		" + customer.getCustomerName() + " 		"
				+ account.getProduct().getProductName() + "		" + account.getBalance());
		Product product = account.getProduct();
		System.out.println("Services for " + product.getProductName());
		for (Service service : product.getServiceList()) {
			System.out.println(service.getServiceCode() + ". " + service.getServiceName());
		}

	}
}
