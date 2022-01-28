
/*
 * Professor Sara Farag
 * Author: Jason Ho & Jarrel Khoo
 * Course: CS331
 * Date: 2020-12-05
 * Description: Final Project 2 Clean N Go Shop
 * Program performs the 3 Scenario described in Project2B.pdf
 * Scenario 1 performs a validation check and insert new information to database
 * Scenario 2 performs search and analysis on Equipment & Supplies on database
 * Scenario 3 searches Database for Employee Work Schedules
 */

import java.io.IOException;
import java.sql.*;

public class Task4 {
    public static void main(String args[]) {
        Connection conn = null;
        try {
            // Step 1: Load the JDBC driver(You have to have the connector Jar file in your
            // project Class path)

            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to the database(Change the URL)

            String url = "jdbc:mysql://localhost:3306/Cleaning_Shop";
            String user = "student";
            String pass = "password";
            conn = DriverManager.getConnection(url, user, pass);

            if (conn != null) {
                System.out.println("Connected to the database");
                DatabaseMetaData dm = conn.getMetaData();
                System.out.println("Driver name: " + dm.getDriverName());
            }

            boolean done = false;
            do {
                Entry.entryMenu();
                System.out.print("Type in your option: ");
                System.out.flush();
                String ch = Entry.readLine();
                System.out.println();
                switch (ch.charAt(0)) {
                    case 'a':
                        Option1.option1Main(conn);
                        break;
                    case 'b':
                    	// Not included
                        break;
                    case 'c':
                    	Option3.schedule(conn);
                        break;
                    case 'd':
                    	if(Entry.validateUser()) {
                    		Option4.option4Main(conn);
                    	}
                    	break;
                    case 'q':
                        done = true;
                        break;
                    default:
                        System.out.println(" Not a valid option ");
                } // switch
            } while (!done);

        } catch (ClassNotFoundException e) {
            System.out.println("Could not load the driver");
        } catch (SQLException ex) {
            System.out.println(ex);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    System.out.println(e);
                }
            }
        }
    }
}