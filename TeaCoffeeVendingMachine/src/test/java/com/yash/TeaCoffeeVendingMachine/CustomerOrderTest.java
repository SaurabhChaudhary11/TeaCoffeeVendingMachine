package com.yash.TeaCoffeeVendingMachine;


import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;

import static org.mockito.Mockito.doNothing;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Appender;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggingEvent;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.util.MockNameImpl;
import org.mockito.internal.verification.Times;
import org.mockito.runners.MockitoJUnitRunner;

import com.yash.tcvm.TeaCoffeeVendingMachine.CustomerOrder;
import com.yash.tcvm.dao.InputScanner;
import com.yash.tcvm.dao.Product;
import com.yash.tcvm.service.impl.ContainerOperationsImpl;
import com.yash.tcvm.service.impl.GenerateReportImpl;
import com.yash.tcvm.service.impl.PaymentImpl;
import static org.mockito.Mockito.verify;


@RunWith(MockitoJUnitRunner.class)
public class CustomerOrderTest {

	@InjectMocks
	private CustomerOrder customerOrder;

	@Mock
	private Product product;

	@Mock
	private ContainerOperationsImpl containerOperations;

	@Mock
	private PaymentImpl payBill;

	@Mock
	private InputScanner inputScanner;

	@Mock
	private GenerateReportImpl generateReportImpl;

	@Mock
	private Appender appenderMock;
	
	@Before 
	public void setupAppender(){
		//appenderMock = mock(Appender.class);
		Logger.getRootLogger().addAppender(appenderMock);
	}
	
	@After
	public void removeAppender(){
		Logger.getRootLogger().removeAppender(appenderMock);
	}
	
	
	@Test
	public void getMenuShouldAllowToOrderTea() {

		Product product1 = new Product();

		when(containerOperations.checkAvailabilty("Tea", 1, product1)).thenReturn(true);
		when(payBill.calculatePriceForOrder("Tea", 10.0, 1, 10)).thenReturn(0.0);
		doNothing().when(containerOperations).adjustContainerQuantity("Tea", 1, product1);
		when(inputScanner.nextInt()).thenReturn(1).thenReturn(1).thenReturn(10);

		customerOrder.getMenu(product1);
		verify(appenderMock,times(7)).doAppend(Mockito.anyObject());
	}

	@Test
	public void getMenuShouldAllowToOrderBlackTea() {

		Product product1 = new Product();

		when(containerOperations.checkAvailabilty("Black Tea", 1, product1)).thenReturn(true);
		when(payBill.calculatePriceForOrder("Black Tea", 5.0, 1, 5)).thenReturn(0.0);
		doNothing().when(containerOperations).adjustContainerQuantity("Black Tea", 1, product1);
		when(inputScanner.nextInt()).thenReturn(2).thenReturn(1).thenReturn(5);

		customerOrder.getMenu(product1);
		
		assertTrue(containerOperations.checkAvailabilty("Black Tea", 1, product1));
		verify(appenderMock,times(7)).doAppend(Mockito.anyObject());
	}

	@Test
	public void getMenuShouldAllowToOrderCoffee() {

		Product product1 = new Product();

		when(containerOperations.checkAvailabilty("Coffee", 1, product1)).thenReturn(true);
		when(payBill.calculatePriceForOrder("Coffee", 15.0, 1, 15)).thenReturn(0.0);
		doNothing().when(containerOperations).adjustContainerQuantity("Coffee", 1, product1);
		when(inputScanner.nextInt()).thenReturn(3).thenReturn(1).thenReturn(15);

		customerOrder.getMenu(product1);

		assertTrue(containerOperations.checkAvailabilty("Coffee", 1, product1));
		verify(appenderMock,times(7)).doAppend(Mockito.anyObject());
	}

	@Test
	public void getMenuShouldAllowToOrderBlackCoffee() {

		Product product1 = new Product();

		when(containerOperations.checkAvailabilty("Black Coffee", 1, product1)).thenReturn(true);
		when(payBill.calculatePriceForOrder("Black Coffee", 10.0, 1, 10)).thenReturn(0.0);
		doNothing().when(containerOperations).adjustContainerQuantity("Black Coffee", 1, product1);
		when(inputScanner.nextInt()).thenReturn(4).thenReturn(1).thenReturn(10);

		customerOrder.getMenu(product1);

		assertTrue(containerOperations.checkAvailabilty("Black Coffee", 1, product1));
		verify(appenderMock,times(7)).doAppend(Mockito.anyObject());
	}

