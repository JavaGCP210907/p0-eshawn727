package com.revature.dao;

import java.util.List;

import com.revature.models.Orders;

public interface OrdersDaoInterface {
	
	public List<Orders> getOrders();
	
	public List<Orders> getOrderByNum(int num);
	
	public double showMoney();
	
	public double calcProfit();

}
