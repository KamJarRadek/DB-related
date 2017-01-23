package views_and_logic;

import java.util.ArrayList;

public class Rental {

	private int rentalId;
	private String customer, author, title, rentalDate, returnDate, lastUpdate;
	private int itemId;
	public static final String [] colNameRental = {"Id", "Item","Rental Date", "Return Date", "Last Update", "Customer", "Author", "Title"};
	public int getRentalId() {
		return rentalId;
	}
	public void setRentalId(int rentalId) {
		this.rentalId = rentalId;
	}
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getRentalDate() {
		return rentalDate;
	}
	public void setRentalDate(String rentalDate) {
		this.rentalDate = rentalDate;
	}
	public String getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}
	public String getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	public static String[] getColnamerental() {
		return colNameRental;
	}
	public static ArrayList<Rental> getRentals() {
		return rentals;
	}
	private static ArrayList<Rental> rentals = new ArrayList<>();
	
	public static void setRentals(ArrayList<Rental> rentals) {
		Rental.rentals = rentals;
	}
	public Rental(int rentalId, int itemId, String customer, String author, String title, String rentalDate, String returnDate,
			String lastUpdate) {
		super();
		this.rentalId = rentalId;
		this.itemId = itemId;
		this.customer = customer;
		this.author = author;
		this.title = title;
		this.rentalDate = rentalDate;
		this.returnDate = returnDate;
		this.lastUpdate = lastUpdate;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	
	
}
