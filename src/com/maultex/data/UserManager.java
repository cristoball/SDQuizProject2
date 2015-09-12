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

		String sqltxt;
		sqltxt = "Select id, firstname, lastname, email, password " + 
				"From App.Account " + 
				"Where email = '" + username + "' " + //joe@example.com' "
				"And password = '" + password + "'";// joe'";

		try
		{

			Connection conn = DataSource.getConnection();
			
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
				System.out.println("Email=" + rs.getString(4) + ", password=" + rs.getString(5));
				User loggedInUser = new User();
				loggedInUser.setEmail("chris.mauldin@gmail.com");
				loggedInUser.setFirstName(rs.getString(2));
				loggedInUser.setLastName(rs.getString(3));
				loggedInUser.setID(rs.getInt(1));
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