	@Test
	public void getMenuShouldAllowToOrderResetContainers() {

		Product product1 = new Product();

		doNothing().when(containerOperations).resetContainer(product1);
		when(inputScanner.nextInt()).thenReturn(5).thenReturn(1);

		customerOrder.getMenu(product1);

		verify(containerOperations).resetContainer(product1);
		verify(appenderMock,times(3)).doAppend(Mockito.anyObject());
	}

	@Test
	public void getMenuShouldAllowToOrderRefillContainers() {

		Product product1 = new Product();

		product1.setTeaContainerCapacity(1000);

		doNothing().when(containerOperations).reFillContainer(1, product1);
		when(inputScanner.nextInt()).thenReturn(6).thenReturn(1).thenReturn(1);

		customerOrder.getMenu(product1);

		verify(containerOperations).reFillContainer(1, product1);
		verify(appenderMock,times(5)).doAppend(Mockito.anyObject());
	}

	@Test
	public void getMenuShouldAllowToCheckContainersStatus() {

		Product product1 = new Product();

		doNothing().when(containerOperations).checkContainerStatus(product1);
		when(inputScanner.nextInt()).thenReturn(7).thenReturn(1);

		customerOrder.getMenu(product1);

		verify(containerOperations).checkContainerStatus(product1);
		verify(appenderMock,times(3)).doAppend(Mockito.anyObject());
	}

	@Test
	public void getMenuShouldAllowToGenerateReport() {

		Product product1 = new Product();

		HashMap<String, List> totalSalePerProduct = new HashMap<String, List>();

		doNothing().when(generateReportImpl).prepareReport(product1, totalSalePerProduct, 0, 0.0);
		when(inputScanner.nextInt()).thenReturn(8).thenReturn(1);

		customerOrder.getMenu(product1);
		verify(appenderMock,times(4)).doAppend(Mockito.anyObject());
	}

	@Test
	public void getMenuShouldHandleExceptionWhenAvalabilityIsFalse() {

		Product product1 = new Product();

		when(containerOperations.checkAvailabilty("Tea", 10, product1)).thenReturn(false);

		when(inputScanner.nextInt()).thenReturn(1).thenReturn(10).thenReturn(1);

		customerOrder.getMenu(product1);

		assertFalse(containerOperations.checkAvailabilty("Tea", 10, product1));
		
		verify(appenderMock,times(7)).doAppend(Mockito.anyObject());
	}

	@Test
	public void getMenuShouldAskForMenuWhenInputIsInvalid() {

		Product product1 = new Product();

		when(inputScanner.nextInt()).thenReturn(10).thenReturn(1);

		customerOrder.getMenu(product1);
		
		verify(appenderMock, times(4)).doAppend(Mockito.anyObject());
	}

	@Test
	public void getMenuShouldReturnExtraAmountWhenInserted() {

		Product product1 = new Product();
		
		when(inputScanner.nextInt()).thenReturn(1).thenReturn(1).thenReturn(12).thenReturn(1);
		when(containerOperations.checkAvailabilty("Tea", 1, product1)).thenReturn(true);
		when(payBill.calculatePriceForOrder("Tea", 10.0, 1, 12)).thenReturn(2.0);
		doNothing().when(containerOperations).adjustContainerQuantity("Tea", 1, product1);
		
		customerOrder.getMenu(product1);
		verify(appenderMock,times(8)).doAppend(Mockito.anyObject());
	}
	
	@Test
	public void shouldReturnCustomerOrder(){
		CustomerOrder customer = new CustomerOrder();
		
	}
	
	@Test
	public void shouldExitFromSystemCustomerOrder(){
		
		Product product1 = new Product();
		when(inputScanner.nextInt()).thenReturn(9);
		
		customerOrder.getMenu(product1);
		
		verify(appenderMock,times(3)).doAppend(Mockito.anyObject());
		
		
	}

}
