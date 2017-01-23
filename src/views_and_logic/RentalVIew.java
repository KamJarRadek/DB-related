package views_and_logic;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class RentalVIew extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txLastUpdate;
	private JTextField txRentalDate;
	private JTextField txId;
	private JTable tableRental;
	private JTextField txFnd;
	private JTextField txItem;
	private JTextField txReturnDate;
	private JTextField txCustomer;
	private JTextField txTitle;
	private JTextField txAuthor;
	/**
	 * Create the panel.
	 */
	public RentalVIew() {

		setBackground(new Color(0, 102, 102));
		setBounds(100, 100, 781, 531);
		setLayout(null);
		setVisible(true);
		
		JLabel lblId = new JLabel("Id");
		lblId.setFont(new Font("Gisha", Font.BOLD, 15));
		lblId.setBounds(10, 330, 46, 14);
		add(lblId);

		JLabel lblName = new JLabel("Rental Date");
		lblName.setFont(new Font("Gisha", Font.BOLD, 15));
		lblName.setBounds(10, 358, 93, 14);
		add(lblName);

		JLabel lblSurname = new JLabel("Last Update");
		lblSurname.setFont(new Font("Gisha", Font.BOLD, 15));
		lblSurname.setBounds(10, 387, 93, 14);
		add(lblSurname);

		JLabel lblRental = new JLabel("Rental");
		lblRental.setFont(new Font("Gisha", Font.BOLD, 15));
		lblRental.setBounds(342, 16, 93, 14);
		add(lblRental);

		txId = new JTextField();
		txId.setBounds(110, 328, 128, 20);
		add(txId);
		txId.setColumns(10);

		txRentalDate = new JTextField();
		txRentalDate.setColumns(10);
		txRentalDate.setBounds(110, 356, 128, 20);
		add(txRentalDate);

		txLastUpdate = new JTextField();
		txLastUpdate.setColumns(10);
		txLastUpdate.setBounds(110, 385, 128, 20);
		add(txLastUpdate);
		
		tableRental = new JTable(new DefaultTableModel(new Object[][] {}, Rental.colNameRental));
		tableRental.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int index = tableRental.getSelectedRow();
				showItem(index);
			}
		});
		tableRental.setPreferredScrollableViewportSize(new Dimension(413, 400));
		tableRental.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		tableRental.setAutoCreateRowSorter(true);
		tableRental.setAutoscrolls(true);
		tableRental.setFillsViewportHeight(true);
		tableRental.setCellSelectionEnabled(true);
		tableRental.setColumnSelectionAllowed(true);
		tableRental.setForeground(new Color(0, 0, 0));
		tableRental.setBackground(new Color(51, 204, 255));
		tableRental.setBounds(218, 11, 206, 168);

		JPanel panel = new JPanel();
		panel.setAutoscrolls(true);
		panel.setBounds(138, 41, 633, 266);
		panel.setVisible(true);
		add(panel);
		panel.add(tableRental.getTableHeader(), BorderLayout.NORTH);
		panel.add(tableRental, BorderLayout.CENTER);

		JButton btnPre = new JButton("Previous");
		btnPre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int current = Integer.parseInt(txId.getText());
					if (current > 1) {
						showItem((current - 2));
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Id can not be empty.");
				}
			}
		});
		btnPre.setBounds(500, 462, 89, 23);
		add(btnPre);


		JButton btnNext = new JButton("Next");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					int current = Integer.parseInt(txId.getText());
					if (current < Author.getAUTHORS().size()) {
						showItem(current);
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "There is no more authors.");
				}
			}
		});
		btnNext.setBounds(599, 462, 89, 23);
		add(btnNext);

		JButton btnInsert = new JButton("Insert");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String [] checkCustomer = txCustomer.getText().split(" ");
				String customerId = Connect.findCustomer(checkCustomer);
				String checkItem = txItem.getText();
				String itemId = Connect.findItem(checkItem);
				
				if (!(itemId.equals("There is no matching item."))&&!(customerId.equals("There is no matching author."))){
				
				String query = "Insert into rental_b (rental_date,item_id, customer_id) values  (current_timestamp() ," + itemId+ " , "
						+ customerId + " )";
				Connect.executeQuery(query, "Inserted");
				}
			}
		});
		btnInsert.setBounds(258, 462, 89, 23);
		add(btnInsert);

		JButton btnUpdate = new JButton("Return this item");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String query = "update rental_b set return_date = current_timestamp() where rental_id=1";
				Connect.executeQuery(query, "Updated");
			}
		});
		btnUpdate.setBounds(368, 462, 89, 23);
		add(btnUpdate);

		txFnd = new JTextField();
		txFnd.setBounds(532, 340, 99, 20);
		add(txFnd);
		txFnd.setColumns(10);

		JLabel lblFindSurname = new JLabel("Find Customer Name Surname");
		lblFindSurname.setFont(new Font("Gisha", Font.BOLD, 14));
		lblFindSurname.setBounds(537, 315, 234, 14);
		add(lblFindSurname);

		JButton btnFind = new JButton("Find");
		btnFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//String [] findSurname = txFnd.getText().split(" ");
				//findBySurname(findSurname[0], findSurname[1]);
				findBySurname(txFnd.getText());
			}
		});
		btnFind.setBounds(662, 339, 89, 23);
		add(btnFind);

		JButton btnClear = new JButton("C");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fillRentalTable(Rental.getRentals());
				showItem(0);
			}
		});
		btnClear.setBounds(662, 367, 89, 23);
		add(btnClear);

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				SwingMain.showChoose();
			}
		});
		btnBack.setBounds(98, 462, 89, 23);
		add(btnBack);
		
		JLabel lblItem = new JLabel("Item");
		lblItem.setFont(new Font("Gisha", Font.BOLD, 15));
		lblItem.setBounds(271, 326, 46, 14);
		add(lblItem);
		
		JLabel lblReturnDate = new JLabel("Return Date");
		lblReturnDate.setFont(new Font("Gisha", Font.BOLD, 15));
		lblReturnDate.setBounds(271, 354, 93, 14);
		add(lblReturnDate);
		
		JLabel lblCustomer = new JLabel("Customer");
		lblCustomer.setFont(new Font("Gisha", Font.BOLD, 15));
		lblCustomer.setBounds(271, 383, 93, 14);
		add(lblCustomer);
		
		txItem = new JTextField();
		txItem.setColumns(10);
		txItem.setBounds(371, 324, 128, 20);
		add(txItem);
		
		txReturnDate = new JTextField();
		txReturnDate.setColumns(10);
		txReturnDate.setBounds(371, 352, 128, 20);
		add(txReturnDate);
		
		txCustomer = new JTextField();
		txCustomer.setColumns(10);
		txCustomer.setBounds(371, 381, 128, 20);
		add(txCustomer);
		
		JLabel lblAuthor = new JLabel("Author");
		lblAuthor.setFont(new Font("Gisha", Font.BOLD, 15));
		lblAuthor.setBounds(10, 417, 93, 14);
		add(lblAuthor);
		
		txTitle = new JTextField();
		txTitle.setColumns(10);
		txTitle.setBounds(371, 411, 128, 20);
		add(txTitle);
		
		JLabel lblTitle = new JLabel("Title");
		lblTitle.setFont(new Font("Gisha", Font.BOLD, 15));
		lblTitle.setBounds(271, 413, 93, 14);
		add(lblTitle);
		
		txAuthor = new JTextField();
		txAuthor.setColumns(10);
		txAuthor.setBounds(110, 415, 128, 20);
		add(txAuthor);


	}
	protected void findBySurname(String checkCustomer) {
		ArrayList<Customer> customer = Customer.getCustomers();
		ArrayList<Rental> rentals = Rental.getRentals();
		ArrayList<Rental> selected = new ArrayList<>();
		
		
		for (int i = 0; i < rentals.size(); i++) {
			String surname = customer.get(i).getSurname();
			String name = customer.get(i).getName();
			
			if (rentals.get(i).getCustomer().equalsIgnoreCase(checkCustomer)
					){
				selected.add(rentals.get(i));
				showItem(i);
				
			}
		}
			fillRentalTable(selected);
		
	}
	public void fillRentalTable(ArrayList<Rental> rentals) {
		DefaultTableModel model = (DefaultTableModel) tableRental.getModel();
		model.setRowCount(0);
		
		Object[] row = new Object[8];
		for (int i = 0; i < rentals.size(); i++) {
			row[0] = rentals.get(i).getRentalId();
			row[1] = rentals.get(i).getItemId();
			row[2] = rentals.get(i).getRentalDate();
			row[3] = rentals.get(i).getReturnDate();
			row[4] = rentals.get(i).getLastUpdate();
			row[5] = rentals.get(i).getCustomer();
			row[6] = rentals.get(i).getAuthor();
			row[7] = rentals.get(i).getTitle();
					
			model.addRow(row);
		}
	}
	protected void showItem(int index) {
		
		txId.setText(String.valueOf(Rental.getRentals().get(index).getRentalId()));
		txRentalDate.setText(Rental.getRentals().get(index).getRentalDate());
		txLastUpdate.setText(Rental.getRentals().get(index).getLastUpdate());
		txAuthor.setText(Rental.getRentals().get(index).getAuthor());
		txTitle.setText(Rental.getRentals().get(index).getTitle());
		txCustomer.setText(Rental.getRentals().get(index).getCustomer());
		txReturnDate.setText(Rental.getRentals().get(index).getReturnDate());
		txItem.setText(String.valueOf(Rental.getRentals().get(index).getItemId()));
	}
}
