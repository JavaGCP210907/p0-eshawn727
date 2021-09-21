package com.revature.models;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import com.revature.dao.CustomersDao;
import com.revature.dao.OrdersDao;
import com.revature.dao.ProductsDao;

public class GUI implements ActionListener {
	
	JFrame baseFrame = new JFrame("Eric Shawn GCP210907"); //Gui frame with title
	JComboBox tblComboBox;
	JComboBox optionBox;
	JScrollPane sp; 
	
	JButton exitB; 
	JButton updateCustB; 
	JButton addProdB; 
	JButton deleteB;
	
	JTextField addTxt1;
	JTextField addTxt2;
	JTextField addTxt3;
	JTextField addTxt4;
	JTextField prodIdtxt;
	
	JPanel npanel = new JPanel();
	JPanel wpanel = new JPanel();
	JPanel spanel = new JPanel();
	JPanel epanel = new JPanel();
	JPanel cpanel = new JPanel();
	JPanel tblPanel = new JPanel();
	
	public GUI() {
		buildGUI();
	}
	
	// build user interface
	public void buildGUI() {
		//Create base frame to hold all containers; set layout/size; add company logo
		ImageIcon image = new ImageIcon("pegasus3.png");
		baseFrame.setSize(800,675);
		baseFrame.setLayout(new BorderLayout());	
		baseFrame.setIconImage(image.getImage());
		baseFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);			
		
		// Sub panels using borderlayout
		npanel.setPreferredSize(new Dimension(70,48));
		wpanel.setPreferredSize(new Dimension(100,100));
		spanel.setPreferredSize(new Dimension(100,50));
		cpanel.setPreferredSize(new Dimension(100,50));		
		npanel.setBackground(Color.WHITE);

		// format center panel with additional layout to structure content
		cpanel.setLayout(new BorderLayout(20,20));
		// create panel to hold buttons; set size
		JPanel buttonPanel = new JPanel();
		buttonPanel.setPreferredSize(new Dimension(0,75));
		
		// add panels to base frame
		cpanel.add(tblPanel,BorderLayout.CENTER);				
		baseFrame.add(npanel,BorderLayout.NORTH);
		baseFrame.add(spanel,BorderLayout.SOUTH);
		baseFrame.add(wpanel,BorderLayout.WEST);
		baseFrame.add(cpanel,BorderLayout.CENTER);
		cpanel.add(buttonPanel,BorderLayout.NORTH);
		
		// add created buttons with event handlers 
		exitB = new JButton("EXIT");		
		spanel.add(exitB);
		exitB.addActionListener(this);
		
		updateCustB = new JButton("Update Customer");
		updateCustB.addActionListener(this);
		
		addProdB = new JButton("Add Product");
		addProdB.addActionListener(this);
		
		deleteB = new JButton("Delete Product");
		deleteB.addActionListener(this);
		
		FlowLayout bLayout = new FlowLayout(); // additional layout for buttons
		bLayout.setHgap(20);
		buttonPanel.setLayout(bLayout);
		buttonPanel.add(updateCustB);	
		buttonPanel.add(addProdB);	
		buttonPanel.add(deleteB);
		
		// create combobox for calculations
		String[] options = {"Gross Sales", "Gross Profit Margin", "Product Net Profit"};
		optionBox = new JComboBox(options);
		optionBox.addActionListener((e) -> {optionBoxAction(e);});
		buttonPanel.add(optionBox);
				
		// create company logo and welcome banner	
		npanel.setLayout(new BorderLayout(50,10));
		JLabel logo = new JLabel(image);
		JLabel greeting = new JLabel("Welcome to Pegasus Enterprises");
		Font font1 = new Font("serif", Font.BOLD, 30);		
		greeting.setFont(font1);				
		npanel.add(logo,BorderLayout.WEST);
		npanel.add(greeting, BorderLayout.CENTER);
		
		// create combobox for table selection
		String[] tables = {"Customers", "Products", "Orders"};
		tblComboBox = new JComboBox(tables);
		tblComboBox.addActionListener( (e) -> {tblSelectAct(e);});		
		JLabel tblSelect = new JLabel("Select Table");
		Font font2 = new Font("serif", Font.BOLD, 16);
		tblSelect.setFont(font2);		
		wpanel.add(tblSelect);
		wpanel.add(tblComboBox);
		
