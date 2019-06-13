package com.yash.tcvm.service.impl;

import com.yash.tcvm.service.interfaces.IPaymentService;

public class PaymentImpl implements IPaymentService {

	@Override
	public double calculatePriceForOrder(String orderType, Double costPerCup, Integer orderQuantity,
			Integer insertedAmount) {

		
		
		//System.out.println((costPerCup * orderQuantity) - Double.valueOf(String.valueOf(insertedAmount)));
		return (costPerCup * orderQuantity) - Double.parseDouble(String.valueOf(insertedAmount));

	}

}
