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

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class CustomerView extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txCustomerId;
	private JTextField txCustomerName;
	private JTextField txCustomerSurname;
	private JTextField txCustomerAddress;
	private JTextField txCustomerPhone;
	private JTable table;
	private JTextField textFind;

	

	/**
	 * Create the panel.
	 */
	public CustomerView() {
		setBackground(new Color(0, 102, 102));
		setBounds(100, 100, 781, 531);
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("CUSTOMER");
		lblNewLabel.setBounds(337, 11, 74, 17);
		lblNewLabel.setFont(new Font("Gisha", Font.BOLD, 14));
		add(lblNewLabel);
		
		JLabel lblCustomerId = new JLabel("Customer id");
		lblCustomerId.setBounds(10, 44, 124, 17);
		lblCustomerId.setFont(new Font("Gisha", Font.BOLD, 14));
		add(lblCustomerId);
		
		txCustomerId = new JTextField();
		txCustomerId.setBounds(108, 43, 167, 20);
		add(txCustomerId);
		txCustomerId.setColumns(10);
		
		JLabel CustomerNAme = new JLabel("Name");
		CustomerNAme.setFont(new Font("Gisha", Font.BOLD, 14));
		CustomerNAme.setBounds(10, 73, 124, 17);
		add(CustomerNAme);
		
		txCustomerName = new JTextField();
		txCustomerName.setColumns(10);
		txCustomerName.setBounds(108, 72, 167, 20);
		add(txCustomerName);
		
		JLabel lblSurname = new JLabel("Surname");
		lblSurname.setFont(new Font("Gisha", Font.BOLD, 14));
		lblSurname.setBounds(10, 102, 124, 17);
		add(lblSurname);
		
		txCustomerSurname = new JTextField();
		txCustomerSurname.setColumns(10);
		txCustomerSurname.setBounds(108, 101, 167, 20);
		add(txCustomerSurname);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setFont(new Font("Gisha", Font.BOLD, 14));
		lblAddress.setBounds(10, 127, 124, 17);
		add(lblAddress);
		
		txCustomerAddress = new JTextField();
		txCustomerAddress.setColumns(10);
		txCustomerAddress.setBounds(108, 126, 167, 20);
		add(txCustomerAddress);
		
		JLabel lblPhone = new JLabel("Phone");
		lblPhone.setFont(new Font("Gisha", Font.BOLD, 14));
		lblPhone.setBounds(10, 156, 124, 17);
		add(lblPhone);
		
		txCustomerPhone = new JTextField();
		txCustomerPhone.setColumns(10);
		txCustomerPhone.setBounds(108, 155, 167, 20);
		add(txCustomerPhone);
		
		JButton button = new JButton("Previous");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int current = Integer.valueOf(txCustomerId.getText());
				if (current>1){
					showItemCustomer(current-2);
				}
			}
		});
		button.setBounds(504, 434, 89, 23);
		add(button);
		
		JButton button_1 = new JButton("Next");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int current = Integer.valueOf(txCustomerId.getText());
				if (current<Customer.getCustomers().size())
				{
					showItemCustomer(current++);
				}
				
			}
		});
		button_1.setBounds(603, 434, 89, 23);
		add(button_1);
		
		JButton button_2 = new JButton("Insert");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			String query = "insert into customer (name, surname, address, phone_no) values (\""+txCustomerName.getText()+"\" ,\" "+
txCustomerSurname.getText()+ "\" ,\" "+txCustomerAddress.getText()+ "\" , "+txCustomerPhone.getText()+")";
				Connect.executeQuery(query, "insert");
			}
		});
		button_2.setBounds(262, 434, 89, 23);
		add(button_2);
		
		JButton button_3 = new JButton("Update");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String query = "update customer set name = '" + txCustomerName.getText() + "', surname = '" + txCustomerSurname.getText()
				+ "' , address = ' " + txCustomerAddress.getText() +"', phone_no = " + txCustomerPhone.getText()
				+ " where customer_id =" + txCustomerId.getText();
		Connect.executeQuery(query, "Updated");
			}
		});
		button_3.setBounds(372, 434, 89, 23);
		add(button_3);
		
		JButton button_4 = new JButton("Back");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				SwingMain.showChoose();
			}
		});
		button_4.setBounds(102, 434, 89, 23);
		add(button_4);
		
		JPanel panel = new JPanel();
		panel.setBounds(326, 59, 412, 345);
		add(panel);
		
		table = new JTable(new DefaultTableModel(new Object[][] {} , Customer.colNameCustomer));
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			int index = table.getSelectedRow();
			showItemCustomer(index);
			}
		});
		table.setPreferredScrollableViewportSize(new Dimension(413, 400));
		table.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		table.setAutoCreateRowSorter(true);
		table.setAutoscrolls(true);
		table.setFillsViewportHeight(true);
		table.setCellSelectionEnabled(true);
		table.setColumnSelectionAllowed(true);
		table.setForeground(new Color(0, 0, 0));
		table.setBackground(new Color(51, 204, 255));
		panel.add(table.getTableHeader(), BorderLayout.NORTH);
		panel.add(table, BorderLayout.CENTER);
		
		JButton btnFind = new JButton("Find");
		btnFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String surname = textFind.getText();
				findCustomerBySurname (surname);
			}
		});
		btnFind.setBounds(186, 266, 89, 23);
		add(btnFind);
		
		JButton btnC = new JButton("C");
		btnC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			fillCustomerTable(Customer.getCustomers());
			}
		});
		btnC.setBounds(186, 300, 89, 23);
		add(btnC);
		
		textFind = new JTextField();
		textFind.setBounds(26, 267, 150, 20);
		add(textFind);
		textFind.setColumns(10);
		
		JLabel label = new JLabel("Find Customer by surname");
		label.setFont(new Font("Gisha", Font.BOLD, 14));
		label.setBounds(26, 239, 193, 17);
		add(label);

		
	}
	protected void findCustomerBySurname(String surname) {
		ArrayList<Customer> customers = Customer.getCustomers();
		ArrayList<Customer> selected = new ArrayList<>();
		for (int i = 0; i < customers.size(); i++) {
			if(surname.equalsIgnoreCase(customers.get(i).getSurname())){
				selected.add(customers.get(i));
				
			}	fillCustomerTable(selected);
			}
		
	}
	public void showItemCustomer(int index) {
		
		txCustomerId.setText(String.valueOf(Customer.getCustomers().get(index).getId()));
		txCustomerName.setText(Customer.getCustomers().get(index).getName());
		txCustomerSurname.setText(Customer.getCustomers().get(index).getSurname());
		txCustomerAddress.setText(Customer.getCustomers().get(index).getAddress());
		txCustomerPhone.setText(Customer.getCustomers().get(index).getPhone());
	
	}
	public void fillCustomerTable(ArrayList<Customer> cust){
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);
		Object[] row = new Object[5];
		
		for (int i = 0; i < cust.size(); i++) {
			row[0] = cust.get(i).getId();
			row[1] = cust.get(i).getName();
			row[2] = cust.get(i).getSurname();
			row[3] = cust.get(i).getAddress();
			row[4] = cust.get(i).getPhone();
			model.addRow(row);
					
		}
	}
}
