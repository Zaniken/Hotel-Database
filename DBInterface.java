//Database project Zaniken Gurule, Christopher newton, Parker Rosenberg

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;
import java.awt.Font;

public class DBInterface {

	// "jdbc:mysql://127.0.0.1:3306/hotelmanagement", "christophernewton",
	// "christophernewton";

	/*
	 * private String addy = "jdbc:mysql://127.0.0.1:3306/hotelmanagement"; private
	 * String name = "christophernewton"; private String password =
	 * "christophernewton";
	 */

	private String addy = "jdbc:mysql://localhost:3306/sys";
	private String name = "root";
	private String password = "password";

	private JFrame frame;
	private JTextField category;
	private JTextField category2;
	private JTextField searchVlue;
	private JLabel lblPleaseTypeSome;
	private JTextField ISBN;
	private JTextField title;
	private JTextField pubDate;
	private JTextField pubID;
	private JTextField Cost;
	private JTextField retail;
	private JTextField discount;
	private JTextField cat;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DBInterface window = new DBInterface();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DBInterface() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(0, 0, 1200, 850);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel menuPanel = new JPanel();
		menuPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		menuPanel.setBounds(0, 0, 145, 611);
		frame.getContentPane().add(menuPanel);
		menuPanel.setLayout(null);

		// start buttons

		JButton btn1 = new JButton("Search Hotels");
		btn1.setBounds(0, 20, 145, 40);
		menuPanel.add(btn1);

		JButton btn2 = new JButton("View Rooms");
		btn2.setBounds(0, 60, 145, 40);
		menuPanel.add(btn2);

		JButton btn3 = new JButton("Reservation Search");
		btn3.setBounds(0, 100, 145, 40);
		menuPanel.add(btn3);

		JButton btn4 = new JButton("Search Staff");
		btn4.setBounds(0, 140, 145, 40);
		menuPanel.add(btn4);

		JButton btn5 = new JButton("Search City");
		btn5.setBounds(0, 180, 145, 40);
		menuPanel.add(btn5);

		JButton btn6 = new JButton("Admin");
		btn6.setBounds(0, 220, 145, 40);
		menuPanel.add(btn6);

		// end buttons

		JPanel titlePanel = new JPanel();
		titlePanel.setBorder(null);
		titlePanel.setBounds(144, 0, 710, 41);
		frame.getContentPane().add(titlePanel);

		JLabel lblWelcomeToMy = new JLabel("Hotel DataBase Interface");
		lblWelcomeToMy.setFont(new Font("Tahoma", Font.BOLD, 18));
		titlePanel.add(lblWelcomeToMy);

		JPanel mainPanel = new JPanel();
		mainPanel.setBounds(144, 42, 740, 569);
		frame.getContentPane().add(mainPanel);
		mainPanel.setLayout(null);

