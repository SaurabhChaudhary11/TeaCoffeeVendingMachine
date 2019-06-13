package com.yash.tcvm.TeaCoffeeVendingMachine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;



import org.apache.log4j.Logger;

import com.yash.tcvm.dao.InputScanner;
import com.yash.tcvm.dao.Product;
import com.yash.tcvm.dao.TotalSaleCost;
import com.yash.tcvm.service.impl.ContainerOperationsImpl;
import com.yash.tcvm.service.impl.GenerateReportImpl;
import com.yash.tcvm.service.impl.PaymentImpl;

public class CustomerOrder {

 private final static Logger logger = Logger.getLogger(CustomerOrder.class);
	
	private Integer totalQuantitySold = 0;

	private Double total = 0.0;

	private Integer insertedAmount = 0;

	private Double returnedAmount = 0.0;

	private Integer refillOption = 0;

	InputScanner inputScanner = new InputScanner();

	HashMap<String, List> totalSalePerProduct = new HashMap<String, List>();

	List<TotalSaleCost> totalSaleCostList = new ArrayList<TotalSaleCost>();

	ContainerOperationsImpl containerOperations = new ContainerOperationsImpl();

	GenerateReportImpl generateReport = new GenerateReportImpl();

	PaymentImpl payBill = new PaymentImpl();

	public CustomerOrder() {

	}

	public CustomerOrder(ContainerOperationsImpl containerOperations, GenerateReportImpl generateReport,
			PaymentImpl payBill, InputScanner inputScanner) {
		super();
		this.containerOperations = containerOperations;
		this.generateReport = generateReport;
		this.payBill = payBill;
		this.inputScanner = inputScanner;
	}

	public void prepareOrder(String orderType, double costPerCup, int orderQuantity, Product product) {
		try {

			if (containerOperations.checkAvailabilty(orderType, orderQuantity, product)) {
				int amount_flag = 0;
				do {
					logger.info("Please enter " + costPerCup * orderQuantity + " Rupee:");

					insertedAmount = inputScanner.nextInt();
					if (insertedAmount >= costPerCup * orderQuantity) {
						amount_flag = 1;
						returnedAmount = payBill.calculatePriceForOrder(orderType, costPerCup, orderQuantity,
								insertedAmount);

						containerOperations.adjustContainerQuantity(orderType, orderQuantity, product);

						logger.info("Drink successfully served using logger");
						
						if (Math.abs(returnedAmount) > 0.0)
							logger.info("Please collect your change:" + Math.abs(returnedAmount));
						
						totalQuantitySold = totalQuantitySold + orderQuantity;
						total = total + costPerCup * orderQuantity;

						for (TotalSaleCost totalCost : totalSaleCostList) {
							if (totalCost.getProductName().equals(orderType)) {
								TotalSaleCost totalSaleCost = new TotalSaleCost();
								totalSaleCost.setCost(totalCost.getCost() + orderQuantity * costPerCup);
								totalSaleCost.setQuantity(totalCost.getQuantity() + orderQuantity);
								totalSaleCost.setProductName(totalCost.getProductName());
								totalSaleCost.setProductID(totalCost.getProductID());

								totalSaleCostList.set(totalSaleCostList.indexOf(totalCost), totalSaleCost);

							}

						}

						totalSalePerProduct.put("total_Sale_Cost", totalSaleCostList);
					}
				} while (amount_flag == 0);

			} else {
				throw new RuntimeException();
			}
		}

		catch (RuntimeException e) {
			
			logger.error("Requested quantity not available, Please try again!");

			askToShowMenuAgain(product);

		}

	}

	public void getMenu(Product product) {
		
		logger.info("************************************************************\n**************Welcome To Tea-Coffee Machine*****************\n************************************************************");
		
		logger.info("1.Tea \n2.Black Tea \n3.Coffee \n4.Black Coffee \n5.Reset Container \n6.Refill Container \n7.Check Container Status \n8.Report \n9.Exit");
		
		String product_type = "$1~Tea$2~Black Tea$3~Coffee$4~Black Coffee";
		StringTokenizer menuTokenizer = new StringTokenizer(product_type, "$");
		if (totalSaleCostList.size() == 0) {
			while (menuTokenizer.hasMoreTokens()) {
				String string = (String) menuTokenizer.nextToken();
				TotalSaleCost totalCost = new TotalSaleCost();
				totalCost.setProductID(Integer.valueOf(string.split("~")[0]));
				totalCost.setProductName(string.split("~")[1]);
				totalCost.setCost(0.0);
				totalCost.setQuantity(0);
				totalSaleCostList.add(totalCost);
			}
		}
		totalSalePerProduct.put("total_Sale_Cost", totalSaleCostList);

		System.out.println("Please select your Option:");

		int selectedOrder = inputScanner.nextInt();
		int orderedQuantity = 0;
		if (selectedOrder <= 4) {
		//	System.out.println("Please enter quantity:");
			logger.info("Please enter quantity:");
			orderedQuantity = inputScanner.nextInt();
		}

		switch (selectedOrder) {
		case 1:
			logger.info("You have ordered " + orderedQuantity + " tea");
			prepareOrder("Tea", 10.0, orderedQuantity, product);
			askToShowMenuAgain(product);
			break;

		case 2:
			logger.info("You have ordered " + orderedQuantity + " black tea");
			prepareOrder("Black Tea", 5.0, orderedQuantity, product);
			askToShowMenuAgain(product);
			break;

		case 3:
			logger.info("You have ordered " + orderedQuantity + " coffee");
			prepareOrder("Coffee", 15.0, orderedQuantity, product);
			askToShowMenuAgain(product);
			break;
		
		case 4:
			logger.info("You have ordered " + orderedQuantity + " black coffee");
			prepareOrder("Black Coffee", 10.0, orderedQuantity, product);
			askToShowMenuAgain(product);
			break;
	
		case 5:
			containerOperations.resetContainer(product);
			askToShowMenuAgain(product);
			break;
		
		case 6:
			logger.info("****Refill Container****");
			logger.info("1.Tea\n2.Coffee\n3.Milk\n4.Sugar\n5.Water");
			refillOption = inputScanner.nextInt();
			containerOperations.reFillContainer(refillOption, product);
			askToShowMenuAgain(product);
			break;
		
		case 7:
			containerOperations.checkContainerStatus(product);
			askToShowMenuAgain(product);
			break;
		
		case 8:
			logger.info("################Report################");
			generateReport.prepareReport(product, totalSalePerProduct, totalQuantitySold, total);
			askToShowMenuAgain(product);
			break;
		
		case 9:
			logger.info("Thank you for your visit");
			break;

		default:
			logger.warn("You have entered invalid option!");
			askToShowMenuAgain(product);
			break;
		}

	}

	public void askToShowMenuAgain(Product product) {
		logger.info("Enter 0 for Menu Or 1 to Exit:");
		int askUser = inputScanner.nextInt();
		if (askUser == 0)
			getMenu(product);

	}

}
