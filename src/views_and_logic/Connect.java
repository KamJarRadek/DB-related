package views_and_logic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class Connect {

	private static String dataBaseName = "library";
	private static String userName = "";
	private static String userPassword = "";
	private static String answer = "";
	private static boolean in = false;
	private static Connection con = null;
	
	//  Class.forName(driverName);
	
	public static Connection getConnection() {
			return con;
	}

	public Connect(String name, String password) {
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + dataBaseName + "?useSSL=false", userName,
					userPassword);
			in = true;
		
		} catch (Exception e) {

			in = false;
			
		}
		Connect.userName = name;
		Connect.userPassword = password;
//		Connect.query = "show tables";
		getConnection();
//		setAuthors();
//		setBooks();
		
	}

	public String showTables() {
		String query = "show tables";
		try {
			Connection conn = getConnection();
			Statement statement = conn.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				String coll = resultSet.getString(1);
				answer += "\n" + coll;
			}
		} catch (Exception e) {
		}
		return answer;
	}

	public static void executeQuery(String query, String message) {
		Connection con = getConnection();
		Statement st;
		try {
			st = con.createStatement();
			if (st.executeUpdate(query) == 1) {
				JOptionPane.showMessageDialog(null, "Data " + message + " Succefully");
			} else {

			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Incorrect data");
			System.out.println(query);
		}
	}

	public static void setCustomers(){
		ArrayList<Customer> customers = new ArrayList<>();
		Connection con = getConnection();
		Statement st;
		ResultSet rs;
		Customer c;
		
		try {
			st = con.createStatement();
			rs = st.executeQuery("select * from customer");
			while(rs.next()){
				c = new Customer(rs.getString("name"), 
						rs.getString("surname"), rs.getString("address"), 
						rs.getString("phone_no"), rs.getInt("customer_id"));
				customers.add(c);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Customer.setCustomers(customers);
		System.out.println(customers);
	}
	
	public static void setAuthors() {
		ArrayList<Author> authors = new ArrayList<>();
		Connection con = getConnection();
		Statement st;
		ResultSet resset;
		Author a;

		try {
			st = con.createStatement();
			resset = st.executeQuery("select * from author");

			while (resset.next()) {
				a = new Author(resset.getInt("author_id"), resset.getString("name"), resset.getString("surname"));
				authors.add(a);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Author.setAUTHORS(authors);
	}

	public static void setBooks() {
		ArrayList<Books> books = new ArrayList<>();
		Connection con = getConnection();
		Statement st;
		ResultSet rs;
		Books b;

		try {
			st = con.createStatement();
			rs = st.executeQuery("select concat(a.name, ' ', a.surname) author, b.title, "
					+ "b.topic, b.release_year, b.book_id, b.no_of_Items, b.description from books b, author a "
					+ "where  b.author_id = a.author_id");
			while (rs.next()) {
				b = new Books(rs.getInt("book_id"), rs.getString("title"), rs.getString("topic"),
						rs.getInt("release_year"), rs.getString("description"), rs.getInt("no_of_items"),
						rs.getString("author"));
				books.add(b);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		Books.setBooks(books);
	}
	public static void setRental() {
		ArrayList<Rental> rentals = new ArrayList<>();
		Connection con = getConnection();
		Statement st;
		ResultSet rs;
		Rental r;

		try {
			st = con.createStatement();
			rs = st.executeQuery("select r.rental_id, i.item_id, r.rental_date, r.return_date,"
					+ " r.last_update, concat(c.name, \" \", c.surname) as customer, "
					+ "concat(a.name, \" \", a.surname) as author, b.title from  rental_b r join "
					+ "customer c on c.customer_id=r.customer_id join item i on i.item_id=r.item_id	 "
					+ "join books b on b.book_id=i.book_id "
					+ "join author a on a.author_id=b.author_id");
			while (rs.next()) {
				r = new Rental(rs.getInt("rental_id"), rs.getInt("item_id"),rs.getString("customer"), rs.getString("author"), rs.getString("title"),
						rs.getString("rental_date"), rs.getString("return_date"), rs.getString("last_update"));
										 
						
				rentals.add(r);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		Rental.setRentals(rentals);
	}

	public static String findAuthor(String [] check) {
		ArrayList<Customer> customers = Customer.getCustomers();
		ArrayList<Author> aData = Author.getAUTHORS();
		String name = "";
		String surname = "";
		if (check.length > 2) {
			name = check[0];
			name += " " + check[1];
			surname = check[2];
		} else if (check.length == 2) {
			name = check[0];
			surname = check[1];
		} else {
			JOptionPane.showMessageDialog(null, "Check author name.");
		}
		
		for (int i = 0; i < aData.size(); i++) {
			if (aData.get(i).getName().equals(name) && aData.get(i).getSurname().equals(surname)) {
				return String.valueOf(aData.get(i).getId());
			}
		}

		return "There is no matching author.";
	}
	public static String findCustomer(String [] check) {
		
		ArrayList<Customer> aData = Customer.getCustomers();
		String name = "";
		String surname = "";
		if (check.length > 2) {
			name = check[0];
			name += " " + check[1];
			surname = check[2];
		} else if (check.length == 2) {
			name = check[0];
			surname = check[1];
		} else {
			JOptionPane.showMessageDialog(null, "Check author customer.");
		}
		
		for (int i = 0; i < aData.size(); i++) {
			String tempName = aData.get(i).getName();
			String tempSurname = aData.get(i).getSurname();
			
			if (tempName.equalsIgnoreCase(name) && tempSurname.equalsIgnoreCase(surname)) {
				System.out.println(String.valueOf(aData.get(i).getId()));
				return String.valueOf(aData.get(i).getId());
			}
		}
		return "There is no matching customer";
	}

	public static String findItem(String checkItem) {
		setItems();
		ArrayList<Item> items = Item.getItems();
		for (int i = 0; i < items.size(); i++) {
			String item = String.valueOf(items.get(i).getItemId());
			if (checkItem.equalsIgnoreCase(item)){
				return item;
			}
		} 
		return "There is no matching item.";
	}
	public static void setItems() {
		ArrayList<Item> items = new ArrayList<>();
		Connection con = getConnection();
		Statement st;
		ResultSet resset;
		Item a;

		try {
			st = con.createStatement();
			resset = st.executeQuery("select * from item");

			while (resset.next()) {
				a = new Item(resset.getInt("item_id"), resset.getInt("book_id"));
				items.add(a);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Item.setItems(items);
	}

	public String getAnswer() {
		return answer;
	}

	public boolean isIn() {
		return in;
	}


}