		JLabel lblPleaseChooseAn = new JLabel("Please choose an option from the menu");
		lblPleaseChooseAn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPleaseChooseAn.setBounds(192, 110, 351, 50);
		mainPanel.add(lblPleaseChooseAn);

		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchHotels(mainPanel);

			}
		});
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewRoomsPanel(mainPanel);

			}
		});

		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn3Gui(mainPanel);

			}
		});
		btn4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn4Gui(mainPanel);

			}
		});

		btn5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn5gui(mainPanel);

			}
		});
		btn6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminPanel(mainPanel);

			}
		});

	}

	// Start btn1
	public void searchHotels(JPanel pan) {

		pan.removeAll();
		JLabel lblPleaseTypeA = new JLabel("Please Refresh to see hotels and their managers");
		lblPleaseTypeA.setBounds(330, 20, 300, 50);
		pan.add(lblPleaseTypeA);

		/*
		 * category = new JTextField(); category.setBounds(326, 70, 200, 50);
		 * pan.add(category); category.setColumns(10);
		 */
		JButton lookup = new JButton("Refresh");
		lookup.setBounds(326, 133, 200, 50);
		pan.add(lookup);
		frame.repaint();
		JTextPane displaypane = new JTextPane();
		displaypane.setBounds(50, 220, 800, 600);

		lookup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				displaypane.setText(searchHotels1());
				pan.add(displaypane);
				System.out.println("test2");
				frame.repaint();

			}
		});

	}

	public String searchHotels1() {
		String Query;
		String mystr;
		/*
		 * if (s.equalsIgnoreCase("all")) {
		 * 
		 * Query = "select ISBN, Title, Retail from BOOKS "; } else { Query =
		 * "select ISBN, Title, Retail from BOOKS where Category ='" + s + "'"; }
		 */
		// TODO add query
		Query = "select HotelName, Manager, PhoneNumber from hotel";

		mystr = "Hotels found in the database: \n \n";
		try {
			System.out.println("test");

			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(addy, name, password);
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery(Query);
			mystr += ("Hotel Name \t |\t Manager \t|\tPhoneNumber \n");
			while (rs.next())

				mystr += rs.getString(1) + " | \t" + rs.getString(2) + "\t |\t" + rs.getString(3) + "\n";
			con.close();
		} catch (Exception e) {
			System.out.println("error");
			System.out.println(e);
		}
//	System.out.println(mystr);
		if (mystr.equals("Hotels found in the database: \n \n")) {
			mystr = "Sorry!!\nNo hotels were found";
		}
		return mystr;

	}
	// end btn1

	// Start btn2
	public void viewRoomsPanel(JPanel pan) {

		pan.removeAll();
		JLabel lblPleaseTypeA = new JLabel("Please type a hotel name to view rooms");
		lblPleaseTypeA.setBounds(330, 20, 300, 50);
		pan.add(lblPleaseTypeA);

		// Start Buttons
		JRadioButton cname = new JRadioButton("Room Types");
		cname.setBounds(62, 47, 200, 50);
		pan.add(cname);

		JRadioButton rdbtnState = new JRadioButton("View all");
		rdbtnState.setBounds(62, 123, 200, 50);
		pan.add(rdbtnState);

		JRadioButton oid = new JRadioButton("Available rooms");
		oid.setBounds(62, 84, 200, 50);
		pan.add(oid);

		ButtonGroup bg = new ButtonGroup();

		bg.add(cname);
		bg.add(oid);
		bg.add(rdbtnState);
		// end buttons

		category = new JTextField();
		category.setBounds(326, 70, 200, 50);
		pan.add(category);
		category.setColumns(10);

		JButton lookup = new JButton("SUBMIT");
		lookup.setBounds(326, 133, 200, 50);
		pan.add(lookup);
		frame.repaint();
		JTextPane displaypane = new JTextPane();
		displaypane.setBounds(50, 220, 800, 600);

		lookup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cname.addActionListener(this);
				oid.addActionListener(this);
				rdbtnState.addActionListener(this);
				int selection = 0;

				// TODO make this work probably just send full query based on this
				
				
				if (cname.isSelected()) {

					selection = 1;
					
				} else if (rdbtnState.isSelected()) {

					selection = 3;
					
				} else if (oid.isSelected()) {

					selection = 2;
				

				}
				

				displaypane.setText(searchRooms(selection, category.getText()));
				pan.add(displaypane);
				frame.repaint();

			}
		});

	}

	public String searchRooms(int selection, String s) {
		String Query;
		String mystr = null;

		if (selection == 1) {

			Query = "select room.Available, room.BedType from room, hotel where room.HotelID=hotel.HotelID and hotel.HotelName='"+s+"'";

			mystr = "rooms found in the database: \n \n";
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection(addy, name, password);

				Statement stmt = con.createStatement();

				ResultSet rs = stmt.executeQuery(Query);
			mystr+=("Available \t | \t Room Type \n");
				while (rs.next())

					mystr += rs.getString(1) + "\t | \t" + rs.getString(2) +"\n";
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}

			if (mystr.equals("Books found in the database: \n \n")) {
				mystr = "Sorry!!\nNo books were found";
			}
			return mystr;
		}
		if (selection == 2) {

			Query = "select room.RoomNum, room.Available, room.BedType from room, hotel where room.HotelID=hotel.HotelID and hotel.HotelName='"+s+"' and room.Available='true'";

			mystr = "rooms found in the database: \n \n";
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection(addy, name, password);

				Statement stmt = con.createStatement();

				ResultSet rs = stmt.executeQuery(Query);
				mystr+=("Room number \t | \t availability \t | \t bed type  \n");
				while (rs.next())

					mystr += rs.getString(1) + "\t | \t" + rs.getString(2) + "\t |\t" + rs.getString(3) + "\n";
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
//	System.out.println(mystr);
			if (mystr.equals("rooms found in the database: \n \n")) {
				mystr = "Sorry!!\nNo books were found";
			}
			return mystr;
		}
		if (selection == 3) {

			Query = "select room.RoomNum, room.Available, room.BedType, room.Smoking, room.price from room, hotel where room.HotelID=hotel.HotelID and hotel.HotelName='"+s+"' ;";

			mystr = "Books found in the database: \n \n";
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection(addy, name, password);

				Statement stmt = con.createStatement();

				ResultSet rs = stmt.executeQuery(Query);
				mystr+=("Room number  | \t availability \t | \t bed type \t | \t smoking \t | \t price   \n");
				while (rs.next())

					mystr += rs.getString(1) + "\t | \t" + rs.getString(2) + "\t |\t" + rs.getString(3) + "\t | \t" + rs.getString(4) +"\t | \t" + rs.getString(5) +"\n";
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}

			
			return mystr;
		}
		return mystr;
	}

	// End btn2

	//start btn 3
	public void btn3Gui(JPanel pan) {

		pan.removeAll();
//		JLabel lblPleaseTypeA = new JLabel("Please provide either customer name or hotel and room#");
	//	lblPleaseTypeA.setBounds(330, 20, 300, 50);
		//pan.add(lblPleaseTypeA);

		// Start Buttons
		JRadioButton cname = new JRadioButton("Customer fist and last name");
		cname.setBounds(62, 47, 200, 50);
		pan.add(cname);

		JRadioButton rdbtnState = new JRadioButton("hotel and room #");
		rdbtnState.setBounds(62, 123, 200, 50);
		pan.add(rdbtnState);

		
		ButtonGroup bg = new ButtonGroup();

		bg.add(cname);
		
		bg.add(rdbtnState);
		// end buttons

		category = new JTextField();
		category.setBounds(300, 70, 200, 50);
		pan.add(category);
		category.setColumns(10);

		category2 = new JTextField();
		category2.setBounds(550, 70, 200, 50);
		pan.add(category2);
		category2.setColumns(10);

		
		
		
		JButton lookup = new JButton("SUBMIT");
		lookup.setBounds(326, 133, 200, 50);
		pan.add(lookup);
		frame.repaint();
		JTextPane displaypane = new JTextPane();
		displaypane.setBounds(50, 220, 800, 600);

		lookup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cname.addActionListener(this);
				
				rdbtnState.addActionListener(this);
				int selection = 0;

				// TODO make this work probably just send full query based on this
				
				
				if (cname.isSelected()) {

					selection = 1;
					
				} else if (rdbtnState.isSelected()) {

					selection = 2;
					
				
				

				}
				

				displaypane.setText(btn3inside(selection, category.getText(),category2.getText()));
				pan.add(displaypane);
				frame.repaint();

			}
		});

	}
	public String btn3inside(int selection, String s, String s2) {
		String Query;
		String mystr = null;

		if (selection == 2) {

			Query ="select distinct customer.FirstName, hotel.HotelName, room.RoomNum, reservation.Res_Date, reservation.Total_Price from hotel, reservation, room, customer where hotel.HotelName='"+s+"'and room.RoomNum='"+s2+"' and reservation.CustomerID=customer.CustomerID and reservation.HotelID=hotel.HotelID";

			mystr = "rooms found in the database: \n \n";
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection(addy, name, password);

				Statement stmt = con.createStatement();

				ResultSet rs = stmt.executeQuery(Query);
				mystr+=("Firstname \t | \t hotelname \t | \t room# \t | \t reservation date \t | \t price  \n");
				while (rs.next())

					mystr += rs.getString(1) + "\t | \t" + rs.getString(2) +"\n";
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}

			if (mystr.equals("Books found in the database: \n \n")) {
				mystr = "Sorry!!\nNo books were found";
			}
			return mystr;
		}
		else if (selection == 1) {

			Query = "select distinct customer.FirstName, hotel.HotelName, room.RoomNum, reservation.Res_Date, reservation.Total_Price from hotel, reservation, room, customer where customer.LastName='"+s2+"'and customer.FirstName='"+s+"' and customer.CustomerID=reservation.CustomerID and reservation.HotelID=hotel.HotelID";
			mystr = "rooms found in the database: \n \n";
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection(addy, name, password);

				Statement stmt = con.createStatement();

				ResultSet rs = stmt.executeQuery(Query);
				mystr+=("Firstname \t | \t hotelname \t | \t room# \t | \t reservation date \t | \t price  \n");
				while (rs.next())

					mystr += rs.getString(1) + "\t | \t" + rs.getString(2) + "\t |\t" + rs.getString(3) + "\t | \t" + rs.getString(4) +  "\t | \t" + rs.getString(5) +  "\n";
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}

			if (mystr.equals("rooms found in the database: \n \n")) {
				mystr = "Sorry!!\nNo books were found";
			}
			return mystr;
		}
		
		return mystr;
	}

	//end btn 3
	
	
	// Start Btn4
	public void btn4Gui(JPanel pan) {

		pan.removeAll();
		JLabel lblPleaseTypeA = new JLabel("Please Refresh to see staff and their availbility");
		lblPleaseTypeA.setBounds(330, 20, 300, 50);
		pan.add(lblPleaseTypeA);

		/*
		 * category = new JTextField(); category.setBounds(326, 70, 200, 50);
		 * pan.add(category); category.setColumns(10);
		 */
		JButton lookup = new JButton("Refresh");
		lookup.setBounds(326, 133, 200, 50);
		pan.add(lookup);
		frame.repaint();
		JTextPane displaypane = new JTextPane();
		displaypane.setBounds(50, 220, 800, 600);

		lookup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				displaypane.setText(gui4back());
				pan.add(displaypane);

				frame.repaint();

			}
		});

	}

	public String gui4back() {
		String Query;
		String mystr;
		/*
		 * if (s.equalsIgnoreCase("all")) {
		 * 
		 * Query = "select ISBN, Title, Retail from BOOKS "; } else { Query =
		 * "select ISBN, Title, Retail from BOOKS where Category ='" + s + "'"; }
		 */
		// TODO add query
		Query = "Select * from staff order by position";

		mystr = "Staff found in the database: \n \n";
		try {

			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(addy, name, password);
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery(Query);
			mystr += ("LastName \t | \t Shift \t | \t Position \t | \t Phonenumber \n");
			while (rs.next())

				mystr += rs.getString(3) + "\t | \t" + rs.getString(5) + "\t |\t" + rs.getString(6) + "\t |\t"
						+ rs.getString(7) + "\n";
			con.close();
		} catch (Exception e) {
			System.out.println("error");
			System.out.println(e);
		}
//	System.out.println(mystr);
		if (mystr.equals(" found in the database: \n \n")) {
			mystr = "Sorry!!\nNo hotels were found";
		}
		return mystr;

	}

	// End Btn4

	// Start btn 5

	public void btn5gui(JPanel pan) {

		pan.removeAll();
		JLabel lblPleaseTypeA = new JLabel("Please type a state to find rooms and rates");
		lblPleaseTypeA.setBounds(330, 20, 300, 50);
		pan.add(lblPleaseTypeA);

		category = new JTextField();
		category.setBounds(326, 70, 200, 50);
		pan.add(category);
		category.setColumns(10);

		JButton lookup = new JButton("SUBMIT");
		lookup.setBounds(326, 133, 200, 50);
		pan.add(lookup);
		frame.repaint();
		JTextPane displaypane = new JTextPane();
		displaypane.setBounds(50, 220, 800, 600);

		lookup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String column = "";

				displaypane.setText(btn5inside(category.getText()));
				pan.add(displaypane);
				frame.repaint();

			}
		});

	}

	public String btn5inside(String state) {
		String Query;
		String mystr;
		/*
		 * if (s.equalsIgnoreCase("all")) {
		 * 
		 * Query = "select ISBN, Title, Retail from BOOKS "; } else { Query =
		 * "select ISBN, Title, Retail from BOOKS where Category ='" + s + "'"; }
		 */
		// TODO add query
		Query = "select hotel.HotelName, room.RoomNum, room.Price from room, hotel where hotel.State='" + state
				+ "' and room.HotelID= hotel.HotelID and room.Available='true' ;";

		mystr = "Hotels rooms found in the database: \n \n";
		try {
			System.out.println("test");

			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(addy, name, password);
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery(Query);
			mystr += ("Hotel Name \t |\t Room # \t|\t price \n");
			while (rs.next())

				mystr += rs.getString(1) + " | \t" + rs.getString(2) + "\t |\t" + rs.getString(3) + "\n";
			con.close();
		} catch (Exception e) {
			System.out.println("error");
			System.out.println(e);
		}
//	System.out.println(mystr);
		if (mystr.equals("Hotels rooms found in the database: \n \n")) {
			mystr = "Sorry!!\nNo hotels were found";
		}
		return mystr;

	}

	// end Btn 5

	// start btn6
	public void AdminPanel(JPanel pan) {

		pan.removeAll();
		JLabel lblPleaseTypeA = new JLabel("Please give a full mySql command");
		lblPleaseTypeA.setBounds(330, 20, 200, 50);
		pan.add(lblPleaseTypeA);

		category = new JTextField();
		category.setBounds(326, 70, 200, 50);
		pan.add(category);
		category.setColumns(10);

		JButton lookup = new JButton("SUBMIT");
		lookup.setBounds(326, 133, 200, 50);
		pan.add(lookup);
		frame.repaint();
		JTextPane displaypane = new JTextPane();
		displaypane.setBounds(50, 220, 800, 600);

		lookup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				adminInsert((category.getText()));
				// pan.add(displaypane);
				frame.repaint();

				// JOptionPane.showMessageDialog(null, "Your record was inserted");

			}
		});

	}

	public void adminInsert(String s) {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(addy, name, password);
			Statement stmt = con.createStatement();
			String Query = s;

			stmt.executeUpdate(s); // insert query
			System.out.println("Test");
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}

	}
	// end btn6

}
