import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Entry {

	public static void entryMenu() {
		System.out.println("***************************************************************************");
		System.out.println("\t \t \t *****************************");
		System.out.println("\t \t   Welcome to Clean-and-Go Shop ");
		System.out.println("\t \t \t *****************************");
		System.out.println("***************************************************************************");
		System.out.println("\t \t (a) Equipment & Supplies ");
		System.out.println("\t \t (b) Customers & Services ");
		System.out.println("\t \t (c) Employees ");
		System.out.println("\t \t (d) Updates ");
		System.out.println("\t \t (q) Quit \n ");
	}

	static String readEntry(String prompt) {
		try {
			StringBuffer buffer = new StringBuffer();
			System.out.print(prompt);
			System.out.flush();
			int c = System.in.read();
			while (c != '\n' && c != -1) {
				buffer.append((char) c);
				c = System.in.read();
			}
			return buffer.toString().trim();
		} catch (IOException e) {
			return "";
		}
	}

	static String readLine() {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr, 1);
		String line = "";

		try {
			line = br.readLine();
		} catch (IOException e) {
			System.out.println("Error in SimpleIO.readLine: " + "IOException was thrown");
			System.exit(1);
		}
		return line;
	}

	// Performs Credential Check
	static boolean validateUser() {

		// Variables to read input
		String userID, userPass;
		userID = "";
		userPass = "";

		boolean flag = true;
		
		// Try once 
		// Keep letting user input until they quit
		do {
			// Prompt user for user and password
			userID = readEntry("userid : ");
			userPass = readEntry("password: ");

			if (userID.equals("admin") && userPass.equals("admin")) {
				return true;
			} else {
				System.out.println("Incorrect username or password, to try again, press Y, or Q to quit");
				String ch = readLine();
				if (ch.charAt(0) == 'Q') {
					return false;
				} else {
					flag = true;
				}
			}
		} while (flag);
		return false;
	}
}
