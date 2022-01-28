import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

public class Option4 {

	// Shows user the Option 4 Menus that user can choose from
	// User will select an option and it calls corresponding function
	public static void option4Main(Connection conn) throws SQLException, IOException {
		boolean done = false;
		do {
			printMenuOneOptions();
			System.out.print("Type in your option: ");
			System.out.flush();
			String ch = Entry.readLine();
			System.out.println();
			switch (ch.charAt(0)) {
				case 'a':
					addNewEquipment(conn);
					break;
				case 'b':
					addNewService(conn);
					break;
				case 'c':
					addNewCustomer(conn);
					break;
				case 'd':
					addNewEmployee(conn);
					break;
				case 'q':
					done = true;
					break;
				default:
					System.out.println(" Not a valid option ");
			} // switch
		} while (!done);
	}

	// Displays to user the options available at beginning
	private static void printMenuOneOptions() {
		System.out.println("***************************************************************************");
		System.out.println("\t \t  Welcome to Clean-and-Go Shop ");
		System.out.println("\t \t (d) Updates ");
		System.out.println("***************************************************************************");
		System.out.println("\t \t (a) Add new equipment ");
		System.out.println("\t \t (b) Add a new service ");
		System.out.println("\t \t (c) Add new customer information ");
		System.out.println("\t \t (d) Add new employee information ");
		System.out.println("\t \t (q) Return to main menu ");
	}

	// Function will add a new equipment into the database
	private static void addNewEquipment(Connection conn) throws SQLException, IOException {

		System.out.println("------Currently adding a New Equipment------ \n");

		// Variables to store New Equipment
		// Prompts user to enter New Equipment Brand, Type, and Quantity
		String new_brand, new_type, new_quantity;
		new_brand = Entry.readEntry("Enter Brand : ");
		new_type = Entry.readEntry("Enter Equipment Type: ");
		new_quantity = Entry.readEntry("Enter Quantity: ");

		// Converts quantity from String to int
		int int_quantity = Integer.parseInt(new_quantity);

		// First statement query (stmt query_one) retrieves current max equipment_ID
		// from database
		// used to update values in Equipment Query
		Statement stmt = conn.createStatement();
		String query_one = "SELECT MAX(Equipment_ID) FROM Equipment";

		// ResultSet type
		ResultSet rset = stmt.executeQuery(query_one);

		// While rset has row returned
		while (rset.next()) {

			// Set returned value from database as current max equipment_ID
			int equipment_ID = rset.getInt(1);

			// Adding New Equipment
			// Query Two will insert entered values into Equipment Table
			String query_two = "INSERT INTO Equipment (Equipment_ID, Brand, Equipment_Type, Quantity)"
					+ " VALUES (?, ?, ?, ?)";

			// PreparedStatement will insert into table
			PreparedStatement preparedStmt = conn.prepareStatement(query_two);
			preparedStmt.setInt(1, equipment_ID + 1);
			preparedStmt.setString(2, new_brand);
			preparedStmt.setString(3, new_type);
			preparedStmt.setInt(4, int_quantity);

			// Runs the query
			preparedStmt.execute();
			break;
		}
	}

