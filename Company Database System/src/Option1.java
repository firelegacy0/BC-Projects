import java.io.IOException;
import java.sql.*;
import java.text.DecimalFormat;

public class Option1 {

	// Main menu of Equipment and supplies
	// Prints out the menu and prompts the user for an entry
	public static void option1Main(Connection conn) throws SQLException, IOException {
		boolean quit = false;
		do {
			option1Menu();
			System.out.print("Type in your option: ");
			System.out.flush();
			String ch = Entry.readLine();
			System.out.println();
			switch (ch.charAt(0)) {
				case 'a':
					analyze(conn);
					break;
				case 'b':
					supplies(conn);
					break;
				case 'c':
					equipment(conn);
					break;
				case 'q':
					quit = true;
					break;
				default:
					System.out.println(" Not a valid option ");
			}
		} while (!quit);
	}

	// Menu for Employee and supplies menu
	private static void option1Menu() {
		System.out.println("***************************************************************************");
		System.out.println("Welcome to the Clean-and-Go Shop");
		System.out.println("a. Equipment & Supplies");
		System.out.println("***************************************************************************");

		System.out.println("a. Analyze the annual expenses of the business");
		System.out.println("b. Supplies");
		System.out.println("c. Equipment");
		System.out.println("q. Quit");
	}

	// Analyze Menu
	private static void analyzeMenu() {
		System.out.println("     Analyze the annual expenses of the business");
		System.out.println("--------------------------------------------------\n");
		System.out.println("Select one of the two options:");
		System.out.println("a. Cleaning supplies annual expenses");
		System.out.println("b. Annual expenses Report");
	}
	
	// Supplies Menu
	private static void suppliesMenu() {
		System.out.println("    			 Supplies");
		System.out.println("--------------------------------------------------\n");
		System.out.println("a. Product suppliers");
		System.out.println("b. Cleaning supplies alert");
	}

	// Equipment Menu
	private static void equipmentMenu() {
		System.out.println("     					Equipment	");
		System.out.println("--------------------------------------------------\n");
		System.out.println("a. Total number of equipment");
		System.out.println("b. Maintenance schedule");
		System.out.println("c. Average monthly usage");
	}

	// Analyze main menu 
	// User chooses one of the two options 
	private static void analyze(Connection conn) throws SQLException, IOException {
		boolean quit = false;
		do {
			analyzeMenu();
			System.out.print("Type in your option: ");
			System.out.flush();
			String ch = Entry.readLine();
			System.out.println();
			switch (ch.charAt(0)) {
				case 'a':
					supplyExpenses(conn);
					quit = true;
					break;
				case 'b':
					annualExpenses(conn);
					quit = true;
					break;
				default:
					System.out.println(" Not a valid option ");
			} // switch
		} while (!quit);
	}

	// Supplies main menu
	// User choose one of the two choices 
	private static void supplies(Connection conn) throws SQLException, IOException {
		boolean quit = false;
		do {
			suppliesMenu();
			System.out.print("Type in your option: ");
			System.out.flush();
			String ch = Entry.readLine();
			System.out.println();
			switch (ch.charAt(0)) {
				case 'a':
					supplier(conn);
					quit = true;
					break;
				case 'b':
					supplyAlert(conn);
					quit = true;
					break;
				default:
					System.out.println(" Not a valid option ");
			} // switch
		} while (!quit);
	}

	// Equipment main menu
	// User chooses one of the three choices 
	private static void equipment(Connection conn) throws SQLException, IOException {
		boolean quit = false;
		do {
			equipmentMenu();
			System.out.print("Type in your option: ");
			System.out.flush();
			String ch = Entry.readLine();
			System.out.println();
			switch (ch.charAt(0)) {
				case 'a':
					totalEquips(conn);
					quit = true;
					break;
				case 'b':
					maintenanceSchedule(conn);
					quit = true;
					break;
				case 'c':
					equipmentUsage(conn);
					quit = true;
					break;
				default:
					System.out.println(" Not a valid option ");
			} // switch
		} while (!quit);
	}

