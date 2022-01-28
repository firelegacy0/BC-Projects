import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Option3 {

	// Shows the schedules of the inputed user ID
	public static void schedule(Connection conn) throws SQLException, IOException {
		Statement stmt = conn.createStatement();

		String query = "SELECT Employee_ID, Start_Day, Start_Time, End_Time " + "FROM Work_Schedule ";
		String check = "SELECT Employee_ID FROM Work_Schedule";

		System.out.println("    Employee Schedule");
		System.out.println("--------------------------------------------------\n");

		// Ask for user input
		String id = Entry.readEntry("Enter Employee ID: ");

		ResultSet rset, rcheck;
		rcheck = stmt.executeQuery(check);

		// Get the employee ID from database
		while (rcheck.next()) {
			String value = rcheck.getString(1);

			// Check if ID is present in database
			if (value.equals(id)) {
				rset = stmt.executeQuery(query);
				// If present print out the employee schedules
				while (rset.next()) {
					String start, end, day;
					int ID;

					ID = rset.getInt(1);
					day = rset.getString(2);
					start = rset.getString(3);
					end = rset.getString(4);

					System.out.println("Employee ID: " + ID + " works on " + day + " from " + start + " to " + end);
				}
				// When found escape function
				return;
			}
		}
		// If entered ID not found
		System.out.println("Incorrect Employee ID.");
	}
}
