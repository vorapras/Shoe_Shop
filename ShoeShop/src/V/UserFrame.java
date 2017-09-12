package V;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import M.CustomerDB;
import M.CustomerManager;
import M.ProductDB;
import M.ProductManager;
import M.UserDB;
import M.UserManager;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UserFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField_id;
	private JTextField textField_username;
	private JTextField textField_password;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					UserFrame frame = new UserFrame();
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
	public UserFrame() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 694, 484);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel label = new JLabel("id");
		label.setBounds(448, 43, 61, 16);
		contentPane.add(label);

		textField_id = new JTextField();
		textField_id.setColumns(10);
		textField_id.setBackground(Color.YELLOW);
		textField_id.setBounds(510, 38, 156, 26);
		contentPane.add(textField_id);

		JLabel lblUsername = new JLabel("username");
		lblUsername.setBounds(449, 76, 61, 16);
		contentPane.add(lblUsername);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(19, 28, 418, 400);
		contentPane.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (table.getSelectedRowCount() < 1) {
					return;
				}
				int index = table.getSelectedRow();
				int id = Integer.parseInt(table.getValueAt(index, 0).toString());
				String username = table.getValueAt(index, 1).toString();
				String password = ""+table.getValueAt(index, 2);
				String usertype = ""+table.getValueAt(index, 3);

				textField_id.setText("" + id);
				textField_username.setText("" + username);
				textField_password.setText("" + password);
				comboBox.setSelectedItem(usertype);

			}
		});
		scrollPane.setViewportView(table);

		textField_username = new JTextField();
		textField_username.setColumns(10);
		textField_username.setBounds(510, 71, 156, 26);
		contentPane.add(textField_username);

		textField_password = new JTextField();
		textField_password.setColumns(10);
		textField_password.setBounds(510, 105, 156, 26);
		contentPane.add(textField_password);

		JLabel lblPassword = new JLabel("password");
		lblPassword.setBounds(448, 110, 61, 16);
		contentPane.add(lblPassword);

		JLabel lblUsertype = new JLabel("usertype");
		lblUsertype.setBounds(449, 148, 61, 16);
		contentPane.add(lblUsertype);

		JButton button_save = new JButton("SAVE NEW");
		button_save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UserDB x = new UserDB(0, textField_username.getText().trim(), textField_password.getText().trim(),
						comboBox.getSelectedItem().toString().trim());
				UserManager.saveNewUser(x);
				load();
				textField_id.setText("");
				textField_username.setText("");
				textField_password.setText("");
				

				JOptionPane.showMessageDialog(UserFrame.this, "finish");
			}
		});
		button_save.setBounds(449, 198, 117, 29);
		contentPane.add(button_save);

		JButton button_edit = new JButton("EDIT");
		button_edit.setBounds(449, 227, 117, 29);
		contentPane.add(button_edit);

		JButton button_delete = new JButton("DELETE");
		button_delete.setBounds(449, 255, 117, 29);
		contentPane.add(button_delete);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"admin", "user"}));
		comboBox.setBounds(510, 144, 108, 27);
		contentPane.add(comboBox);
		
		load();
	}

	ArrayList<UserDB> list;
	private JComboBox comboBox;

	public void load() {
		list = UserManager.getAllUser();

		DefaultTableModel model = new DefaultTableModel();

		model.addColumn("id");
		model.addColumn("username");
		model.addColumn("password");
		model.addColumn("usertype");

		for (UserDB c : list) {
			model.addRow(new Object[] { c.id, c.username, c.password, c.usertype });

		}
		table.setModel(model);

	}
}
