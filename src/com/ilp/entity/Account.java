package com.ilp.entity;

public class Account {
	private String accountNo;
	private double balance;
	private Product product;

	public Account(String accountNo, double balance, Product product) {
		this.accountNo = accountNo;
		this.balance = balance;
		this.product = product;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}
