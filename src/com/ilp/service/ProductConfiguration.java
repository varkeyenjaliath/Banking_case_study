package com.ilp.service;

import java.util.ArrayList;
import java.util.Scanner;

import com.ilp.entity.CurrentAccount;
import com.ilp.entity.LoanAccount;
import com.ilp.entity.Product;
import com.ilp.entity.SavingsMaxAccount;
import com.ilp.entity.Service;

public class ProductConfiguration {

	public static ArrayList<Service> createService() {
		// TODO Auto-generated method stub
		ArrayList<Service> serviceList = new ArrayList<Service>();
		Scanner scanner = new Scanner(System.in);
		char serviceMenuChoice;
		do {
			System.out.println("Enter service ID: ");
			String serviceCode = scanner.nextLine();
			System.out.println("Enter service name: ");
			String serviceName = scanner.nextLine();
			System.out.println("Enter service rate: ");
			double serviceRate = scanner.nextDouble();
			scanner.nextLine();
			serviceList.add(new Service(serviceCode, serviceName, serviceRate));

			System.out.println("Do you want to add another service?(y/n): ");
			serviceMenuChoice = scanner.next().charAt(0);
			scanner.nextLine();
		} while (serviceMenuChoice == 'y');

		return serviceList;
	}

	public static ArrayList<Product> createProduct(ArrayList<Service> serviceList) {
		// TODO Auto-generated method stub
		String productCode;
		String productName;
		ArrayList<Product> productList = new ArrayList<Product>();
		ArrayList<Service> productServiceList = new ArrayList<Service>();
		Product product = null;
		Scanner scanner = new Scanner(System.in);

		char productMenuChoice;
		do {
			int productChoice;
			System.out.println("Choose the product you want to create");
			System.out.println("1. Savings Max Account");
			System.out.println("2. Current Account");
			System.out.println("3. Loan Account");
			System.out.println("Enter your choice: ");
			productChoice = scanner.nextInt();
			scanner.nextLine();
			switch (productChoice) {
			case 1:
				System.out.println("Enter product ID: ");
				productCode = scanner.nextLine();
				productName = "Savings Max Account";
				productServiceList = createProductServiceList(serviceList);
				product = new SavingsMaxAccount(productCode, productName, productServiceList);
				break;
			case 2:
				System.out.println("Enter product ID: ");
				productCode = scanner.nextLine();
				productName = "Current Account";
				productServiceList = createProductServiceList(serviceList);
				product = new CurrentAccount(productCode, productName, productServiceList);
				break;
			case 3:
				System.out.println("Enter product ID: ");
				productCode = scanner.nextLine();
				productName = "Loan Account";
				productServiceList = createProductServiceList(serviceList);
				product = new LoanAccount(productCode, productName, productServiceList);
				break;
			}

			productList.add(product);

			System.out.println("Do you want to add another product?(y/n): ");
			productMenuChoice = scanner.next().charAt(0);
			scanner.nextLine();
		} while (productMenuChoice == 'y');

		return productList;
	}

	private static ArrayList<Service> createProductServiceList(ArrayList<Service> serviceList) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		ArrayList<Service> productServiceList = new ArrayList<Service>();
		char serviceMenuChoice = 0;
		do {
			System.out.println("Choose the service you want to add");
			for (Service service : serviceList) {
				System.out.println(
						service.getServiceCode() + ". " + service.getServiceName() + " @Rs." + service.getRate());
			}
			System.out.println("Enter the ID of the service you want to add: ");
			String serviceChoice = scanner.nextLine();
			for (Service service : serviceList) {
				if (serviceChoice.equalsIgnoreCase(service.getServiceCode())) {
					productServiceList.add(service);
				}
			}

			System.out.println("Do you want to add another service to this product?(y/n): ");
			serviceMenuChoice = scanner.next().charAt(0);
			scanner.nextLine();
		} while (serviceMenuChoice == 'y');

		return productServiceList;

	}

}
