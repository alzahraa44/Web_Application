package dbConnection;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login extends HttpServlet {
	DbConnect dbConnect = DbConnect.gdbConnect();
	private static final long serialVersionUID = 1L;

	public Login() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.html");
		requestDispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection connection = dbConnect.getConnection();

		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("select * from School.students where user=? and password=?");
			preparedStatement.getString(1, request.getParameter("user"));
			preparedStatement.getString(2, request.getParameter("password"));
			ResultSet resultSet=preparedStatement.executeQuery();
			connection.close();
		if (resultSet.next()) {
			response.sendRedirect("wel");
		}else {
			 RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.html");
             requestDispatcher.forward(request, response);
		}
			
			

		} catch (SQLException e) {
			e.printStackTrace();

		}

	}

}
