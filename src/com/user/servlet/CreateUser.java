package com.user.servlet;

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
 * Servlet implementation class CreateUser
 */
@WebServlet("/createUser")
public class CreateUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection conn;
	private PreparedStatement stmt;
  

	@Override
	public void init() throws ServletException {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","root");
		    stmt = conn.prepareStatement("insert into users values(?,?,?,?,?)");
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
		String name = request.getParameter("name");
		int age = Integer.parseInt(request.getParameter("age"));
		int salary =Integer.parseInt(request.getParameter("salary"));
		String desig = request.getParameter("designation");
		try {
			//Statement stmt = conn.createStatement();
			//int result = stmt.executeUpdate("insert into user values('"+id+"','"+name+"','"+age+"','"+salary+"','"+desig+"')");
			
			stmt.setInt(1, id);
			stmt.setString(2, name);
			stmt.setInt(3, age);
			stmt.setInt(4, salary);
			stmt.setNString(5, desig);
			int result = stmt.executeUpdate();
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			if(result>0) {
				out.println("<b>User Registered<b>");
			}else {
				out.println("User not Resgistered");
			}
			out.println("<a href='home.html'>Home</a>");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	@Override
	public void destroy() {
		try {
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
