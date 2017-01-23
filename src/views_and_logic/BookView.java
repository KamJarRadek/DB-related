package views_and_logic;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JPanel;

public class BookView extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public BookView() {
		setBackground(new Color(0, 102, 102));
		setBounds(100, 100, 781, 531);
		setLayout(null);
		}
	
	protected void findByTitle(String findBook) {
		ArrayList<Books> books = Books.getBooks();
		ArrayList<Books> selectedBooks = new ArrayList<>();
		for (int i = 0; i < books.size(); i++) {
			if (findBook.equals(books.get(i).getTitle())) {
				showItem(i, "bo");
				selectedBooks.add(books.get(i));
			}
			fillBooksTable(selectedBooks);
		}
	}

	private void showItem(int i, String string) {
		// TODO Auto-generated method stub
		
	}

	private void fillBooksTable(ArrayList<Books> selectedBooks) {
		// TODO Auto-generated method stub
		
	}


}
