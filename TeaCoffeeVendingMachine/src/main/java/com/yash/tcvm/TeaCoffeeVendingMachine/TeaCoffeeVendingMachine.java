package com.yash.tcvm.TeaCoffeeVendingMachine;

import com.yash.tcvm.dao.Product;

public class TeaCoffeeVendingMachine {

	private static Product product = new Product();
	
	private static CustomerOrder customerOrder = new CustomerOrder();
	
	public static void main(String[] args) {
		customerOrder.getMenu(product);

	}

}
