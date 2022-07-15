import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.border.TitledBorder;

import com.mysql.jdbc.Connection;

import net.proteanit.sql.DbUtils;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class BookCRUD extends JFrame {

	private JPanel contentPane;
	private JTextField tname;
	private JTextField tedition;
	private JTextField tprice;
	private JTextField tid;
	private JTable table;
	private JTable table_1;

	public String bname, bedition, bprice;
	String edition,price,bid;
	PreparedStatement pst;
	ResultSet rs;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookCRUD frame = new BookCRUD();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	Connection con = null;

	public void table_load() {
		try {
			pst = con.prepareStatement("select * from book");
			rs = pst.executeQuery();
			// System.out.print(rs);
			table_1.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public BookCRUD() {

		con = (Connection) connection.dbconnect();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1278, 735);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("BOOK MANAGEMENT SYSTEM");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblNewLabel.setBounds(443, 26, 467, 71);
		contentPane.add(lblNewLabel);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "REGESTRATION", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panel.setBounds(28, 110, 449, 327);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("BOOK NAME");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1.setBounds(12, 73, 130, 39);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("EDITION");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1_1.setBounds(12, 165, 130, 39);
		panel.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_2 = new JLabel("PRICE");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1_2.setBounds(12, 267, 130, 39);
		panel.add(lblNewLabel_1_2);

		tname = new JTextField();
		tname.setFont(new Font("Tahoma", Font.BOLD, 18));
		tname.setBounds(187, 73, 238, 39);
		panel.add(tname);
		tname.setColumns(10);

		tedition = new JTextField();
		tedition.setFont(new Font("Tahoma", Font.BOLD, 18));
		tedition.setColumns(10);
		tedition.setBounds(187, 165, 238, 39);
		panel.add(tedition);

		tprice = new JTextField();
		tprice.setFont(new Font("Tahoma", Font.BOLD, 18));
		tprice.setColumns(10);
		tprice.setBounds(187, 267, 238, 39);
		panel.add(tprice);

		JButton btnNewButton = new JButton("SAVE");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				bname = tname.getText();
				bedition = tedition.getText();
				bprice = tprice.getText();

				try {
					pst = con.prepareStatement("Insert into book(name, edition, price)values(?,?,?)");
					pst.setString(1, bname);
					pst.setString(2, bedition);
					pst.setString(3, bprice);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record Inserted !!!!");
					table_load();
					tname.setText("");
					tedition.setText("");
					tprice.setText("");
					tname.requestFocus();

				}

				catch (Exception e) {
					System.out.print(e);
				}

			}

		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 22));
		btnNewButton.setBounds(28, 470, 114, 53);
		contentPane.add(btnNewButton);

		JButton btnExit = new JButton("EXIT");
		btnExit.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 22));
		btnExit.setBounds(198, 470, 114, 53);
		contentPane.add(btnExit);

		JButton btnClear = new JButton("CLEAR");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tname.setText("");
				tedition.setText("");
				tprice.setText("");
			}
		});
		btnClear.setFont(new Font("Tahoma", Font.BOLD, 22));
		btnClear.setBounds(363, 470, 114, 53);
		contentPane.add(btnClear);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "SEARCH", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panel_1.setBounds(28, 555, 449, 97);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel_1_1_1 = new JLabel("BOOK ID");
		lblNewLabel_1_1_1.setBounds(24, 49, 137, 37);
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		panel_1.add(lblNewLabel_1_1_1);

		tid = new JTextField();
		tid.addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent e) {

				try {

					String id = tid.getText();

					pst = con.prepareStatement("select name,edition,price from book where id = ?");
					pst.setString(1, id);
					ResultSet rs = pst.executeQuery();

					if (rs.next() == true) {

						String name = rs.getString(1);
						String edition = rs.getString(2);
						String price = rs.getString(3);

						tname.setText(name);
						tedition.setText(edition);
						tprice.setText(price);

					} else {
						tname.setText("");
						tedition.setText("");
						tprice.setText("");

					}

				}

				catch (SQLException ex) {

				}

			}

		});
		tid.setFont(new Font("Tahoma", Font.BOLD, 18));
		tid.setColumns(10);
		tid.setBounds(181, 47, 238, 39);
		panel_1.add(tid);

		table = new JTable();
		table.setBounds(540, 619, 661, -488);
		contentPane.add(table);

		table_1 = new JTable();
		table_1.setBounds(550, 110, 629, 440);
		contentPane.add(table_1);

		JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
														
					
					bname = tname.getText();
					edition = tedition.getText();
					price = tprice.getText();
					bid  = tid.getText();
					
					 try {
							pst = con.prepareStatement("update book set name= ?,edition=?,price=? where id =?");
							pst.setString(1, bname);
				            pst.setString(2, edition);
				            pst.setString(3, price);
				            pst.setString(4, bid);
				            pst.executeUpdate();
				            JOptionPane.showMessageDialog(null, "Record Update!!!!!");
				            table_load();
				           
				            tname.setText("");
				            tedition.setText("");
				            tprice.setText("");
				            tname.requestFocus();
						}

			            catch (SQLException e1) {
							
							e1.printStackTrace();
						}
		
				}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 22));
		btnUpdate.setBounds(550, 582, 222, 53);
		contentPane.add(btnUpdate);

		JButton btnDelete = new JButton("DELETE");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                String bid;
	bid  = tid.getText();
	
	 try {
			pst = con.prepareStatement("delete from book where id =?");
	
            pst.setString(1, bid);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Record Delete!!!!!");
            table_load();
           
            tname.setText("");
            tedition.setText("");
            tprice.setText("");
            tname.requestFocus();
		}

        catch (SQLException e1) {
			
			e1.printStackTrace();
		}
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 22));
		btnDelete.setBounds(949, 579, 230, 58);
		contentPane.add(btnDelete);

		table_load();
	}

}
