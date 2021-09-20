package com.revature.models;

public class Orders {
	
	private int order_num;
	private int prod_id_fk;
	private int cust_num_fk;
	private double sum = 0;
	
	public Orders() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Orders(int order_num, int prod_id_fk, int cust_num_fk) {
		super();
		this.order_num = order_num;
		this.prod_id_fk = prod_id_fk;
		this.cust_num_fk = cust_num_fk;
	}

	public Orders(int prod_id_fk, int cust_num_fk) {
		super();
		this.prod_id_fk = prod_id_fk;
		this.cust_num_fk = cust_num_fk;
	}

	public Orders(double dbl) {
		// TODO Auto-generated constructor stub
		this.sum = dbl;
	}

	public double getSum() {
		return sum;
	}

	public void setSum(double sum) {
		this.sum = sum;
	}

	@Override
	public String toString() {
		return "Orders [order_num=" + order_num + ", prod_id_fk=" + prod_id_fk + ", cust_num_fk=" + cust_num_fk + "]";
	}

	public int getOrder_num() {
		return order_num;
	}

	public void setOrder_num(int order_num) {
		this.order_num = order_num;
	}

	public int getProd_id_fk() {
		return prod_id_fk;
	}

	public void setProd_id_fk(int prod_id_fk) {
		this.prod_id_fk = prod_id_fk;
	}

	public int getCust_num_fk() {
		return cust_num_fk;
	}

	public void setCust_num_fk(int cust_num_fk) {
		this.cust_num_fk = cust_num_fk;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cust_num_fk;
		result = prime * result + order_num;
		result = prime * result + prod_id_fk;
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
		Orders other = (Orders) obj;
		if (cust_num_fk != other.cust_num_fk)
			return false;
		if (order_num != other.order_num)
			return false;
		if (prod_id_fk != other.prod_id_fk)
			return false;
		return true;
	}
	
	
}
