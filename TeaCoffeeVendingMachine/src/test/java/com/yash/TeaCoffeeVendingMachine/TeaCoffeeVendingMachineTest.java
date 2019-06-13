package com.yash.TeaCoffeeVendingMachine;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.yash.tcvm.TeaCoffeeVendingMachine.CustomerOrder;
import com.yash.tcvm.TeaCoffeeVendingMachine.TeaCoffeeVendingMachine;
import com.yash.tcvm.dao.Product;

@RunWith(MockitoJUnitRunner.class)
public class TeaCoffeeVendingMachineTest {

	@InjectMocks
	private TeaCoffeeVendingMachine teaCoffeeVendingMachine;

	@Mock
	private static Product product;

	@Mock
	private static CustomerOrder customerOrder;

	@Test
	public void mainTest() {
	
		
	//	Mockito.doNothing().when(customerOrder).getMenu(product);
		
	//	TeaCoffeeVendingMachine.main(null);
		

	}

}
