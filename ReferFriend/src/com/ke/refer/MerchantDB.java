package com.ke.refer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.HashSet;



public class MerchantDB {
	private Connection conn;
	private String table_name;
	//private PreparedStatement pstmt;
	//private Statement stmt;

	public MerchantDB(Connection conn) {
		table_name = "Merchant";
		this.conn = conn;

	}
	
	public HashSet<HashMap<String, String>> getAllMerchant() {
		HashSet<HashMap<String, String>> merchant_id_name_set = new HashSet<HashMap<String, String>>();
		ResultSet rs=null;
		Statement stmt=null;
		try {
			String query = "select * from " + table_name;
			stmt = conn.createStatement();
			 rs = stmt.executeQuery(query);
			 while (rs.next()) {
				 HashMap<String, String> id_name = new HashMap<String, String>();
				 id_name.put("id", rs.getString(1));
				 id_name.put("name", rs.getString(2));
				merchant_id_name_set.add(id_name);
				}
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(rs!=null){
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(stmt!=null){
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return merchant_id_name_set;
	}
		
public HashSet checkMerchant(Merchant merchant) {
		HashSet merchant_name_set = new HashSet();
		//boolean check_result = false;
		ResultSet rs=null;
		String merchant_name=null;
		Statement stmt=null;
		//int count = 0;
		try {
			String query = "select * from " + table_name
					+ " where Upper(M_NAME) like UPPER('" + merchant.getName()
					+ "%')";
			stmt = conn.createStatement();
			 rs = stmt.executeQuery(query);
			while (rs.next()) {
				merchant_name = rs.getString(2);
				merchant_name_set.add(merchant_name);
			}
		}
			catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		finally{
			if(rs!=null){
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(stmt!=null){
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return merchant_name_set;

	}

public boolean loadMerchant(Merchant merchant) {
	boolean load_result = false;
	PreparedStatement pstmt=null;
	try {
		conn.setAutoCommit(false);
		String sql = "INSERT INTO "
				+ table_name
				+ "(M_NAME,CATEGORY,isNew, INSERT_DATE) VALUES(?,?,?,str_to_date(?,'%Y%m%d%H%i'))";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, merchant.getName());
		pstmt.setString(2, "NULL");
		pstmt.setString(3, "No");
		pstmt.setString(4, merchant.getInsert_date());
		pstmt.execute();
		conn.commit();
		load_result = true;
	} catch (Exception e) {
		e.printStackTrace();
	}finally{
		if(pstmt!=null){
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
//	try {
//		conn.close();
//	} catch (SQLException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
	
	return load_result;
	
}
}
