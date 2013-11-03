package com.ke.refer;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class MerchantProc
 */
public class MerchantProc extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MerchantProc() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		String merchant_name_pre = request.getParameter("merchant");
		String email = request.getParameter("email");
		
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmm");
		Calendar cal = Calendar.getInstance();
		String insert_datetime = dateFormat.format(cal.getTime());
		
		Merchant merchant = new Merchant();
		merchant.setName(merchant_name_pre);
		merchant.setInsert_date(insert_datetime);
		
		DBUtil db = new DBUtil();
		Connection conn = null;
		
		try {
			conn = db.getDBConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HashSet merchant_name_set = null;
		MerchantDB merDB = new MerchantDB(conn);
		merchant_name_set = merDB.checkMerchant(merchant);
		
		System.out.println(merchant_name_set);
		
		if(merchant_name_set.isEmpty()	){
			Boolean load_result_merchant = merDB.loadMerchant(merchant);
			if(load_result_merchant){
				System.out.println("load success");
			}
			else{
				System.out.println("load fail");
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);
	}

}
