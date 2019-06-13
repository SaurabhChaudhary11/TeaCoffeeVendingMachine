package com.yash.tcvm.dao;

public class TotalSaleCost {

	int productID;
	String productName;
	int quantity = 0;

	double cost = 0.0;

	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int qunatity) {
		this.quantity = qunatity;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

}