		// add java image to center panel for application launch
		ImageIcon image2 = new ImageIcon("java.png");
		JLabel logo2 = new JLabel(image2);
		tblPanel.add(logo2);
		// make application visible
		baseFrame.setVisible(true);	
	}
	
	// event handler to build proper panel to get user input
	@Override
	public void actionPerformed(ActionEvent e) {		
		//button logic builds a panel in center for user input
		if (e.getSource() == exitB){
			baseFrame.setVisible(false);
		} else if (e.getSource() == updateCustB) {
			buildCustPanel();
		} else if (e.getSource() == addProdB) {
			buildProdPanel();		
		} else if (e.getSource() == deleteB) {
			buildDeletePanel();
		}		
	} // end actionPerformed
	
	// event handler for updating customer last name
	private void updateCustAction(ActionEvent e) {
		// access dao layer to update customer last name in database
		CustomersDao custDao = new CustomersDao();
		String num = addTxt1.getText();
		int custNum = Integer.parseInt(num);
		String lName = addTxt2.getText();
		custDao.updateCustLName(custNum, lName);
	}
	
	// event handler for add product button
	private void addProdAction(ActionEvent e) {
		// access dao layer to add a new product to database
		ProductsDao prodDao = new ProductsDao();		
		String prodName = addTxt1.getText();
		String prodType = addTxt2.getText();
		String num = addTxt3.getText();
		double prodCost = Double.parseDouble(num);
		String num2 = addTxt4.getText();
		double prodPrice = Double.parseDouble(num2);		
		Products prod = new Products(prodName, prodType, prodCost, prodPrice);
		prodDao.addProduct(prod);		
	}

	// event handler for delete product button
	private void deleteProdAct(ActionEvent e) {
		// access dao layer to delete a product by id number
		ProductsDao prodDao = new ProductsDao();
		String num = addTxt1.getText();
		int prodId = Integer.parseInt(num);
		prodDao.deleteProduct(prodId);		
	}
	
	// event handler for table selection
	public void tblSelectAct(ActionEvent e) {
		// access dao layer to display tables to user
		if(tblComboBox.getSelectedItem() == "Customers") {
			//
			JTable custTable = getCustomersJtbl();
			JScrollPane custSp = new JScrollPane(custTable);
			tblPanel.removeAll();
			tblPanel.add(custSp);
			baseFrame.setVisible(true);
			
		} else if (tblComboBox.getSelectedItem() == "Products"){
			//
			JTable prodTable = getProductsJtbl();
			JScrollPane prodSp = new JScrollPane(prodTable);
			tblPanel.removeAll();
			tblPanel.add(prodSp);				
			baseFrame.setVisible(true);
			
		} else if (tblComboBox.getSelectedItem() == "Orders") {
			//
			JTable ordTable = getOrdersJtbl();
			JScrollPane ordSp = new JScrollPane(ordTable);
			tblPanel.removeAll();
			tblPanel.add(ordSp, BorderLayout.CENTER);
			baseFrame.setVisible(true);				
		}				
	}
	
	// event handler for calculation options
	public void optionBoxAction (ActionEvent e) {
		//combobox logic 
		if(e.getSource() == optionBox) {
			if(optionBox.getSelectedItem() == "Gross Sales") {
				// access dao layer and use method to calculate gross sales from orders table
				OrdersDao ordDao = new OrdersDao();
				String gross = ordDao.showMoney();
				JOptionPane.showMessageDialog(null, gross);
				
			} else if(optionBox.getSelectedItem() == "Gross Profit Margin") {
				//
				System.out.println("gross profit");
			} else if(optionBox.getSelectedItem() == "Product Net Profit") {
				//
				System.out.println("net product profit");
			}	
		}		
	}
	
	// convert products arrayList to JTable
	public JTable getProductsJtbl() {
		// access dao layer and get table data
		ProductsDao prodDao = new ProductsDao();
		List<Products> prodList = new ArrayList<Products>(prodDao.getProducts());
		
		JTable prodTable = new JTable();
		String[] colName = {"prod_id", "prod_name", "prod_type", "prod_production_cost", "prod_sale_price"};
		Object[][] object = new Object[100][100];
		int i = 0;
		for(Products prod: prodList) {
			object[i][0] = prod.getProd_id();
			object[i][1] = prod.getProd_name();
			object[i][2] = prod.getProd_type();
			object[i][3] = prod.getProd_production_cost();		
			object[i][4] = prod.getProd_sale_price();
			i++;
			prodTable = new JTable(object, colName);
		}
		
		return prodTable;
	}
	
	// convert customers arrayList to JTable
	public JTable getCustomersJtbl() {
		CustomersDao custDao = new CustomersDao();
		List<Customers> custList = new ArrayList<Customers>(custDao.getCustomers());
		
		JTable custTable = new JTable();
		String[] colName = {"cust_num", "f_name", "l_name"};
		
		Object[][] object = new Object[100][100];
		int i = 0;
		for(Customers cust: custList) {
			object[i][0] = cust.getCust_num();
			object[i][1] = cust.getF_name();
			object[i][2] = cust.getL_name();
			i++;
			custTable = new JTable(object, colName);
		}
		
		return custTable;
	}
	
	// convert orders arrayList to JTable
	public JTable getOrdersJtbl() {
		OrdersDao ordersDao = new OrdersDao();
		List<Orders> ordersList = new ArrayList<Orders>(ordersDao.getOrders());
		
		JTable ordTable = new JTable();
		String[] colName = {"order_num", "prod_id", "cust_num"};
		Object[][] object = new Object[100][100];
		int i = 0;
		for(Orders ord: ordersList) {
			object[i][0] = ord.getOrder_num();
			object[i][1] = ord.getProd_id_fk();
			object[i][2] = ord.getCust_num_fk();
			i++;
			ordTable = new JTable(object, colName);
		}
		
		return ordTable;	
	}
	
	// build panel for user input options
	public void buildCustPanel() {
		// create a panel for user input
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(4,0));
		JLabel prompt1 = new JLabel("Enter Customer Number:");
		addTxt1 = new JTextField(20);
		JLabel prompt2 = new JLabel("Enter Last Name:");
		addTxt2 = new JTextField(20);	
		JLabel blank = new JLabel("");
		JLabel blank2 = new JLabel("");
		JLabel blank3 = new JLabel("");
		JButton updateCustB = new JButton("Confirm");	
		updateCustB.addActionListener((e) -> {updateCustAction(e);});
		
		buttonPanel.add(prompt1);
		buttonPanel.add(addTxt1);
		buttonPanel.add(prompt2);			
		buttonPanel.add(addTxt2);
		buttonPanel.add(blank);
		buttonPanel.add(blank2);
		buttonPanel.add(blank3);
		buttonPanel.add(updateCustB);
					
		tblPanel.removeAll();
		tblPanel.repaint();
		tblPanel.add(buttonPanel, BorderLayout.CENTER);
		baseFrame.revalidate();
		baseFrame.setVisible(true);
	}

	// build panel for user input options
	public void buildProdPanel() {
		// create panel for product input
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(6,0));
		JLabel prompt1 = new JLabel("Product Name:");
		addTxt1 = new JTextField(20);
		JLabel prompt2 = new JLabel("Product Type:");
		addTxt2 = new JTextField(20);	
		JLabel prompt3 = new JLabel("Product Production Price:");
		addTxt3 = new JTextField(20);
		JLabel prompt4 = new JLabel("Product Sale Price:");
		addTxt4 = new JTextField(20);
		JLabel blank = new JLabel("");
		JLabel blank2 = new JLabel("");
		JLabel blank3 = new JLabel("");
		
		JButton addProdB = new JButton("Confirm");
		addProdB.addActionListener((e) -> {addProdAction(e);});
				
		buttonPanel.add(prompt1);
		buttonPanel.add(addTxt1);
		buttonPanel.add(prompt2);			
		buttonPanel.add(addTxt2);
		buttonPanel.add(prompt3);
		buttonPanel.add(addTxt3);
		buttonPanel.add(prompt4);
		buttonPanel.add(addTxt4);
		buttonPanel.add(blank);
		buttonPanel.add(blank2);
		buttonPanel.add(blank3);
		buttonPanel.add(addProdB);
										
		tblPanel.removeAll();
		tblPanel.repaint();
		tblPanel.add(buttonPanel, BorderLayout.CENTER);
		baseFrame.revalidate();
		baseFrame.setVisible(true);
	}

	// build panel for user input options
	public void buildDeletePanel(){
		// create panel for delete input
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(4,0));
		JButton deleteProdB = new JButton("Confirm");
		deleteProdB.addActionListener((e) -> {deleteProdAct(e);});
		JLabel prompt = new JLabel("Enter Product ID:");
		addTxt1 = new JTextField(20);
		JLabel blank = new JLabel("");
		JLabel blank2 = new JLabel("");
		JLabel blank3 = new JLabel("");
		
		buttonPanel.add(prompt);
		buttonPanel.add(addTxt1);
		buttonPanel.add(blank);
		buttonPanel.add(blank2);
		buttonPanel.add(blank3);
		buttonPanel.add(deleteProdB);
		
		tblPanel.removeAll();
		tblPanel.repaint();
		tblPanel.add(buttonPanel, BorderLayout.CENTER);
		baseFrame.revalidate();
		baseFrame.setVisible(true);
	}

} // class end
