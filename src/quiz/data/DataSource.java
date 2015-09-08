package quiz.data;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {

	private static Connection dbConn = null;
	private static String dbUrl = "jdbc:derby:/Users/christomaul/SD/databases/quiz";
	
	public static Connection getConnection() throws Exception
	{
		if (dbConn == null)
		{
			
			Class driverClass = Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			DriverManager.registerDriver((Driver) driverClass.newInstance());
			dbConn = DriverManager.getConnection(dbUrl);
		}
		return dbConn;
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
