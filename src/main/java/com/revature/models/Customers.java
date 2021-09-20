package com.revature.models;

public class Customers {
	
	private int cust_num;
	private String f_name;
	private String l_name;
	
	public Customers() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Customers(int cust_num, String f_name, String l_name) {
		super();
		this.cust_num = cust_num;
		this.f_name = f_name;
		this.l_name = l_name;
	}

	public Customers(String f_name, String l_name) {
		super();
		this.f_name = f_name;
		this.l_name = l_name;
	}

	@Override
	public String toString() {
		return "Customers [cust_num=" + cust_num + ", f_name=" + f_name + ", l_name=" + l_name + "]";
	}

	public int getCust_num() {
		return cust_num;
	}

	public void setCust_num(int cust_num) {
		this.cust_num = cust_num;
	}

	public String getF_name() {
		return f_name;
	}

	public void setF_name(String f_name) {
		this.f_name = f_name;
	}

	public String getL_name() {
		return l_name;
	}

	public void setL_name(String l_name) {
		this.l_name = l_name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cust_num;
		result = prime * result + ((f_name == null) ? 0 : f_name.hashCode());
		result = prime * result + ((l_name == null) ? 0 : l_name.hashCode());
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
		Customers other = (Customers) obj;
		if (cust_num != other.cust_num)
			return false;
		if (f_name == null) {
			if (other.f_name != null)
				return false;
		} else if (!f_name.equals(other.f_name))
			return false;
		if (l_name == null) {
			if (other.l_name != null)
				return false;
		} else if (!l_name.equals(other.l_name))
			return false;
		return true;
	}
	
	
	

}
