package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TheaterDB {
	static Connection con;
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@70.12.115.56:1521:orcl";
	String user = "heegeon";
	String passw = "1234";
	

	private TheaterDB() throws Exception {
		getClass().forName(driver);
		con = DriverManager.getConnection(url, user, passw);
	}

	public static Connection getConnection() throws Exception {
		if (con == null)
			new TheaterDB();
		return con;
	}

	
}
