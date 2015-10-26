package com.maultex.data;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
		
		try 
		{	
			Connection conn = DataSource.getConnection();
			PreparedStatement stmt = getSecureSqlStatement(username, password, conn);
			ResultSet rs = stmt.executeQuery();		
			
			// check Quiz db integrity
			if (rs.getFetchSize() > 1)
			{
				throw new IllegalStateException("More than one user account found");
			}

			if (rs.next())
			{
				User loggedInUser = new User();
				loggedInUser.setID(rs.getInt(1));
				loggedInUser.setFirstName(rs.getString(2));
				loggedInUser.setLastName(rs.getString(3));
				loggedInUser.setEmail(rs.getString(4));
				
				System.out.println("User found: " + loggedInUser);
				return loggedInUser;
			} 
			else
			{
				System.out.println("User not found");

			}

		} 
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		
		return null;
	}


	/**
	 * Get a prepared securely prepared login
	 * @param username
	 * @param password
	 * @param conn
	 * @return
	 * @throws SQLException
	 */
	private static PreparedStatement getSecureSqlStatement(String username, String password, Connection conn) throws SQLException
	{
		String sqltxt = "Select id, firstname, lastname, email, password " + 
						"From App.Account " + 
						"Where email=? And password=?";
		
		
		PreparedStatement stmt = conn.prepareStatement(sqltxt);
		stmt.setString(1, username);
		stmt.setString(2, password);
		
		System.out.println("SQL login query: " + stmt.toString());
		return stmt;
	}

	
	/**
	 * Simple db test of authenticating through Account table
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String args[]) throws Exception
	{
		String URL = "jdbc:derby://localhost:1527/quiz";
		
		try (Connection conn = DriverManager.getConnection(URL);)
				
		{
			
			PreparedStatement stmt =UserManager.getSecureSqlStatement("test@test.net", "test", conn);
			ResultSet rs = stmt.executeQuery();		
			
			// check Quiz db integrity
			if (rs.getFetchSize() > 1)
			{
				throw new IllegalStateException("More than one user account found");
			}

			if (rs.next())
			{
				User loggedInUser = new User();
				loggedInUser.setID(rs.getInt(1));
				loggedInUser.setFirstName(rs.getString(2));
				loggedInUser.setLastName(rs.getString(3));
				loggedInUser.setEmail(rs.getString(4));
				
				System.out.println("User found: " + loggedInUser);
				//return loggedInUser;
			} 
			else
			{
				System.out.println("User not found");

			}

		} 
		catch (Exception ex)
		{
			ex.printStackTrace();
		}

	}

}
