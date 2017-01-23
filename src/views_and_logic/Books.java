package views_and_logic;

import java.util.ArrayList;

public class Books {

	private int book_id;
	
	private String title;
	private String topic;
	private int release_year;
	private String description;
	private int no_of_items;
	private String bookAuthor;
	public static final String [] colName = {"Author", "Title", "Topic", "Description", "Year", "Book Id", "No of items"};
	private static ArrayList<Books> books = new ArrayList<>();
	
 	
	public static ArrayList<Books> getBooks() {
		return books;
	}

	public static void setBooks(ArrayList<Books> books) {
		Books.books = books;
	}

	public Books(int book_id, String title, String topic, int release_year, String description, int no_of_items,
			String bookAuthor) {
		super();
		this.book_id = book_id;
		this.title = title;
		this.topic = topic;
		this.release_year = release_year;
		this.description = description;
		this.no_of_items = no_of_items;
		this.bookAuthor = bookAuthor;
	}

	public int getBook_id() {
		return book_id;
	}

	public String getTitle() {
		return title;
	}

	public String getTopic() {
		return topic;
	}

	public int getRelease_year() {
		return release_year;
	}

	public String getDescription() {
		return description;
	}

	public int getNo_of_items() {
		return no_of_items;
	}

	public String getBookAuthor() {
		return bookAuthor;
	}
		
}
