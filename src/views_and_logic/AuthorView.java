package views_and_logic;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Label;
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
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.table.DefaultTableModel;

public class AuthorView extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txSurname;
	private JTextField txName;
	private JTextField txId;
	private JTable tableAuthor;
	private JTextField txFnd;
	/**
	 * @wbp.nonvisual location=115,-18
	 */
	/**
	 * Create the panel.
	 */
	public AuthorView() {
	
		setBackground(new Color(0, 102, 102));
		setBounds(100, 100, 781, 531);
		setLayout(null);
		setVisible(true);
		
		JLabel lblId = new JLabel("Id");
		lblId.setFont(new Font("Gisha", Font.BOLD, 15));
		lblId.setBounds(22, 64, 46, 14);
		add(lblId);

		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Gisha", Font.BOLD, 15));
		lblName.setBounds(22, 92, 71, 14);
		add(lblName);

		JLabel lblSurname = new JLabel("Surname");
		lblSurname.setFont(new Font("Gisha", Font.BOLD, 15));
		lblSurname.setBounds(22, 121, 93, 14);
		add(lblSurname);

		JLabel lblAuthor = new JLabel("Author");
		lblAuthor.setFont(new Font("Gisha", Font.BOLD, 15));
		lblAuthor.setBounds(128, 11, 93, 14);
		add(lblAuthor);

		txId = new JTextField();
		txId.setBounds(122, 62, 128, 20);
		add(txId);
		txId.setColumns(10);

		txName = new JTextField();
		txName.setColumns(10);
		txName.setBounds(122, 90, 128, 20);
		add(txName);

		txSurname = new JTextField();
		txSurname.setColumns(10);
		txSurname.setBounds(122, 119, 128, 20);
		add(txSurname);
		
		tableAuthor = new JTable(new DefaultTableModel(new Object[][] {}, Author.AuthorColumnName));
		tableAuthor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int index = tableAuthor.getSelectedRow();
				showItem(index);
			}
		});
		tableAuthor.setPreferredScrollableViewportSize(new Dimension(413, 400));
		tableAuthor.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		tableAuthor.setAutoCreateRowSorter(true);
		tableAuthor.setAutoscrolls(true);
		tableAuthor.setFillsViewportHeight(true);
		tableAuthor.setCellSelectionEnabled(true);
		tableAuthor.setColumnSelectionAllowed(true);
		tableAuthor.setForeground(new Color(0, 0, 0));
		tableAuthor.setBackground(new Color(51, 204, 255));
		tableAuthor.setBounds(218, 11, 206, 168);

		JPanel panel = new JPanel();
		panel.setAutoscrolls(true);
		panel.setBounds(333, 25, 388, 312);
		panel.setVisible(true);
		add(panel);
		panel.add(tableAuthor.getTableHeader(), BorderLayout.NORTH);
		panel.add(tableAuthor, BorderLayout.CENTER);

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
		btnPre.setBounds(448, 370, 89, 23);
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
		btnNext.setBounds(547, 370, 89, 23);
		add(btnNext);

		JButton btnInsert = new JButton("Insert");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String query = "Insert into author  (name, surname)  values ('" + txName.getText() + "','"
						+ txSurname.getText() + "')";
				Connect.executeQuery(query, "Inserted");

			}
		});
		btnInsert.setBounds(206, 370, 89, 23);
		add(btnInsert);

		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String query = "update author set name = '" + txName.getText() + "', surname = '" + txSurname.getText()
						+ "' where author_id =" + txId.getText();
				Connect.executeQuery(query, "Updated");
			}
		});
		btnUpdate.setBounds(316, 370, 89, 23);
		add(btnUpdate);

		txFnd = new JTextField();
		txFnd.setBounds(46, 238, 99, 20);
		add(txFnd);
		txFnd.setColumns(10);

		JLabel lblFindSurname = new JLabel("Find Surname");
		lblFindSurname.setFont(new Font("Gisha", Font.BOLD, 14));
		lblFindSurname.setBounds(68, 210, 93, 14);
		add(lblFindSurname);

		JButton btnFind = new JButton("Find");
		btnFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String findSurname = txFnd.getText();
				findBySurname(findSurname);

			}
		});
		btnFind.setBounds(176, 237, 89, 23);
		add(btnFind);

		JButton btnClear = new JButton("C");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fillAuthorTable(Author.getAUTHORS());
				showItem(0);
			}
		});
		btnClear.setBounds(176, 285, 86, 23);
		add(btnClear);

		JButton btnBack_1 = new JButton("Back");
		btnBack_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				SwingMain.showChoose();
			}
		});
		btnBack_1.setBounds(46, 370, 89, 23);
		add(btnBack_1);


	}
	protected void findBySurname(String findSurname) {
		ArrayList<Author> authors = Author.getAUTHORS();
		ArrayList<Author> selected = new ArrayList<>();
		
		for (int i = 0; i < authors.size(); i++) {
			if (findSurname.equalsIgnoreCase(authors.get(i).getSurname())) {
				selected.add(authors.get(i));
				showItem(i);
			}
			fillAuthorTable(selected);
		}
	}
	public void fillAuthorTable(ArrayList<Author> authors) {
		DefaultTableModel model = (DefaultTableModel) tableAuthor.getModel();
		model.setRowCount(0);
		
		Object[] row = new Object[3];
		for (int i = 0; i < authors.size(); i++) {
			row[0] = authors.get(i).getId();
			row[1] = authors.get(i).getName();
			row[2] = authors.get(i).getSurname();
			model.addRow(row);
		}
	}
	protected void showItem(int index) {
		txId.setText(String.valueOf(Author.getAUTHORS().get(index).getId()));
		txName.setText(Author.AUTHORS.get(index).getName());
		txSurname.setText(Author.getAUTHORS().get(index).getSurname());
	}
}