	// Calculate the amount of money that was spent on supplies
	private static void supplyExpenses(Connection conn) throws SQLException, IOException {
		Statement stmt = conn.createStatement();
		
		String query = "SELECT SUM(Amount_Due) " + "FROM Purchase "
				+ "WHERE (Purchase_Date BETWEEN '2020-01-01' AND '2020-12-31') AND (Description LIKE '%Cleaning Supplies%')";

		ResultSet rset;
		rset = stmt.executeQuery(query);

		System.out.println("    Cleaning supplies annual expenses");
		System.out.println("--------------------------------------------------\n");
		
		// Read all the returned rows from the query execution
		// Print out the values
		while (rset.next()) {
			double salary = rset.getDouble(1);
			System.out.println("Total amount spent: $" + salary);
		}
	}

	private static void annualExpenses(Connection conn) throws SQLException, IOException {
		double total = 0;

		Statement stmt = conn.createStatement();

		String purchase = "SELECT SUM(Amount_Due) " + "FROM Purchase "
				+ "WHERE (Purchase_Date BETWEEN '2020-01-01' AND '2020-12-31') AND (Description LIKE '%Equipment%')";

		String salaries = "SELECT Salary * SUM(TIMESTAMPDIFF(HOUR, Start_Time, End_Time)) "
				+ "FROM Work_Schedule AS w JOIN Employee AS e ON w.Employee_ID = e.Employee_ID "
				+ "WHERE (Start_Day BETWEEN '2020-01-01' AND '2020-12-31') " + "GROUP BY e.Employee_ID";

		String equipCost = "SELECT SUM(Cost) " + "FROM Maintenance_Schedule";
		
		// Create three different variables with the ResbultSet type 
		// For three different queries
		ResultSet rsetP, rsetS, rsetE;
		
		// Formatting for decimal numbers to 2 decimal spaces
		DecimalFormat df = new DecimalFormat("#.##");

		System.out.println("    Annual expenses Report");
		System.out.println("--------------------------------------------------\n");

		rsetP = stmt.executeQuery(purchase);
		// Read all the returned rows from the query execution
		// Print out the values
		while (rsetP.next()) {
			double equipment = rsetP.getDouble(1);
			System.out.println("Equipment: $" + equipment);
		}

		rsetS = stmt.executeQuery(salaries);
		// Read all the returned rows from the query execution
		// Add all the values up
		while (rsetS.next()) {
			double salary = rsetS.getDouble(1);
			total = total + salary;
		}

		rsetE = stmt.executeQuery(equipCost);
		// Read all the returned rows from the query execution
		// Print out the values
		while (rsetE.next()) {
			double cost = rsetE.getDouble(1);
			System.out.println("Cost of equipment maintenance: $" + df.format(cost * (365 / 7)));
		}
		
		// Print the total amount for employee salaries
		System.out.println("Salaries: $" + total * (365 / 7));

		// Assume 3000/month
		double rent = 12 * 3000;
		System.out.println("Rent: $" + rent);
	}
	
	// Supplier function that prints out supplier and the products they offer
	private static void supplier(Connection conn) throws SQLException, IOException {
		String query = "SELECT Supplier_Name, Products " + "FROM Supplier " + "WHERE Supplier_Name = ?";

		PreparedStatement p = conn.prepareStatement(query);

		System.out.println("    Products suppliers");
		System.out.println("--------------------------------------------------\n");
		
		// Ask for user input
		String supplier = Entry.readEntry("Enter supplier name: ");
		p.clearParameters();
		p.setString(1, supplier);

		// Execute the query with the user input
		ResultSet rset = p.executeQuery();
		
		// Check if value is found
		// If not return not found statement
		if (rset.next()) {
			String name, product;

			name = rset.getString(1);
			product = rset.getString(2);

			System.out.println("Products by " + name + ": " + product);
		} else {
			System.out.println("There is no such supplier.");
		}
	}
	
