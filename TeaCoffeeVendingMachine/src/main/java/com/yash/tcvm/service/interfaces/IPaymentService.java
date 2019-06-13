package com.yash.tcvm.service.interfaces;

public interface IPaymentService {
	
	public double calculatePriceForOrder(String orderType, Double costPerCup, Integer orderQuantity,Integer insertedAmount);
}
