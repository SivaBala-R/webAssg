package com.display.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DisplayDetails
 */
@WebServlet("/displayDetails")
public class DisplayDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Connection conn;

	@Override
	public void init() throws ServletException {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "root");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			Statement stmt = conn.createStatement();
			ResultSet resultSet = stmt.executeQuery("select  * from users");
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.print("<table>");
			out.print("<tr>");
			out.print("<th>");
			out.println("id");
			out.print("</th>");
			out.print("<th>");
			out.println("name");
			out.print("</th>");
			out.print("<th>");
			out.println("age");
			out.print("</th>");
			out.print("<th>");
			out.println("salary");
			out.print("</th>");
			out.print("<th>");
			out.println("designation");
			out.print("</th>");
			out.print("</tr>");

			while (resultSet.next()) {
				out.println("<tr>");
				out.println("<td>");
				out.print(resultSet.getInt(1));
				out.println("</td>");
				out.println("<td>");
				out.print(resultSet.getString(2));
				out.println("</td>");
				out.println("<td>");
				out.print(resultSet.getInt(3));
				out.println("</td>");
				out.println("<td>");
				out.print(resultSet.getInt(4));
				out.println("</td>");
				out.println("<td>");
				out.print(resultSet.getString(5));
				out.println("</td>");
				out.println("</tr>");
				out.print("</table>");

			}
			out.println("<h2><a href='home.html'>Home<a></h2>");
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