	// Function will add a new service into database
	private static void addNewService(Connection conn) throws SQLException, IOException {

		System.out.println("-------Currently adding a New Service------- \n");

		// Variables to store New Service
		// Prompts user to enter name, description, rate, duration, equipment_ID,
		// supplies_ID
		String new_name, new_description, new_rate, new_duration, equipment_ID, supplies_ID;
		new_name = Entry.readEntry("Enter Service Name : ");
		new_description = Entry.readEntry("Enter Description: ");
		new_rate = Entry.readEntry("Enter Rate(Per Hour) : ");
		new_duration = Entry.readEntry("Enter Duration (Hours): ");
		System.out.println();
		System.out.println("If Equipment_ID or Supplies_ID is NULL, press Enter");
		equipment_ID = Entry.readEntry("Enter Equipment_ID used : ");
		supplies_ID = Entry.readEntry("Enter Supplies_ID used: ");
		
		// Conversion of variables
		// Converts rate from String to double
		// Converts duration from String to int
		double dbl_rate = Double.parseDouble(new_rate);
		int int_duration = Integer.parseInt(new_duration);

		// First statement query (stmtOne query_one) retrieves current max Service_ID
		// from Service Table
		// used to update values in actual query
		Statement stmtOne = conn.createStatement();
		String query_one = "SELECT MAX(Service_ID) FROM Service";

		// Second statement query (stmtTwo query_two) retrieves current max Request_ID
		// from Service Table
		// used to update values in actual query
		Statement stmtTwo = conn.createStatement();
		String query_two = "SELECT MAX(Request_ID) FROM Service_Request";

		ResultSet rsetOne = stmtOne.executeQuery(query_one);

		while (rsetOne.next()) {

			// Set returned value from database as current max service ID
			// from stmtOne query_one
			int service_id = rsetOne.getInt(1);

			ResultSet rsetTwo = stmtTwo.executeQuery(query_two);

			while (rsetTwo.next()) {

				// Set returned value from database as current max request ID
				// from stmtTwo query_two
				int request_id = rsetTwo.getInt(1);

				// Query will update Service_Request table before the actual Service query, due
				// to foreign key constraint
				String constraint_query = "INSERT INTO Service_Request (Request_ID)" + " VALUES (?)";

				PreparedStatement constraint_statement = conn.prepareStatement(constraint_query);
				constraint_statement.setInt(1, request_id + 1);
				constraint_statement.execute();

				// Query Three will insert entered values into Service Table
				String query_three = "INSERT INTO Service (Service_ID, Name, Description, Rate, Duration_Hours, Request_ID, Equipment_ID, Supplies_ID)"
						+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

				// PreparedStatement will insert into Service Table
				PreparedStatement preparedStmt = conn.prepareStatement(query_three);
				preparedStmt.setInt(1, service_id + 1);
				preparedStmt.setString(2, new_name);
				preparedStmt.setString(3, new_description);
				preparedStmt.setDouble(4, dbl_rate);
				preparedStmt.setInt(5, int_duration);
				preparedStmt.setInt(6, request_id + 1);

				// if both equipment ID and supplies_ID is blank
				if (equipment_ID.isBlank() && supplies_ID.isBlank()) {
					preparedStmt.setNull(7, Types.NULL);
					preparedStmt.setNull(8, Types.NULL);
				}

				// if equipmentID is blank, we only convert supplies ID from String to int
				// and pass it to preparedStatement
				else if (equipment_ID.isBlank()) {
					int int_supplies_ID = Integer.parseInt(supplies_ID);
					preparedStmt.setNull(7, Types.NULL);
					preparedStmt.setInt(8, int_supplies_ID);
				}

				// if suppliesID is blank, only convert equipment ID from String to int
				// and pass it to preparedStatement
				else if (supplies_ID.isBlank()) {
					int int_equipment_ID = Integer.parseInt(equipment_ID);
					preparedStmt.setInt(7, int_equipment_ID);
					preparedStmt.setNull(8, Types.NULL);
				}

				// if both supplies_ID and equipment_ID is not blank
				// convert both equipment_ID and supplies_ID from String to int
				// and pass it to preparedStatement
				else if (supplies_ID != "" && equipment_ID != "") {
					int int_equipment_ID = Integer.parseInt(equipment_ID);
					int int_supplies_ID = Integer.parseInt(supplies_ID);
					preparedStmt.setInt(7, int_equipment_ID);
					preparedStmt.setInt(8, int_supplies_ID);
				}

				// Runs the Service Table query
				preparedStmt.execute();
				break;
			}
		}
	}

	// Function will add a new Customer to database
	private static void addNewCustomer(Connection conn) throws SQLException, IOException {

		System.out.println("-------Currently adding a New Customer------- \n");

		// Variables to store New Customer
		// Part 1 stores information about a Person object
		// Prompts user to enter First_Name, Last_Name, Email, Address, Phone,
		// Date_of_Birth
		String first_name, last_name, email, address, phone, date_of_birth;
		System.out.println("Information for Part 1 -- Person Table");
		first_name = Entry.readEntry("Enter First Name: ");
		last_name = Entry.readEntry("Enter Last Name: ");
		email = Entry.readEntry("Enter Email: ");
		address = Entry.readEntry("Enter Address: ");
		phone = Entry.readEntry("Enter Phone: ");
		date_of_birth = Entry.readEntry("Enter Date Of Birth (YY-MM-DD): ");

		System.out.println();

		// Part 2 stores information about Customer Information
		// Prompts user to enter CC number, Balance, CC_Date, CC_Code
		System.out.println("Information for Part 2 -- Customer Table");
		String CC_Num, balance, CC_Date, CC_Code;
		CC_Num = Entry.readEntry("Enter Credit Card Number: ");
		balance = Entry.readEntry("Enter Balance: ");
		CC_Date = Entry.readEntry("Enter Credit Card Date (MM/DD): ");
		CC_Code = Entry.readEntry("Enter Credit Card Code (3 digit): ");

		// Conversion of variables
		// Converts balance from String to double
		// Converts CC_Code from String to int
		double dbl_balance = Double.parseDouble(balance);
		int int_CC_Code = Integer.parseInt(CC_Code);

		// First statement query retrieves current max_ID from database
		// used to add in second query
		Statement stmt = conn.createStatement();

		// Query One will retrieve current max ID from Person Table
		String query_one = "SELECT MAX(ID) FROM Person";

		ResultSet rset = stmt.executeQuery(query_one);

		// While rset has row returned
		while (rset.next()) {

			// Set returned value from database as current max current_ID
			// from stmt query_one
			int current_ID = rset.getInt(1);

			// Adding New Customer Part 1
			// Query will update Person Table before the actual Customer query, due to
			// foreign key constraint
			// This uses information from Part 1
			String constraint_query = "INSERT INTO Person (ID, First_Name, Last_Name, Email, Address, Phone, Date_Of_Birth)"
					+ " VALUES (?, ?, ?, ?, ?, ?, ?)";

			PreparedStatement constraint_statement = conn.prepareStatement(constraint_query);
			constraint_statement.setInt(1, current_ID + 1);
			constraint_statement.setString(2, first_name);
			constraint_statement.setString(3, last_name);
			constraint_statement.setString(4, email);
			constraint_statement.setString(5, address);
			constraint_statement.setString(6, phone);
			constraint_statement.setString(7, date_of_birth);

			// Runs the query
			constraint_statement.execute();

			// Adding New Customer Part 2
			// Query Two will insert values Customer Table
			// This uses information from Part 2
			String query_two = "INSERT INTO Customer (CC, Balance, Customer_ID, CC_Date, CC_Code)"
					+ " VALUES (?, ?, ?, ?, ?)";

			// PreparedStatement will insert into Customer Table
			PreparedStatement preparedStmt = conn.prepareStatement(query_two);
			preparedStmt.setString(1, CC_Num);
			preparedStmt.setDouble(2, dbl_balance);
			preparedStmt.setInt(3, current_ID + 1);
			preparedStmt.setString(4, CC_Date);
			preparedStmt.setInt(5, int_CC_Code);

			// Runs the query
			preparedStmt.execute();
			break;
		}
	}

