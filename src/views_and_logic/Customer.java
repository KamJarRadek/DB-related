package views_and_logic;

import java.util.ArrayList;

public class Customer {
	private String name, surname, address,phone;
	private int customerId;
	private static ArrayList<Customer> customers  = new ArrayList<>();
	public static final String [] colNameCustomer = {"Id", "Name", "Surname", "Address", "Phone"};
	
	public static ArrayList<Customer> getCustomers() {
		return customers;
	}

	public static void setCustomers(ArrayList<Customer> customers) {
		Customer.customers = customers;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public String getAddress() {
		return address;
	}

	public String getPhone() {
		return phone;
	}

	public int getId() {
		return customerId;
	}

	public static String[] getColnamecustomer() {
		return colNameCustomer;
	}

	public Customer(String name, String surname, String address, String phone, int customerId) {
		super();
		this.name = name;
		this.surname = surname;
		this.address = address;
		this.phone = phone;
		this.customerId = customerId;
	}

	@Override
	public String toString() {
		return "Customer [name=" + name + ", surname=" + surname + ", address=" + address + ", phone=" + phone
				+ ", customerId=" + customerId + "]";
	}
	
}
