package com.revature.models;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
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
	
	JFrame baseFrame = new JFrame("Eric Shawn GCP210907");
	JComboBox tblComboBox;
	JComboBox optionBox;
	JScrollPane sp; 
	JButton exitB; 
	JButton editB; 
	JButton addB; 
	JButton deleteB;
	JTextField addTxt1;
	JTextField addTxt2;
	
	JPanel npanel = new JPanel();
	JPanel wpanel = new JPanel();
	JPanel spanel = new JPanel();
	JPanel epanel = new JPanel();
	JPanel cpanel = new JPanel();
	JPanel tblPanel = new JPanel();
	
	public GUI() {
		buildGUI();
	}
	
	public void buildGUI() {
		//
		ImageIcon image = new ImageIcon("pegasus3.png");
		
		//baseFrame.getContentPane().setBackground(Color.blue);
		baseFrame.setSize(800,675);
		baseFrame.setLayout(new BorderLayout());
		//baseFrame.setResizable(false);
		baseFrame.setIconImage(image.getImage());
		baseFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		baseFrame.setLayout(new BorderLayout());
		
		
		npanel.setPreferredSize(new Dimension(70,48));
		wpanel.setPreferredSize(new Dimension(100,100));
		spanel.setPreferredSize(new Dimension(100,50));
		epanel.setPreferredSize(new Dimension(100,100));
		cpanel.setPreferredSize(new Dimension(50,100));
		
		npanel.setBackground(Color.WHITE);
		//wpanel.setBackground(Color.blue);
		//spanel.setBackground(Color.green);
		//epanel.setBackground(Color.gray);
		//cpanel.setBackground(Color.yellow);
		
		// SUB PANELS
		cpanel.setLayout(new BorderLayout(20,20));
		JPanel centerTxt = new JPanel();
		//centerTxt.setBackground(Color.BLACK);
		centerTxt.setPreferredSize(new Dimension(0,50));
		//tblPanel.setBackground(Color.cyan);
		//tblPanel.setPreferredSize(new Dimension(250,250));
		cpanel.add(tblPanel,BorderLayout.CENTER);
		
		
				
		baseFrame.add(npanel,BorderLayout.NORTH);
		baseFrame.add(spanel,BorderLayout.SOUTH);
		baseFrame.add(epanel,BorderLayout.EAST);
		baseFrame.add(wpanel,BorderLayout.WEST);
		baseFrame.add(cpanel,BorderLayout.CENTER);
		cpanel.add(centerTxt,BorderLayout.NORTH);
		
		//
		exitB = new JButton("EXIT");	
		JButton confirmActB = new JButton("Confirm");
		confirmActB.addActionListener((e) -> {confirmAct(e);});
		spanel.add(confirmActB);
		spanel.add(exitB);
		exitB.addActionListener(this);
		
		editB = new JButton("EDIT");
		editB.addActionListener(this);
		addB = new JButton("Add");
		addB.addActionListener(this);
		deleteB = new JButton("Delete");
		deleteB.addActionListener(this);
		
		FlowLayout bLayout = new FlowLayout();
		bLayout.setHgap(20);
		centerTxt.setLayout(bLayout);
		centerTxt.add(addB);
		centerTxt.add(editB);		
		centerTxt.add(deleteB);
		String[] options = {"Gross Sales", "Gross Profit Margin", "Product Net Profit", "Sort Orders By Product"};
		optionBox = new JComboBox(options);
		optionBox.addActionListener((e) -> {optionBoxAction(e);});
		centerTxt.add(optionBox);
		
		
		//
		npanel.setLayout(new BorderLayout(50,10));
		
		JLabel logo = new JLabel(image);
		JLabel greeting = new JLabel("Welcome to Pegasus Enterprises");
		Font font1 = new Font("serif", Font.BOLD, 30);
		
		greeting.setFont(font1);
		
		
		npanel.add(logo,BorderLayout.WEST);
		npanel.add(greeting, BorderLayout.CENTER);
		
		//combobox for tables selection
		String[] tables = {"Customers", "Products", "Orders"};
		tblComboBox = new JComboBox(tables);
		tblComboBox.addActionListener( (e) -> {tblSelectAct(e);});
		
		JLabel tblSelect = new JLabel("Select Table");
		Font font2 = new Font("serif", Font.BOLD, 16);
		tblSelect.setFont(font2);
		
		wpanel.add(tblSelect);
		wpanel.add(tblComboBox);
		
		ImageIcon image2 = new ImageIcon("java.png");
		JLabel logo2 = new JLabel(image2);
		logo2.setSize(100, 100);
		tblPanel.add(logo2);
		//tblPanel.setBackground(Color.white);
		baseFrame.setVisible(true);	
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		
		//button logic.
		if (e.getSource() == exitB){
			baseFrame.setVisible(false);
		} else if (e.getSource() == addB) {
			//
			JPanel addPanel = new JPanel();
			addPanel.setLayout(new GridLayout(3,0));
			JLabel prompt1 = new JLabel("Enter Customer Number:");
			addTxt1 = new JTextField(20);
			JLabel prompt2 = new JLabel("Enter Last Name:");
			addTxt2 = new JTextField(20);			
			
			addPanel.add(prompt1);
			addPanel.add(addTxt1);
			addPanel.add(prompt2);			
			addPanel.add(addTxt2);
						
			tblPanel.removeAll();
			tblPanel.repaint();
			tblPanel.add(addPanel, BorderLayout.CENTER);
			baseFrame.revalidate();
			baseFrame.setVisible(true);
		} else if (e.getSource() == editB) {
			//
		} else if (e.getSource() == deleteB) {
			//
		}
		
	} // end actionPerformed
	
	private void confirmAct(ActionEvent e) {
		// TODO Auto-generated method stub
		CustomersDao custDao = new CustomersDao();
		String num = addTxt1.getText();
		int custNum = Integer.parseInt(num);
		String lName = addTxt2.getText();
		custDao.updateCustLName(custNum, lName);
	}

	public void tblSelectAct(ActionEvent e) {
		
		if(e.getSource() == tblComboBox) {	
			if(tblComboBox.getSelectedItem() == "Customers") {
				System.out.println("cust test");
				//
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
				
				JScrollPane custSp = new JScrollPane(custTable);
				tblPanel.removeAll();
				tblPanel.add(custSp);
				baseFrame.setVisible(true);
				
			} else if (tblComboBox.getSelectedItem() == "Products"){
				System.out.println("products");
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
				
				JScrollPane prodSp = new JScrollPane(prodTable);
				tblPanel.removeAll();
				tblPanel.add(prodSp);				
				baseFrame.setVisible(true);
				
			} else if (tblComboBox.getSelectedItem() == "Orders") {
				System.out.println("orders");
				JTable ordTable = getOrdersJtbl();
				JScrollPane ordSp = new JScrollPane(ordTable);
				tblPanel.removeAll();
				tblPanel.add(ordSp, BorderLayout.CENTER);
				baseFrame.setVisible(true);				
			}
		}		
	}
	
	public void optionBoxAction (ActionEvent e) {
		//combobox logic for addded functions
		if(e.getSource() == optionBox) {
			if(optionBox.getSelectedItem() == "Gross Sales") {
				//
				OrdersDao ordDao = new OrdersDao();
				String gross = ordDao.showMoney();
				JOptionPane.showMessageDialog(null, gross);
				
			} else if(optionBox.getSelectedItem() == "Gross Profit Margin") {
				//
				System.out.println("gross profit");
			} else if(optionBox.getSelectedItem() == "Product Net Profit") {
				//
				System.out.println("net product profit");
			} else if(optionBox.getSelectedItem() == "Sort Orders By Product") {
				//
				System.out.println("sort products");
			}
		}		
	}
	
	
	// convert orders arrayList to JTable; return as JTable object
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

} // class end
