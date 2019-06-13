package com.yash.tcvm.service.impl;

import com.yash.tcvm.dao.Product;
import com.yash.tcvm.service.interfaces.IProductContainerService;

public class ContainerOperationsImpl implements IProductContainerService {

	@Override
	public boolean checkAvailabilty(String productType, Integer quantity, Product product) {
		if (productType.equalsIgnoreCase("Tea")) {
			if ((product.getTeaContainerCapacity() - quantity * 5 < 0)
					|| (product.getSugarContainerCapacity() - quantity * 15 < 0)
					|| (product.getWaterContainerCapacity() - quantity * 60 < 0)
					|| (product.getMilkContainerCapacity() - quantity * 40 < 0)) {
				return false;
			}
			return true;
		} else if (productType.equalsIgnoreCase("Black Tea")) {
			if ((product.getTeaContainerCapacity() - quantity * 3 < 0)
					|| (product.getSugarContainerCapacity() - quantity * 15 < 0)
					|| (product.getWaterContainerCapacity() - quantity * 100 < 0)) {
				return false;
			}
			return true;
		} else if (productType.equalsIgnoreCase("Coffee")) {
			if ((product.getCoffeeContainerCapacity() - quantity * 4 < 0)
					|| (product.getSugarContainerCapacity() - quantity * 15 < 0)
					|| (product.getWaterContainerCapacity() - quantity * 20 < 0)
					|| (product.getMilkContainerCapacity() - quantity * 80 < 0)) {
				return false;
			}
			return true;
		} else if (productType.equalsIgnoreCase("Black Coffee")) {
			if ((product.getCoffeeContainerCapacity() - quantity * 3 < 0)
					|| (product.getSugarContainerCapacity() - quantity * 15 < 0)
					|| (product.getWaterContainerCapacity() - quantity * 100 < 0)) {
				return false;
			}
			return true;
		}
		return false;
	}

	@Override
	public void adjustContainerQuantity(String productType, Integer quantity, Product product) {
		if (productType.equalsIgnoreCase("Tea")) {
			product.setTeaContainerCapacity(product.getTeaContainerCapacity() - (quantity * 5 + quantity * 1));
			product.setWaterContainerCapacity(product.getWaterContainerCapacity() - (quantity * 60 + quantity * 5));
			product.setSugarContainerCapacity(product.getSugarContainerCapacity() - (quantity * 15 + quantity * 2));
			product.setMilkContainerCapacity(product.getMilkContainerCapacity() - (quantity * 40 + quantity * 4));

			product.setTeaWasteAmount(product.getTeaWasteAmount() + quantity * 1);
			product.setWaterWasteAmount(product.getWaterWasteAmount() + quantity * 5);
			product.setSugarWasteAmount(product.getSugarWasteAmount() + quantity * 2);
			product.setMilkWasteAmount(product.getMilkWasteAmount() + quantity * 4);

		} else if (productType.equalsIgnoreCase("Black Tea")) {
			product.setTeaContainerCapacity(product.getTeaContainerCapacity() - quantity * 3);
			product.setWaterContainerCapacity(product.getWaterContainerCapacity() - (quantity * 100 + quantity * 12));
			product.setSugarContainerCapacity(product.getSugarContainerCapacity() - (quantity * 15 + quantity * 3));

	
			product.setWaterWasteAmount(product.getWaterWasteAmount() + quantity * 12);
			product.setSugarWasteAmount(product.getSugarWasteAmount() + quantity * 2);

		}

		else if (productType.equalsIgnoreCase("Coffee")) {
			product.setCoffeeContainerCapacity(product.getCoffeeContainerCapacity() - (quantity * 4 + quantity * 1));
			product.setMilkContainerCapacity(product.getMilkContainerCapacity() - (quantity * 80 + quantity * 8));
			product.setSugarContainerCapacity(product.getSugarContainerCapacity() - (quantity * 15 + quantity * 2));
			product.setWaterContainerCapacity(product.getWaterContainerCapacity() - (quantity * 20 + quantity * 3));

			product.setCoffeeWasteAmount(product.getCoffeeWasteAmount() + quantity * 1);
			product.setMilkWasteAmount(product.getMilkWasteAmount() + quantity * 8);
			product.setSugarWasteAmount(product.getSugarWasteAmount() + quantity * 2);
			product.setWaterWasteAmount(product.getWaterWasteAmount() + quantity * 3);

		} else if (productType.equalsIgnoreCase("Black Coffee")) {
			product.setCoffeeContainerCapacity(product.getCoffeeContainerCapacity() - quantity * 3);
			product.setSugarContainerCapacity(product.getSugarContainerCapacity() - (quantity * 15 + quantity * 2));
			product.setWaterContainerCapacity(product.getWaterContainerCapacity() - (quantity * 100 + quantity * 12));

			product.setSugarWasteAmount(product.getSugarWasteAmount() + quantity * 2);
			product.setWaterWasteAmount(product.getWaterWasteAmount() + quantity * 12);

		}

	}

	public void resetContainer(Product product) {

		product.setTeaContainerCapacity(2000);
		product.setCoffeeContainerCapacity(2000);
		product.setMilkContainerCapacity(10000);
		product.setWaterContainerCapacity(15000);
		product.setSugarContainerCapacity(8000);
		System.out.println("All Containers successfully Reset");

	}

	@Override
	public void checkContainerStatus(Product product) {

		System.out.println("*********Quantity Available in Containers*******");
		System.out.println("Tea:" + product.getTeaContainerCapacity());
		System.out.println("Coffee:" + product.getCoffeeContainerCapacity());
		System.out.println("Milk:" + product.getMilkContainerCapacity());
		System.out.println("Sugar:" + product.getSugarContainerCapacity());
		System.out.println("Water:" + product.getWaterContainerCapacity());

	}

	@Override
	public void reFillContainer(Integer productID, Product product) {
		try {
			if (productID == 1 && product.getTeaContainerCapacity() < 2000) {
				product.setTeaContainerCapacity(2000);
				System.out.println("Successfully Refilled");
			} else if (productID == 2 && product.getCoffeeContainerCapacity() < 2000) {
				product.setCoffeeContainerCapacity(2000);
				System.out.println("Successfully Refilled");
			} else if (productID == 3 && product.getMilkContainerCapacity() < 10000) {
				product.setMilkContainerCapacity(10000);
				System.out.println("Successfully Refilled");
			} else if (productID == 4 && product.getWaterContainerCapacity() < 15000) {
				product.setWaterContainerCapacity(15000);
				System.out.println("Successfully Refilled");
			} else if (productID == 5 && product.getSugarContainerCapacity() < 8000) {
				product.setSugarContainerCapacity(8000);
				System.out.println("Successfully Refilled");
			} else {
				throw new RuntimeException();
			}
		} catch (RuntimeException e) {
			System.out.println("Container will Overflow!!");
		}

	}

}
