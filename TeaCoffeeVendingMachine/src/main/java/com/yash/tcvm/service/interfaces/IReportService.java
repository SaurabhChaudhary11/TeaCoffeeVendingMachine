package com.yash.tcvm.service.interfaces;

import java.util.HashMap;
import java.util.List;

import com.yash.tcvm.dao.Product;

public interface IReportService {
	
	public void prepareReport(Product product,HashMap<String,List> totalSale,Integer totalQuantitySold,Double totalPrice);

}
