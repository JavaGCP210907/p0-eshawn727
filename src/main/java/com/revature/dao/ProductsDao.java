package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Products;
import com.revature.utils.ConnectionUtil;

public class ProductsDao implements ProductsDaoInterface {

	@Override
	public List<Products> getProducts() {
		// store database records in list
		try(Connection db_link = ConnectionUtil.getConnection()){
			
			ResultSet rs = null;
			String sql = "select * from products";			
			Statement sqlObj = db_link.createStatement();
			rs = sqlObj.executeQuery(sql);
			
			List<Products> prodList = new ArrayList<>();
			
			while(rs.next()) {
				
				Products prod = new Products(rs.getInt("prod_id"), 
											 rs.getString("prod_name"),
											 rs.getString("prod_type"),
											 rs.getDouble("prod_production_cost"),
											 rs.getDouble("prod_sale_price"));
				
				prodList.add(prod);
			}
			
			// return filled arrayList with data from customers table
			return prodList;
			
		} catch (SQLException e) {
			System.out.println("Error...db connection/ProductsDao");
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<Products> getProductByPrice(double price) {
		// search products table by sale price
		try(Connection db_link = ConnectionUtil.getConnection()){
			
			ResultSet rs = null;
			String sql = "select * from products where prod_sale_price = ?";
			PreparedStatement ps = db_link.prepareStatement(sql);
			ps.setDouble(1, price);
			rs = ps.executeQuery();
			
			List<Products> prodList = new ArrayList<>();
			
			while(rs.next()) {
				
				Products prod = new Products(rs.getInt("prod_id"), 
											 rs.getString("prod_name"),
											 rs.getString("prod_type"),
											 rs.getDouble("prod_production_cost"),
											 rs.getDouble("prod_sale_price"));
				
				prodList.add(prod);
			}
			
			// return filled arrayList with data from customers table
			return prodList;
			
		} catch (SQLException e) {
			System.out.println("Error...db connection/CustomersDao/getCustomerById");
		}
		
		return null;
	}

	@Override
	public void addProduct(Products product) {
		// add a product to products table
		try(Connection db_link = ConnectionUtil.getConnection()){
			String sql = "insert into products (prod_name, prod_type, prod_production_cost, prod_sale_price) "
					     + "values (?, ?, ?, ?)";			
			PreparedStatement ps = db_link.prepareStatement(sql);
			
			ps.setString(1, product.getProd_name());
			ps.setString(2, product.getProd_type());
			ps.setDouble(3, product.getProd_production_cost());
			ps.setDouble(4, product.getProd_sale_price());
			ps.executeUpdate();
			System.out.println("Product Added");
			
		} catch (SQLException e) {
			System.out.println("Error...db connection/CustomersDao/addCustomer");
			e.printStackTrace();
		}
		
	}

	@Override
	public void deleteProduct(int id) {
		// remove a product from products table
		try(Connection db_link = ConnectionUtil.getConnection()){
			String sql = "delete from products where prod_id = ?";
			PreparedStatement ps = db_link.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
			System.out.println("Product Removed");
			
		} catch (SQLException e) {
			System.out.println("Error...db connection/ProductsDao/deleteProduct");
			e.printStackTrace();
		}	
	}

	@Override
	public void updateProdSalePrice(int id, double price) {
		// update a products sale price
		try(Connection db_link = ConnectionUtil.getConnection()){
			
			String sql = "update products set prod_sale_price = ? where prod_id = ?";
			PreparedStatement ps = db_link.prepareStatement(sql);
			ps.setDouble(1, price);
			ps.setInt(2, id);
			ps.executeUpdate();
			System.out.println("**Updated sale price**");
			
		} catch (SQLException e) {
			System.out.println("Error...ProductsDao/updateProdPrice");
			e.printStackTrace();
		}		
	}
}
