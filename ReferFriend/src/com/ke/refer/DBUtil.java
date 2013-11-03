package com.ke.refer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

	private static String url = "jdbc:mysql://localhost:3306/ReferFriend?";
	private static String userName = "keli";
	private static String password = "1234";
	
	public Connection getDBConnection() throws SQLException{
		DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		 Connection conn = DriverManager.getConnection(url +
                 "user=" + userName + "&" + "password=" + password);
		 System.out.println("Connected to DB");
		return conn;
	}

	public void closeResource(Connection conn){
		
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		System.out.println("Connection closed");
	}
	
	
//	public static void main(String[] args) throws SQLException{
//		DBUtil db = new DBUtil();
//		db.getDBConnection();
//	}
}
