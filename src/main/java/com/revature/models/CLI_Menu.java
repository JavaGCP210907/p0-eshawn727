package com.revature.models;

import java.util.List;
import java.util.Scanner;

import com.revature.dao.CustomersDao;
import com.revature.dao.OrdersDao;
import com.revature.dao.ProductsDao;

public class CLI_Menu {
	
	CustomersDao custDao =  new CustomersDao();
	ProductsDao prodDao = new ProductsDao();
	OrdersDao ordDao = new OrdersDao();
	String input;
	List<Customers> custList;
	List<Products> prodList;
	List<Orders> ordList;
	
	/* ADD LOGGER
	 * 
	 * 
	 * 
	 */
	
	public void runCLI() {
		
		boolean runMenu = true;
		
		Scanner scan = new Scanner(System.in);
		
		// Greeting
		System.out.println("--------------------------------");
		System.out.println("|WELCOME TO PEGASUS ENTERPRISES|");
		System.out.println("--------------------------------");
		System.out.println();
		
		while(runMenu) {
			
	
			System.out.println("|----------ENTER AN OPTION--------------|");
			System.out.println("|                                       |");
			System.out.println("|Show Customers Table:         customers|");
			System.out.println("|---------------------------------------|");
			System.out.println("|Show Products Table:           products|");
			System.out.println("|---------------------------------------|");
			System.out.println("|Show Orders Table:               orders|");
			System.out.println("|Search Customers by number:     custNum|");
			System.out.println("|Search Products by Price:      findProd|");
			System.out.println("|Add Customer:                   addCust|");
			System.out.println("|Update Product Sale Price: updateProdPr|");
			System.out.println("|Delete Product:              deleteProd|");
			System.out.println("|Calculate Gross Sales:            gross|");
			//System.out.println("|Calculate Product Net Profit: profit   |");
			//System.out.println("|Calculate Gross Profit Margin: margin  |");
			System.out.println("|Exit:                              exit|");
			System.out.println("|---------------------------------------|");
			input = scan.nextLine();
			
			
			switch (input) {
			
			case "customers": {
				custList = custDao.getCustomers();
				for(Customers cust: custList) {
					System.out.println(cust);
				}
				break;
			}
			case "products": {
				prodList = prodDao.getProducts();
				for(Products prod: prodList) {
					System.out.println(prod);
				}
				break;
			}
			case "orders": {
				ordList = ordDao.getOrders();
				for(Orders ord: ordList) {
					System.out.println(ord);
				}
				break;
			}
			case "custNum": {
				System.out.println("Enter the customer number");
				int id = scan.nextInt();	
				scan.nextLine();
				custList = custDao.getCustomersById(id);
				for(Customers cust: custList) {
					System.out.println(cust);
				}
				break;
			}
			case "findProd": {
				System.out.println("Enter the Products Price");
				double price = scan.nextDouble();
				scan.nextLine();
				prodList = prodDao.getProductByPrice(price);
				for(Products prod: prodList) {
					System.out.println(prod);
				}
				break;
			}
			case "addCust":	{
				System.out.println("Enter the cutomers first name: ");
				String fName = scan.nextLine();
				System.out.println("Enter the customers last name: ");
				String lName = scan.nextLine();
				Customers cust = new Customers(fName, lName);
				custDao.addCustomer(cust);
				break;
			}
			case "updateProdPr": {
				System.out.println("Enter the product ID: ");
				int prodId = scan.nextInt();
				System.out.println("Enter the new sale price: ");
				double salePr = scan.nextDouble();
				scan.nextLine();
				scan.nextLine();
				prodDao.updateProdSalePrice(prodId, salePr);
				break;
			}
			case "deleteProd": {
				System.out.println("Enter the Product ID: ");
				int prodId = scan.nextInt();
				scan.nextLine();
				prodDao.deleteProduct(prodId);
				break;
			}
			case "gross": {
				System.out.println(ordDao.showMoney());
				break;
			}
			case "exit": {
				System.out.println("Have a nice day!");
				runMenu = false;
				break;
			}
			default: {
				System.out.println("That selction is not available...Please try again");
			}
			} //end switch
		}
		
		scan.close();
	}

}
