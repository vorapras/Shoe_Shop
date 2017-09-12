package V;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import M.CompanyInfoDB;
import M.CompanyInfoManager;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CompanyInfoFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField_company_name;
	private JTextField textField_address;
	private JTextField textField_phone;
	private JTextField textField_email;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CompanyInfoFrame frame = new CompanyInfoFrame();
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
	public CompanyInfoFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField_company_name = new JTextField();
		textField_company_name.setBounds(169, 30, 251, 26);
		contentPane.add(textField_company_name);
		textField_company_name.setColumns(10);
		
		JLabel lblCompanyName = new JLabel("Company Name");
		lblCompanyName.setBounds(45, 35, 99, 16);
		contentPane.add(lblCompanyName);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setBounds(45, 73, 99, 16);
		contentPane.add(lblAddress);
		
		textField_address = new JTextField();
		textField_address.setColumns(10);
		textField_address.setBounds(169, 68, 251, 26);
		contentPane.add(textField_address);
		
		JLabel lblPhone = new JLabel("Phone");
		lblPhone.setBounds(45, 106, 99, 16);
		contentPane.add(lblPhone);
		
		textField_phone = new JTextField();
		textField_phone.setColumns(10);
		textField_phone.setBounds(169, 101, 251, 26);
		contentPane.add(textField_phone);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(45, 139, 99, 16);
		contentPane.add(lblEmail);
		
		textField_email = new JTextField();
		textField_email.setColumns(10);
		textField_email.setBounds(169, 134, 251, 26);
		contentPane.add(textField_email);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 xCompanyInfoDB.company_name = textField_company_name.getText();
				 xCompanyInfoDB.address = textField_address.getText();
				 xCompanyInfoDB.phone = textField_phone.getText();
				 xCompanyInfoDB.email = textField_email.getText();
				CompanyInfoManager.edit(xCompanyInfoDB);				
			}
		});
		btnSave.setBounds(169, 194, 117, 29);
		contentPane.add(btnSave);
		
		loadData ();
	}
	CompanyInfoDB xCompanyInfoDB;
 public void loadData ()
 {
	 xCompanyInfoDB = CompanyInfoManager.getCompanyInfo();
	 textField_company_name.setText(xCompanyInfoDB.company_name);
	 textField_address.setText(xCompanyInfoDB.address);
	 textField_phone.setText(xCompanyInfoDB.phone);
	 textField_email.setText(xCompanyInfoDB.email);

 }
	
}
