package com.revature.dao;

import java.util.List;

import com.revature.models.Products;

public interface ProductsDaoInterface {
	
	public List<Products> getProducts();
	
	public List<Products> getProductByPrice(double price);
	
	public void addProduct(Products product);
	
	public void deleteProduct(int id);
	
	public void updateProdSalePrice(int id, double price);

}
