package views_and_logic;

import java.util.ArrayList;

public class Author {

	private int id;
	private String name;
	private String surname;
	public static final String[] AuthorColumnName = { "Id", "First Name", "Last Name" };
	public static  ArrayList<Author> AUTHORS = new ArrayList<>();

	public static ArrayList<Author> getAUTHORS() {
		return AUTHORS;
	}

	public static void setAUTHORS(ArrayList<Author> aUTHORS) {
		AUTHORS = aUTHORS;
	}

	public Author(int id, String name, String surname) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
	}

	@Override
	public String toString() {
		return "Author [id=" + id + ", name=" + name + ", surname=" + surname + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

}
