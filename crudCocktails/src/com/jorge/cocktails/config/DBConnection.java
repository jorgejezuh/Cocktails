package com.jorge.cocktails.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
	
	//private static final String DRIVER = "org.postgresql.Driver";
	private static final String DSN = "jdbc:postgresql://localhost:5432/"; //Data Source Name
	private static final String DBNAME = "cocktails";
	private static final String DBUSERNAME = "postgres";
	private static final String DBPASSWORD = "1234";
	
	public Connection connection () throws SQLException {
		Connection conn = null;
		Properties props = new Properties();
		props.put("user", DBUSERNAME);
		props.put("password", DBPASSWORD);
		
		try {
			conn = DriverManager.getConnection(DSN + DBNAME, props);
		} catch (SQLException e){
			
		}
		
		return conn;
	}
}