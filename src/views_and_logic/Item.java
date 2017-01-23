package views_and_logic;

import java.util.ArrayList;

public class Item {
	private int itemId, bookId;
	public static ArrayList<Item> items = new ArrayList<>();
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public static ArrayList<Item> getItems() {
		return items;
	}
	public static void setItems(ArrayList<Item> items) {
		Item.items = items;
	}
	public Item(int itemId, int bookId) {
		super();
		this.itemId = itemId;
		this.bookId = bookId;
	}
	
}
