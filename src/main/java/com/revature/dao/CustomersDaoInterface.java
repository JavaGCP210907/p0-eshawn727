package com.revature.dao;

import java.util.List;

import com.revature.models.Customers;

public interface CustomersDaoInterface {
	
	// return List of all customers
	public List<Customers> getCustomers();
	
	// return a customer given customer id
	public List<Customers> getCustomersById(int id);
	
	public void addCustomer(Customers customer);
	
	public void deleteCustomer(int num);
	
	public void updateCustLName(int num, String l_name);

}
