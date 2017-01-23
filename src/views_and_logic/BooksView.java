package views_and_logic;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class BooksView extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txbNoOfItems;
	private JTextField txBAuthor;
	private JTextField txBDesc;
	private JTextField txBTitle;
	private JTextField txByear;
	private JTextField txBTopic;
	private JTextField txBid;
	private JTable tableBooks;
	private JTextField txBFind;

	/**
	 * Create the panel.
	 */
	public BooksView() {

		setBackground(new Color(0, 102, 102));
		setBounds(100, 100, 781, 531);
		setLayout(null);

		JLabel lblBooks = new JLabel("Books");
		lblBooks.setBounds(234, 11, 41, 17);
		lblBooks.setFont(new Font("Gisha", Font.BOLD, 14));
		add(lblBooks);

		JPanel panelFrame = new JPanel();
		panelFrame.setBackground(new Color(0, 102, 102));
		panelFrame.setBounds(10, 35, 195, 261);
		add(panelFrame);
		panelFrame.setLayout(null);

		JLabel lblAuthor_1 = new JLabel("Author");
		lblAuthor_1.setBounds(21, 5, 46, 17);
		panelFrame.add(lblAuthor_1);
		lblAuthor_1.setFont(new Font("Gisha", Font.BOLD, 14));

		JLabel lblTopic = new JLabel("Topic");
		lblTopic.setBounds(21, 67, 37, 17);
		panelFrame.add(lblTopic);
		lblTopic.setFont(new Font("Gisha", Font.BOLD, 14));

		JLabel lblYear = new JLabel("Year");
		lblYear.setBounds(21, 166, 31, 17);
		panelFrame.add(lblYear);
		lblYear.setFont(new Font("Gisha", Font.BOLD, 14));

		JLabel lbldesc = new JLabel("Description");
		lbldesc.setBounds(10, 99, 103, 17);
		panelFrame.add(lbldesc);
		lbldesc.setFont(new Font("Gisha", Font.BOLD, 14));

		JLabel lblNoOfItems = new JLabel("No of items");
		lblNoOfItems.setBounds(21, 231, 78, 17);
		panelFrame.add(lblNoOfItems);
		lblNoOfItems.setFont(new Font("Gisha", Font.BOLD, 14));

		txbNoOfItems = new JTextField();
		txbNoOfItems.setBounds(109, 230, 61, 20);
		panelFrame.add(txbNoOfItems);
		txbNoOfItems.setColumns(10);

		JLabel lblBookId = new JLabel("Book Id");
		lblBookId.setBounds(21, 203, 52, 17);
		panelFrame.add(lblBookId);
		lblBookId.setFont(new Font("Gisha", Font.BOLD, 14));

		txBAuthor = new JTextField();
		txBAuthor.setBounds(84, 4, 105, 20);
		panelFrame.add(txBAuthor);
		txBAuthor.setColumns(10);

		JLabel lblTitle = new JLabel("Title");
		lblTitle.setBounds(21, 33, 29, 17);
		panelFrame.add(lblTitle);
		lblTitle.setFont(new Font("Gisha", Font.BOLD, 14));

		txBDesc = new JTextField();
		txBDesc.setBounds(103, 99, 86, 56);
		panelFrame.add(txBDesc);
		txBDesc.setColumns(10);

		txBTitle = new JTextField();
		txBTitle.setBounds(84, 32, 105, 20);
		panelFrame.add(txBTitle);
		txBTitle.setColumns(10);

		txByear = new JTextField();
		txByear.setBounds(84, 165, 86, 20);
		panelFrame.add(txByear);
		txByear.setColumns(10);

		txBTopic = new JTextField();
		txBTopic.setBounds(84, 66, 105, 20);
		panelFrame.add(txBTopic);
		txBTopic.setColumns(10);

		txBid = new JTextField();
		txBid.setBounds(84, 196, 86, 20);
		panelFrame.add(txBid);
		txBid.setColumns(10);

		JPanel btPanel = new JPanel();
		btPanel.setBounds(215, 47, 529, 269);
		btPanel.setAutoscrolls(true);
		add(btPanel);
		btPanel.setLayout(new FlowLayout());

		tableBooks = new JTable(new DefaultTableModel(new Object[][] {}, Books.colName));
		tableBooks.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = tableBooks.getSelectedRow();
				showItem(index);
			}
		});
		tableBooks.setBounds(218, 11, 206, 168);
		tableBooks.setPreferredScrollableViewportSize(new Dimension(413, 400));
		tableBooks.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		tableBooks.setAutoCreateRowSorter(true);
		tableBooks.setAutoscrolls(true);
		tableBooks.setFillsViewportHeight(true);
		tableBooks.setCellSelectionEnabled(true);
		tableBooks.setColumnSelectionAllowed(true);
		tableBooks.setForeground(new Color(0, 0, 0));
		tableBooks.setBackground(new Color(51, 204, 255));
		btPanel.add(tableBooks.getTableHeader(), BorderLayout.NORTH);
		btPanel.add(tableBooks, BorderLayout.CENTER);

		JButton btnFindBook = new JButton("Find");
		btnFindBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String findBook = txBFind.getText();
				findByTitle(findBook);
			}
		});
		btnFindBook.setBounds(116, 307, 89, 23);
		add(btnFindBook);

		JButton btnRestBook = new JButton("C");
		btnRestBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showItem(0);
				fillBooksTable(Books.getBooks());
			}
		});
		btnRestBook.setBounds(116, 341, 89, 23);
		add(btnRestBook);

		JButton btnUpdateBook = new JButton("Update");
		btnUpdateBook.setBounds(340, 375, 89, 23);
		add(btnUpdateBook);

		JButton btnaddBook = new JButton("Add");
		btnaddBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id;
				String[] check = txBAuthor.getText().split(" ");
				String findAuthor = Connect.findAuthor(check);

				if (!findAuthor.equals("There is no matching author")) {
					id = Integer.parseInt(findAuthor);
					String query = "insert into books "
							+ "(title, author_id,topic, release_year,description,no_of_items) " + "values ( \""
							+ txBTitle.getText() + "\" , " + id + " , \"" + txBTopic.getText() + "\" , "
							+ txByear.getText() + " , \"" + txBDesc.getText() + "\", " + txbNoOfItems.getText() + " )";

					System.out.println(query);
					Connect.executeQuery(query, "insert");
				} else {
					JOptionPane.showMessageDialog(null,
							"This author doesn't existe in database. First add new author then the book.");
				}
			}
		});
		btnaddBook.setBounds(228, 375, 89, 23);
		add(btnaddBook);

		JButton btnNextBook = new JButton("Next");
		btnNextBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int current = Integer.parseInt(txBid.getText());
					if (current < Books.getBooks().size()) {
						showItem(current);
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "there is no more records.");
				}
			}
		});
		btnNextBook.setBounds(580, 375, 89, 23);
		add(btnNextBook);

		JButton btnPreBook = new JButton("Previous");
		btnPreBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int current = Integer.parseInt(txBid.getText());
					if (current > 1) {
						showItem((current - 2));
					}

				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Id can not be empty.");
				}
			}
		});
		btnPreBook.setBounds(468, 375, 89, 23);
		add(btnPreBook);

		txBFind = new JTextField();
		txBFind.setBounds(20, 308, 86, 22);
		add(txBFind);
		txBFind.setColumns(10);

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				SwingMain.showChoose();
			}
		});
		btnBack.setBounds(23, 375, 89, 23);
		add(btnBack);
	}

	protected void fillBooksTable(ArrayList<Books> books) {
		DefaultTableModel model = (DefaultTableModel) tableBooks.getModel();
		model.setRowCount(0);

		Object[] row = new Object[7];

		for (int i = 0; i < books.size(); i++) {
			row[0] = books.get(i).getBookAuthor();
			row[1] = books.get(i).getTitle();
			row[2] = books.get(i).getTopic();
			row[3] = books.get(i).getDescription();
			row[4] = books.get(i).getRelease_year();
			row[5] = books.get(i).getBook_id();
			row[6] = books.get(i).getNo_of_items();
			model.addRow(row);
		}
	}

	protected void findByTitle(String findBook) {
		ArrayList<Books> books = Books.getBooks();
		ArrayList<Books> selectedBooks = new ArrayList<>();
		for (int i = 0; i < books.size(); i++) {
			if (findBook.equals(books.get(i).getTitle())) {
				showItem(i);
				selectedBooks.add(books.get(i));
			}
			fillBooksTable(selectedBooks);
		}
	}

	protected void showItem(int index) {
		txBAuthor.setText(Books.getBooks().get(index).getBookAuthor());
		txBTitle.setText(Books.getBooks().get(index).getTitle());
		txBTopic.setText(Books.getBooks().get(index).getTopic());
		txBDesc.setText(Books.getBooks().get(index).getDescription());
		txByear.setText(String.valueOf(Books.getBooks().get(index).getRelease_year()));
		txBid.setText(String.valueOf(Books.getBooks().get(index).getBook_id()));
		txbNoOfItems.setText(String.valueOf(Books.getBooks().get(index).getNo_of_items()));

	}

}
