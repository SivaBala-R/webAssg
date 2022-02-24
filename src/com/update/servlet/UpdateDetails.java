package com.update.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UpdateDetails
 */
@WebServlet("/updateDetails")
public class UpdateDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
   

	
	private Connection conn;
  

	@Override
	public void init() throws ServletException {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","root");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		int salary =Integer.parseInt(request.getParameter("salary"));
		try {
			
	        Statement stmt = conn.createStatement();
			int result = stmt.executeUpdate("update users set salary='"+salary+"' where id='"+id+"'");
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			if(result>0) {
				out.println("<h2>User Updated</h2>");
			}else {
				out.println("<h2>User not found</h2>");
			}
			out.println("<a href='home.html'>Home</a>");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	@Override
	public void destroy() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
