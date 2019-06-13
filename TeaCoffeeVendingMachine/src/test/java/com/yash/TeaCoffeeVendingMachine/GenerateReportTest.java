package com.yash.TeaCoffeeVendingMachine;

import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.yash.tcvm.dao.Product;
import com.yash.tcvm.dao.TotalSaleCost;
import com.yash.tcvm.service.impl.GenerateReportImpl;

@RunWith(MockitoJUnitRunner.class)
public class GenerateReportTest {

	@InjectMocks
	private GenerateReportImpl generateReport;

	@Mock
	private Product product;

	@Mock
	private TotalSaleCost totalSaleCost;

	@Test
	public void shouldGenerateReport() {

		GenerateReportImpl report = Mockito.mock(GenerateReportImpl.class);
		
		TotalSaleCost totalCost = new TotalSaleCost();
		totalCost.setProductID(1);
		totalCost.setProductName("Tea");
		totalCost.setQuantity(5);
		totalCost.setCost(10d);
		
		List<TotalSaleCost> totalSaleCostList = new ArrayList<TotalSaleCost>();
		totalSaleCostList.add(totalCost);
		
		HashMap<String, List> totalSale = new HashMap<String, List>();		
		
		totalSale.put("total_Sale_Cost", totalSaleCostList);

		//doNothing().when(report.prepareReport(product, totalSale,10,10.0));
		
		doCallRealMethod().when(report).prepareReport(product, totalSale,10,10.0);

		report.prepareReport(product, totalSale,10,10.0);

		verify(report).prepareReport(product, totalSale,10,10.0);

	}

}
