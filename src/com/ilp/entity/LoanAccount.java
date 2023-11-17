package com.ilp.entity;

import java.util.ArrayList;

public class LoanAccount extends Product {
	private double chequeDepositCharge = 0.3;

	public LoanAccount(String productCode, String productName, ArrayList<Service> serviceList) {
		super(productCode, productName, serviceList);
		// TODO Auto-generated constructor stub
	}

	public double getChequeDepositCharge() {
		return chequeDepositCharge;
	}

	public void setChequeDepositCharge(double chequeDepositCharge) {
		this.chequeDepositCharge = chequeDepositCharge;
	}

}
