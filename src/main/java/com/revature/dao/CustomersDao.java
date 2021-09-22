package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Customers;
import com.revature.utils.ConnectionUtil;

public class CustomersDao implements CustomersDaoInterface {

	@Override
	public List<Customers> getCustomers() {
		
		// Open connection to database and use list to store database records
		try(Connection db_link = ConnectionUtil.getConnection()){
			
			ResultSet rs = null;
			String sql = "select * from customers order by cust_num";			
			Statement sqlObj = db_link.createStatement();
			rs = sqlObj.executeQuery(sql);
			
			List<Customers> custList = new ArrayList<>();
			
			while(rs.next()) {
				
				Customers cust = new Customers(rs.getInt("cust_num"), 
											   rs.getString("f_name"),
											   rs.getString("l_name"));
				
				custList.add(cust);
			}
			
			// return filled arrayList with data from customers table
			return custList;
			
		} catch (SQLException e) {
			System.out.println("Error...db connection/CustomersDao/getCustomers");
			e.printStackTrace();
		}
				
		return null;
	}

	@Override
	public List<Customers> getCustomersById(int id) {
		// search for customer by id number
		try(Connection db_link = ConnectionUtil.getConnection()){
			
			ResultSet rs = null;
			String sql = "select * from customers where cust_num = ?";
			PreparedStatement ps = db_link.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			List<Customers> custList = new ArrayList<>();
			
			while(rs.next()) {
				Customers cust = new Customers(rs.getInt("cust_num"), rs.getString("f_name"), rs.getString("l_name"));
				custList.add(cust);
			}
			
			return custList;
			
		} catch (SQLException e) {
			System.out.println("Error...db connection/CustomersDao/getCustomerById");
			e.printStackTrace();
		}
				
		return null;
	}

	@Override
	public void addCustomer(Customers customer) {
		// add customer to customers table
		try(Connection db_link = ConnectionUtil.getConnection()){
			String sql = "insert into customers (f_name, l_name) values (?, ?)";			
			PreparedStatement ps = db_link.prepareStatement(sql);
			
			ps.setString(1, customer.getF_name());
			ps.setString(2, customer.getL_name());
			ps.executeUpdate();
			System.out.println("Customer Added");
			
		} catch (SQLException e) {
			System.out.println("Error...db connection/CustomersDao/addCustomer");
			e.printStackTrace();
		}
		
	}

	@Override
	public void deleteCustomer(int num) {
		// remove a customer from table
		try(Connection db_link = ConnectionUtil.getConnection()){
			String sql = "delete from customers where cust_num = ?";
			PreparedStatement ps = db_link.prepareStatement(sql);
			ps.setInt(1, num);
			ps.executeUpdate();
			System.out.println("Customer Removed");
			
		} catch (SQLException e) {
			System.out.println("Error...db connection/CustomersDao/deleteCustomer");
			e.printStackTrace();
		}
		
	}

	@Override
	public void updateCustLName(int num, String l_name) {
		// update a customers last name
		try(Connection db_link = ConnectionUtil.getConnection()){
			String sql = "update customers set l_name = ? where cust_num = ?";
			PreparedStatement ps = db_link.prepareStatement(sql);
			ps.setString(1, l_name);
			ps.setInt(2, num);
			ps.executeUpdate();
			System.out.println("Customer Updated");
			
		} catch (SQLException e) {
			System.out.println("Error...db connection/CustomersDao/updateCustomer");
			e.printStackTrace();
		}
	}



}
