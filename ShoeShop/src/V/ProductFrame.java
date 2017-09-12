package V;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableModel;

import M.CustomerDB;
import M.CustomerManager;
import M.ProductDB;
import M.ProductManager;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;

import java.awt.Color;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ProductFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField_id;
	private JTextField textField_name;
	private JTextField textField_price_per_unit;
	private JTextField textField_description;
	private ImagePanel imagePanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					ProductFrame frame = new ProductFrame();
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
	public ProductFrame() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 722, 520);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 6, 358, 475);
		contentPane.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (table.getSelectedRowCount() < 1)
					return;
				int index = table.getSelectedRow();
				BufferedImage img = list.get(index).product_image;
				if (img != null) {
					imagePanel.setImage(img);
				}
			}
		});
		scrollPane.setViewportView(table);

		JLabel lblProductid = new JLabel("product id");
		lblProductid.setBounds(376, 21, 101, 16);
		contentPane.add(lblProductid);

		JLabel lblProductName = new JLabel("product name");
		lblProductName.setBounds(376, 60, 100, 16);
		contentPane.add(lblProductName);

		JLabel lblPricePerUnit = new JLabel("price per unit");
		lblPricePerUnit.setBounds(376, 98, 113, 16);
		contentPane.add(lblPricePerUnit);

		JLabel lblProductDest = new JLabel("product dest.");
		lblProductDest.setBounds(376, 138, 100, 16);
		contentPane.add(lblProductDest);

		textField_id = new JTextField();
		textField_id.setColumns(10);
		textField_id.setBackground(Color.YELLOW);
		textField_id.setBounds(483, 16, 188, 26);
		contentPane.add(textField_id);

		textField_name = new JTextField();
		textField_name.setColumns(10);
		textField_name.setBounds(483, 55, 188, 26);
		contentPane.add(textField_name);

		textField_price_per_unit = new JTextField();
		textField_price_per_unit.setColumns(10);
		textField_price_per_unit.setBounds(483, 93, 188, 26);
		contentPane.add(textField_price_per_unit);

		textField_description = new JTextField();
		textField_description.setColumns(10);
		textField_description.setBounds(483, 133, 188, 26);
		contentPane.add(textField_description);

		JButton button_save = new JButton("SAVE NEW");
		button_save.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) {
				
				if(textField_price_per_unit.getText().trim().matches("-?\\d+(\\.\\d+)?") )
				{
					JOptionPane.showMessageDialog(ProductFrame.this, "Number only Please");
					textField_price_per_unit.requestFocus();
					textField_price_per_unit.selectAll();
				}
				ProductDB x =new ProductDB();
				x.product_id = 0;
				x.product_name = textField_name.getText().trim();
				x.product_description = textField_description.getText().trim();
				x.price_per_unit = Double.parseDouble(textField_price_per_unit.getText().trim());
				x.product_image = (BufferedImage)imagePanel.getImage();
				
				ProductManager.saveProduct(x);
				load();
				textField_id.setText("");
				textField_name.setText("");
				textField_description.setText("");
				textField_price_per_unit.setText("");
				imagePanel.setImage(null);

				JOptionPane.showMessageDialog(ProductFrame.this, "finish!!");
			}
		});
		button_save.setBounds(371, 371, 117, 29);
		contentPane.add(button_save);

		JButton button_edit = new JButton("EDIT");
		button_edit.setBounds(483, 371, 117, 29);
		contentPane.add(button_edit);

		JButton button_delete = new JButton("DELETE");
		button_delete.setBounds(599, 371, 117, 29);
		contentPane.add(button_delete);

		imagePanel = new ImagePanel();
		imagePanel.setBounds(449, 181, 254, 178);
		contentPane.add(imagePanel);

		JButton btnBrowseimg = new JButton("Browse img");
		btnBrowseimg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				fc.addChoosableFileFilter(new OpenFileFilter("jpeg", "Photo in JPEG format"));
				fc.addChoosableFileFilter(new OpenFileFilter("jpg", "Photo in JPEG format"));
				fc.addChoosableFileFilter(new OpenFileFilter("png", "PNG image"));
				fc.addChoosableFileFilter(new OpenFileFilter("svg", "Scalable Vector Graphic"));
				fc.setAcceptAllFileFilterUsed(false);
				int returnVal = fc.showOpenDialog(ProductFrame.this);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File f = fc.getSelectedFile();

					try {
						BufferedImage bimg = ImageIO.read(f);
						System.out.println(bimg);
						imagePanel.setImage(bimg);

					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnBrowseimg.setBounds(371, 181, 73, 39);
		contentPane.add(btnBrowseimg);

		load();
	}

	ArrayList<ProductDB> list;
	private JTable table;

	public void load() {
		list = ProductManager.getAllProduct();
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("product_id");
		model.addColumn("product_name");
		model.addColumn("price_per_unit");
		model.addColumn("product_description");

		for (ProductDB c : list) {
			model.addRow(new Object[] { "" + c.product_id, c.product_name, c.price_per_unit, c.product_description });

		}
		table.setModel(model);

	}
}

class OpenFileFilter extends FileFilter {

	String description = "";
	String fileExt = "";

	public OpenFileFilter(String extension) {
		fileExt = extension;
	}

	public OpenFileFilter(String extension, String typeDescription) {
		fileExt = extension;
		this.description = typeDescription;
	}

	@Override
	public boolean accept(File f) {
		if (f.isDirectory())
			return true;
		return (f.getName().toLowerCase().endsWith(fileExt));
	}

	@Override
	public String getDescription() {
		return description;
	}
}
