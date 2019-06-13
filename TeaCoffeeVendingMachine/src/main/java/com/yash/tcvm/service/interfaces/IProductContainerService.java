package com.yash.tcvm.service.interfaces;

import com.yash.tcvm.dao.Product;

public interface IProductContainerService {

	public boolean checkAvailabilty(String productType, Integer quantity, Product product);

	public void adjustContainerQuantity(String productType, Integer quantity, Product product);

	public void checkContainerStatus(Product product);

	public void reFillContainer(Integer productID, Product product);

	public void resetContainer(Product product);

}
