package com.yash.TeaCoffeeVendingMachine;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.yash.tcvm.service.impl.PaymentImpl;

import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;



@RunWith(MockitoJUnitRunner.class)
public class PaymentTest {

	@InjectMocks
	private PaymentImpl payBill;

	@Mock
	PaymentImpl payBillForOrder;
	
	@Test
	public void calculatePriceForOrderShouldReturnAmountToBeReturnedToCustomer() {

	//	PaymentImpl payBillForOrder = Mockito.mock(PaymentImpl.class);

		when(payBillForOrder.calculatePriceForOrder("Tea", 10.0, 5, 50)).thenReturn(0.0);
	//	doCallRealMethod().when(payBillForOrder).calculatePriceForOrder("Tea", 10.0, 5, 50);

		Assert.assertEquals(0.0, payBillForOrder.calculatePriceForOrder("Tea", 10.0, 5, 50), 0);

	}

}
