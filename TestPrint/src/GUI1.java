
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class GUI1 extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField textField_name;
	private JTextField textField_total_sale;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					try{
						UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					}catch (Exception e) {
						e.printStackTrace();
					}
					GUI1 frame = new GUI1();
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
	ArrayList<Saleman> list = new ArrayList<Saleman>();
	public GUI1() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(16, 75, 394, 185);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		textField_name = new JTextField();
		textField_name.setBounds(121, 6, 130, 26);
		contentPane.add(textField_name);
		textField_name.setColumns(10);
		
		textField_total_sale = new JTextField();
		textField_total_sale.setBounds(121, 44, 130, 26);
		contentPane.add(textField_total_sale);
		textField_total_sale.setColumns(10);
		
		JLabel lblName = new JLabel("name");
		lblName.setBounds(22, 11, 61, 16);
		contentPane.add(lblName);
		
		JLabel lblTotalSale = new JLabel("total sale");
		lblTotalSale.setBounds(16, 49, 61, 16);
		contentPane.add(lblTotalSale);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(! textField_total_sale.getText().matches("-?\\d+(\\.\\d+)?"))
				{
					JOptionPane.showMessageDialog(GUI1.this, "input only number");
					textField_total_sale.requestFocus();
					textField_total_sale.selectAll();
					return;
				}
				Saleman s = new Saleman();
				s.name = textField_name.getText();
				s.totalSale = Integer.parseInt(textField_total_sale.getText());
				list.add(s);
				
				DefaultTableModel model = new DefaultTableModel();
				model.addColumn("name");
				model.addColumn("totalSale");
				
				for(Saleman ss : list)
				{
					model.addRow(new Object[] {ss.name ,ss.totalSale});
				}
				table.setModel(model);
			}
		});
		btnAdd.setBounds(255, 6, 96, 29);
		contentPane.add(btnAdd);
		
		JButton btnPrint = new JButton("Print");
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PrinterJob job = PrinterJob.getPrinterJob();
				job.setPrintable(new SalemanPrintable(list));
				boolean doPrint = job.printDialog();
				if (doPrint) {
					try {
						job.print();
					} catch (PrinterException ee) {
						// TODO: handle exception
					}
				}
			}
		});
		btnPrint.setBounds(348, 6, 96, 29);
		contentPane.add(btnPrint);
	}
}