	// Prints out which supplies needs re-supplying
	private static void supplyAlert(Connection conn) throws SQLException, IOException {
		Statement stmt = conn.createStatement();

		String query = "SELECT Inventory, Required_Inventory, Name " 
		+ "FROM Cleaning_Supplies";

		ResultSet rset;
		rset = stmt.executeQuery(query);

		System.out.println("    Cleaning supplies alert");
		System.out.println("--------------------------------------------------\n");
		
		// Read all the returned rows from the query execution
		// Prints out the supplies 
		while (rset.next()) {
			int inv, reqInv;
			String name;

			inv = rset.getInt(1);
			reqInv = rset.getInt(2);
			name = rset.getString(3);
			
			// Check if amount is less than the required amount
			if (inv < reqInv) {
				System.out.println(name + " needs resupplying");
			}
		}
	}
	
	// Print out the total number of equipment grouped by type
	private static void totalEquips(Connection conn) throws SQLException, IOException {
		Statement stmt = conn.createStatement();

		String query = "SELECT Equipment_Type, SUM(Quantity) " + "FROM Equipment " + "GROUP BY Equipment_Type";

		ResultSet rset;
		rset = stmt.executeQuery(query);

		System.out.println("    Total Equipment");
		System.out.println("--------------------------------------------------\n");

		// Read all the returned rows from the query execution
		// Print out the equipment
		while (rset.next()) {
			String name = rset.getString(1);
			double num = rset.getInt(2);

			System.out.println("Type: " + name + ", Quantity: " + num);
		}
	}

	// Prints out the maintenance schedules for the week
	private static void maintenanceSchedule(Connection conn) throws SQLException, IOException {
		Statement stmt = conn.createStatement();

		// SQL query
		String query = "SELECT Start_Day, Equipment_Type "
				+ "FROM Maintenance_Schedule AS m JOIN Equipment AS e ON m.Equipment_ID = e.Equipment_ID "
				+ "WHERE m.Start_Day IS NOT NULL";

		// Execute the query
		ResultSet rset;
		rset = stmt.executeQuery(query);

		System.out.println("    Maintenance Schedule");
		System.out.println("--------------------------------------------------\n");

		// Read all the returned rows from the query execution
		// Print out the schedules
		while (rset.next()) {
			String week, type;

			week = rset.getString(1);
			type = rset.getString(2);

			System.out.println(type + " has maintenance on " + week);
		}
	}
	
	//Prints out the daily equipment usage according to month for the year
	private static void equipmentUsage(Connection conn) throws SQLException, IOException {
		Statement stmt = conn.createStatement();

		// SQL query
		String query = "SELECT DISTINCT e.Equipment_ID, Year, e.Equipment_Type "
				+ "FROM Total_Usage AS u JOIN Equipment AS e ON u.Equipment_ID = e.Equipment_ID";

		// Execute above query
		ResultSet rset = stmt.executeQuery(query);

		// SQL query
		String query2 = "SELECT Month, Usage_Hours, e.Equipment_ID, Equipment_Type "
				+ "FROM Total_Usage AS u JOIN Equipment AS e ON u.Equipment_ID = e.Equipment_ID "
				+ "WHERE e.Equipment_ID = ? " + "ORDER BY Month DESC";

		
		PreparedStatement p = conn.prepareStatement(query2);
		
		// Decimal formatting to 2 decimal places
		DecimalFormat df = new DecimalFormat("#.##");

		System.out.println("    Average Monthly Equipment Usage");
		System.out.println("--------------------------------------------------\n");

		// Read all the returned rows from the query execution
		while (rset.next()) {
			String month, strID, equipment;
			double num;
			int id, year;

			year = rset.getInt(2);
			id = rset.getInt(1);
			equipment = rset.getString(3);

			// Prints out just the year and equipment
			System.out.println(
					"In " + year + " (current year) for " + equipment + " average monthly usage during this year is: ");

			// Convert id to string
			strID = Integer.toString(id);

			p.clearParameters();
			p.setString(1, strID);
			
			// Execute query with equipment ID
			ResultSet rset1 = p.executeQuery();

			// Read all the returned rows from the query execution 
			// Print out month and hours of one equipment
			while (rset1.next()) {
				num = rset1.getDouble(2);
				month = rset1.getString(1);

				System.out.println(month + ": " + df.format(num / 31) + " hours per day");
			}
		}
	}
}
