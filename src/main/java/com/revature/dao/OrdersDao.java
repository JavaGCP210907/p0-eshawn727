package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Orders;
import com.revature.utils.ConnectionUtil;

public class OrdersDao implements OrdersDaoInterface {

	@Override
	public List<Orders> getOrders() {
		
		try(Connection db_link = ConnectionUtil.getConnection()){
			
			ResultSet rs = null;
			String sql = "select * from orders";			
			Statement sqlObj = db_link.createStatement();
			rs = sqlObj.executeQuery(sql);
			
			List<Orders> orderList = new ArrayList<>();
			
			while(rs.next()) {
				
				Orders order = new Orders(rs.getInt("order_num"), 
										  rs.getInt("prod_id_fk"),
										  rs.getInt("cust_num_fk"));
				
				orderList.add(order);
			}
			
			// return filled arrayList with data from customers table
			return orderList;
			
		} catch (SQLException e) {
			System.out.println("Error...db connection/OrdersDao");
			e.printStackTrace();
		}
				
		return null;
	}

	@Override
	public List<Orders> getOrderByNum(int num) {
		// TODO Auto-generated method stub
		try(Connection db_link = ConnectionUtil.getConnection()){
			
			ResultSet rs = null;
			String sql = "select * from orders where order_num = ?";
			PreparedStatement ps = db_link.prepareStatement(sql);
			ps.setInt(1, num);
			rs = ps.executeQuery();
			List<Orders> orderList = new ArrayList<>();
			
			while(rs.next()) {
				Orders ord = new Orders(rs.getInt("order_num"), rs.getInt("prod_id_fk"), rs.getInt("cust_num"));
				orderList.add(ord);
			}
			
			return orderList;
			
		} catch (SQLException e) {
			System.out.println("Error...db connection/OrdersDao/getOrderByNum");
		}
		
		return null;
	}

	@Override
	public String showMoney() {
		// TODO Auto-generated method stub
		String sumAmount = null;
		try(Connection db_link = ConnectionUtil.getConnection()){
			double sum = 0;
			ResultSet rs = null;
			String sql = "select sum(prod_sale_price) as sum_alias from products, orders where products.prod_id = orders.prod_id_fk";
			Statement s = db_link.createStatement();
		
			rs = s.executeQuery(sql);		
			
			while (rs.next()) {
				sum = rs.getDouble("sum_alias");
			}
			
			System.out.println("We made  profits!!" + sum);
			sumAmount = "First quarter sales are at " + sum + " to date!";
			
		} catch (SQLException e) {
			System.out.println("Error...db connection/OrdersDao/showMoney");
		}
		
		
		return sumAmount;
	}

}