	// Function will add a new Employee to the database
	private static void addNewEmployee(Connection conn) throws SQLException, IOException {

		System.out.println("-------Currently adding a New Employee------- \n");

		// Variables to store New Employee
		// Part 1 stores information about a Person object
		// Prompts user to enter First_Name, Last_Name, Email, Address, Phone,
		// Date_of_Birth
		String first_name, last_name, email, address, phone, date_of_birth;
		System.out.println("Information for Part 1 -- Person Table");
		first_name = Entry.readEntry("Enter First Name: ");
		last_name = Entry.readEntry("Enter Last Name: ");
		email = Entry.readEntry("Enter Email: ");
		address = Entry.readEntry("Enter Address: ");
		phone = Entry.readEntry("Enter Phone: ");
		date_of_birth = Entry.readEntry("Enter Date Of Birth: ");

		System.out.println();

		// Part 2 stores information about Employee Information
		// Prompts user to enter gender, hired date, position
		System.out.println("Information for Part 2 -- Employee Table");
		String gender, hired, position, salary;
		gender = Entry.readEntry("Enter Gender (M/F): ");
		hired = Entry.readEntry("Enter Date Hired (YYYY-MM-DD): ");
		position = Entry.readEntry("Enter Position: ");
		salary = Entry.readEntry("Enter Salary: ");

		// Conversion of variables
		// Converts salary from String to double
		double dbl_salary = Double.parseDouble(salary);

		// First statement query retrieves current max_ID from database
		// used to add in second query
		Statement stmt = conn.createStatement();

		// Query One will retrieve current max ID from Person Table
		String query_one = "SELECT MAX(ID) FROM Person";

		ResultSet rset = stmt.executeQuery(query_one);

		// While rset has row returned
		while (rset.next()) {

			// Set returned value from database as current max current_ID
			// from stmt query_one
			int current_ID = rset.getInt(1);

			// Adding New Employee Part 1
			// Query will update Person Table before the actual Employee query, due to
			// foreign key constraint
			// This uses information from Part 1
			String constraint_query = "INSERT INTO Person (ID, First_Name, Last_Name, Email, Address, Phone, Date_Of_Birth)"
					+ " VALUES (?, ?, ?, ?, ?, ?, ?)";

			PreparedStatement constraint_statement = conn.prepareStatement(constraint_query);
			constraint_statement.setInt(1, current_ID + 1);
			constraint_statement.setString(2, first_name);
			constraint_statement.setString(3, last_name);
			constraint_statement.setString(4, email);
			constraint_statement.setString(5, address);
			constraint_statement.setString(6, phone);
			constraint_statement.setString(7, date_of_birth);

			// Runs the query
			constraint_statement.execute();

			// Adding New Employee Part 2
			// Query Two will insert values Employee Table
			// This uses information from Part 2
			String query_two = "INSERT INTO Employee (Gender, Hired, Position, Employee_ID, Salary)"
					+ " VALUES (?, ?, ?, ?, ?)";

			// PreparedStatement will insert into Employee Table
			PreparedStatement preparedStmt = conn.prepareStatement(query_two);
			preparedStmt.setString(1, gender);
			preparedStmt.setString(2, hired);
			preparedStmt.setString(3, position);
			preparedStmt.setInt(4, current_ID + 1);
			preparedStmt.setDouble(5, dbl_salary);

			// Runs the query
			preparedStmt.execute();
			break;
		}
	}
}
