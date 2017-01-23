package views_and_logic;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class SwingMain extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField userNameTx;
	private JPasswordField userePassword;
	private static JPanel choose;
	private JTextArea display;
	private Connect connect = null;
	private CustomerView customer;
	private AuthorView authorView;
	private BooksView booksView;
	private RentalVIew rental;

	/**
	 * Create the frame.
	 */
	public SwingMain() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 781, 531);
		getContentPane().setLayout(new CardLayout(0, 0));
		ImageIcon iconMain = new ImageIcon("icons\\library.png");
		setIconImage(iconMain.getImage());
		

		JPanel login = new JPanel();
		login.setBackground(new Color(0, 102, 102));
		getContentPane().add(login, "name_190987116937850");
		login.setLayout(null);
		login.setVisible(true);

		JLabel lblNewLabel = new JLabel("User name");
		lblNewLabel.setBounds(269, 77, 86, 14);
		login.add(lblNewLabel);

		userNameTx = new JTextField();
		userNameTx.setText("root");
		userNameTx.setBounds(381, 74, 86, 20);
		login.add(userNameTx);
		userNameTx.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setBounds(269, 102, 86, 23);
		login.add(lblNewLabel_1);

		userePassword = new JPasswordField("root");
		userePassword.setBounds(381, 105, 86, 20);
		login.add(userePassword);

		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				String user = userNameTx.getText();
				String password = new String (userePassword.getPassword());
				connect = new Connect(user, password);
				if (connect.isIn()) {
					login.setVisible(false);
					choose.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "Try again!");
				}
			}
		});
		btnNewButton.setBounds(334, 157, 89, 23);
		login.add(btnNewButton);

		choose = new JPanel();
		choose.setBackground(new Color(0, 102, 102));
		getContentPane().add(choose, "name_190990045437001");
		choose.setLayout(new FlowLayout());
		choose.setVisible(false);

		JButton btnShowTables = new JButton("Show Tables");
		btnShowTables.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				display.setText(connect.showTables());
			}
		});
		btnShowTables.setBounds(1, 0, 108, 262);
		choose.add(btnShowTables);
		
		JButton authors = new JButton("Authors");
		authors.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connect.setAuthors();
				choose.setVisible(false);
				authorView = new AuthorView();
				getContentPane().add(authorView);
				authorView.fillAuthorTable(Author.getAUTHORS());
				authorView.showItem(0);
				authorView.setVisible(true);
				}
		});
		choose.add(authors);

		JButton btnBooks = new JButton("Books");
		btnBooks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connect.setBooks();
				choose.setVisible(false);
				booksView = new BooksView();
				getContentPane().add(booksView);
				booksView.fillBooksTable(Books.getBooks());
				booksView.showItem(0);
				booksView.setVisible(true);
			}
		});
		choose.add(btnBooks);
		JButton btnCustomer = new JButton("Customer");
		btnCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connect.setCustomers();
				choose.setVisible(false);
				customer = new CustomerView();
				getContentPane().add(customer);
				customer.setVisible(true);
				customer.showItemCustomer(0);
				customer.fillCustomerTable(Customer.getCustomers());
				
			}
		});
		choose.add(btnCustomer);
		
		JButton btnRental = new JButton("Rental");
		btnRental.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				choose.setVisible(false);
				Connect.setItems();
				Connect.setRental();
				Connect.setCustomers();
				rental = new RentalVIew();
				getContentPane().add(rental);
				rental.setVisible(true);
				rental.showItem(0);
				rental.fillRentalTable(Rental.getRentals());
				
				
			}
		});
		choose.add(btnRental);

		display = new JTextArea(10, 40);
		display.setWrapStyleWord(true);
		display.setLineWrap(true);
		display.setBounds(325, 0, 108, 262);
		choose.add(display);

	}
	public static void showChoose(){
		choose.setVisible(true);
	}
}