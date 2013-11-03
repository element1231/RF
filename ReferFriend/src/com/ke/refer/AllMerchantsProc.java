package com.ke.refer;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * Servlet implementation class AllMerchantsProc
 */
public class AllMerchantsProc extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AllMerchantsProc() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DBUtil db = new DBUtil();
		Connection conn = null;
		try {
			conn = db.getDBConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MerchantDB merDB = new MerchantDB(conn);
		HashSet<HashMap<String, String>> merchant_id_name_set = new HashSet<HashMap<String, String>>();
		merchant_id_name_set = merDB.getAllMerchant();
		
		String json = setmap_to_json_string(merchant_id_name_set);
		
		System.out.println(json);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public String setmap_to_json_string(HashSet<HashMap<String, String>> hashset)
	{       
	    JSONArray json_arr=new JSONArray();
	    for (HashMap<String, String> map : hashset) {
	        JSONObject json_obj=new JSONObject();
	        for (Entry<String, String> entry : map.entrySet()) {
	            String key = entry.getKey();
	            String value = entry.getValue();
	            try {
	                json_obj.put(key,value);
	            } catch (Exception e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            }                           
	        }
	         json_arr.add(json_obj);
	    }
	    return json_arr.toString();
	}
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);
	}

}
