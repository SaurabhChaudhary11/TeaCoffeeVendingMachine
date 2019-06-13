package com.yash.tcvm.service.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import com.yash.tcvm.TeaCoffeeVendingMachine.CustomerOrder;
import com.yash.tcvm.dao.Product;
import com.yash.tcvm.dao.TotalSaleCost;
import com.yash.tcvm.service.interfaces.IReportService;

public class GenerateReportImpl implements IReportService {
	 private final static Logger logger = Logger.getLogger(CustomerOrder.class);

	@Override
	public void prepareReport(Product product, HashMap<String, List> totalSale, Integer totalQuantitySold,
			Double totalPrice) {
		logger.info("*****Tea-Cofee Sold*********");
		List<TotalSaleCost> totalSaleCost = totalSale.get("total_Sale_Cost");
		for (TotalSaleCost e : totalSaleCost) {
				logger.info(e.getProductName() + ":" + e.getQuantity() + "cup :" + e.getCost());
		}
		logger.info("*****Total Product soldout and price*********");
		logger.info("Total cups:" + totalQuantitySold);
		logger.info("Total:" + totalPrice + "/-");
		logger.info("*****Available Quantity in Containers*********");
		logger.info("Tea:" + product.getTeaContainerCapacity());
		logger.info("Coffee:" + product.getCoffeeContainerCapacity());
		logger.info("Water:" + product.getWaterContainerCapacity());
		logger.info("Milk:" + product.getMilkContainerCapacity());
		logger.info("Sugar:" + product.getSugarContainerCapacity());

		logger.info("****Waste Of Products****");
		logger.info("Tea Wasted:" + product.getTeaWasteAmount());
		logger.info("Coffee Wasted:" + product.getCoffeeWasteAmount());
		logger.info("Water Wasted:" + product.getWaterWasteAmount());
		logger.info("Sugar Wasted:" + product.getSugarWasteAmount());
		logger.info("Milk Wasted:" + product.getMilkWasteAmount());

	}

}
