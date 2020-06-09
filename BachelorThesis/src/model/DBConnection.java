package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	static String name = "root";
	static String pass = "root";
	public static String dbURL = "jdbc:mysql://localhost/test";
	
	public static Connection getConnection() throws SQLException
	{
		
		System.out.println(DriverManager.getConnection(dbURL,name,pass));
		return DriverManager.getConnection(dbURL,name,pass);
	}
}