package com.revature.models;

public class Products {
	
	private int prod_id;
	private String prod_name;
	private String prod_type;
	private double prod_production_cost;
	private double prod_sale_price;
	
	
	public Products() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Products(int prod_id, String prod_name, String prod_type, double prod_production_cost,
			double prod_sale_price) {
		super();
		this.prod_id = prod_id;
		this.prod_name = prod_name;
		this.prod_type = prod_type;
		this.prod_production_cost = prod_production_cost;
		this.prod_sale_price = prod_sale_price;
	}


	public Products(String prod_name, String prod_type, double prod_production_cost, double prod_sale_price) {
		super();
		this.prod_name = prod_name;
		this.prod_type = prod_type;
		this.prod_production_cost = prod_production_cost;
		this.prod_sale_price = prod_sale_price;
	}


	@Override
	public String toString() {
		return "Products [prod_id=" + prod_id + ", prod_name=" + prod_name + ", prod_type=" + prod_type
				+ ", prod_production_cost=" + prod_production_cost + ", prod_sale_price=" + prod_sale_price + "]";
	}


	public int getProd_id() {
		return prod_id;
	}


	public void setProd_id(int prod_id) {
		this.prod_id = prod_id;
	}


	public String getProd_name() {
		return prod_name;
	}


	public void setProd_name(String prod_name) {
		this.prod_name = prod_name;
	}


	public String getProd_type() {
		return prod_type;
	}


	public void setProd_type(String prod_type) {
		this.prod_type = prod_type;
	}


	public double getProd_production_cost() {
		return prod_production_cost;
	}


	public void setProd_production_cost(double prod_production_cost) {
		this.prod_production_cost = prod_production_cost;
	}


	public double getProd_sale_price() {
		return prod_sale_price;
	}


	public void setProd_sale_price(double prod_sale_price) {
		this.prod_sale_price = prod_sale_price;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + prod_id;
		result = prime * result + ((prod_name == null) ? 0 : prod_name.hashCode());
		long temp;
		temp = Double.doubleToLongBits(prod_production_cost);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(prod_sale_price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((prod_type == null) ? 0 : prod_type.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Products other = (Products) obj;
		if (prod_id != other.prod_id)
			return false;
		if (prod_name == null) {
			if (other.prod_name != null)
				return false;
		} else if (!prod_name.equals(other.prod_name))
			return false;
		if (Double.doubleToLongBits(prod_production_cost) != Double.doubleToLongBits(other.prod_production_cost))
			return false;
		if (Double.doubleToLongBits(prod_sale_price) != Double.doubleToLongBits(other.prod_sale_price))
			return false;
		if (prod_type == null) {
			if (other.prod_type != null)
				return false;
		} else if (!prod_type.equals(other.prod_type))
			return false;
		return true;
	}
	
	

}
