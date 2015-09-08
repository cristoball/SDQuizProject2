package com.maultex.data;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import quiz.data.DataSource;

public class UserManager implements Authenticate
{

	/**
	 * Checks against datasource for correct username/password combo
	 * 
	 * @param username
	 * @param password
	 * @return a new User object if credentials were found
	 */
	public User login(String username, String password)
	{
		// if (username.equals("test@test.net"))
		// {
		// if (password.equals("test"))
		// {
		// User loggedInUser = new User();
		// loggedInUser.setEmail(username);
		// loggedInUser.setFirstName("Chris");
		// loggedInUser.setLastName("Mauldin");
		// return loggedInUser;
		// }
		//
		// }
		// return null;

		//String URL = "jdbc:derby://localhost:1527/quiz";
		//String dbUrl = "jdbc:derby:/Users/christomaul/SD/databases/quiz";
		String sqltxt;
		sqltxt = "Select email, password " + 
				"From App.Account " + 
				"Where email = '" + username + "' " + //joe@example.com' "
				"And password = '" + password + "'";// joe'";

		try
		{
			//
			// Print out all loaded JDBC drivers.
//			java.util.Enumeration e = java.sql.DriverManager.getDrivers();
//			System.out.println("BEFORE DRIVER LOAD");
//			while (e.hasMoreElements())
//			{
//				Object driverAsObject = e.nextElement();
//				System.out.println("JDBC Driver=" + driverAsObject);
//			}

			//Class driverClass = Class.forName("org.apache.derby.jdbc.ClientDriver");
//			Class driverClass = Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
//			DriverManager.registerDriver((Driver) driverClass.newInstance());
//			Connection conn = DriverManager.getConnection(dbUrl);
			Connection conn = DataSource.getConnection();
			
//			System.out.println("AFTER DRIVER LOAD");
//			// Print out all loaded JDBC drivers.
//			e = java.sql.DriverManager.getDrivers();
//			while (e.hasMoreElements())
//			{
//				Object driverAsObject = e.nextElement();
//				System.out.println("JDBC Driver=" + driverAsObject);
//			}

			
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sqltxt);

			System.out.println(stmt.toString());
			System.out.println(sqltxt);
			// check Quiz db integrity
			if (rs.getFetchSize() > 1)
			{
				throw new IllegalStateException("More than one user account found");
			}

			System.out.println("User found in db. Authenticate.");
			if (rs.next())
			{
				System.out.println("Email=" + rs.getString(1) + ", password=" + rs.getString(2));
				User loggedInUser = new User();
				loggedInUser.setEmail("chris.mauldin@gmail.com");
				loggedInUser.setFirstName("Chris");
				loggedInUser.setLastName("Mauldin");
				System.out.println("User found: " + loggedInUser);
				return loggedInUser;
			} else
			{
				System.out.println("User not found");

			}

		} catch (Exception ex)
		{
			ex.printStackTrace();
		}
		return null;
	}

	
	/**
	 * Simple db test of authenticating through Account table
	 * @param args
	 */
	public static void main(String args[])
	{
		String URL = "jdbc:derby://localhost:1527/quiz";
		String sqltxt;
		sqltxt = "Select email, password " + 
				"From App.Account " + 
				"Where email = 'joe@example.com' " +
				"And password = 'joe'";

		try (Connection conn = DriverManager.getConnection(URL);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sqltxt);)
		{
			System.out.println(stmt.toString());
			System.out.println(sqltxt);
			// check Quiz db integrity
			if (rs.getFetchSize() > 1)
			{
				throw new IllegalStateException("More than one user account found");
			}

			System.out.println("User found in db. Authenticate.");
			if (rs.next())
			{
				System.out.println("Email=" + rs.getString(1) + ", password=" + rs.getString(2));
				User loggedInUser = new User();
				loggedInUser.setEmail("chris.mauldin@gmail.com");
				loggedInUser.setFirstName("Chris");
				loggedInUser.setLastName("Mauldin");
				System.out.println("User found: " + loggedInUser);
			} else
			{
				System.out.println("User not found");
			}

		} catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}

}
