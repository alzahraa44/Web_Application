package dbConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class Regist extends HttpServlet {
	
	DbConnection dbConnection = dbConnection.gdbConnection();
	private static final long serialVersionUID = 1L;
       
   
    public Regist() {
        super();
   }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		response.sendRedirect("regiter.html");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection connection=dbConnection.getConnection();
		try {
		PreparedStatement preparedStatement=connection.preparedStatement("insert into School.students values=(?,?,?,?)");
		preparedStatement.setString(1, request.getParameter("users"));
		preparedStatement.setString(2, request.getParameter("first"));
		preparedStatement.setString(3, request.getParameter("last"));
		preparedStatement.setString(4, request.getParameter("password"));
		preparedStatement.executeUpdate();
		connection.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
			response.sendRedirect("Login");
		
		
	}
}
