package controller.servlets;

import dao.UserDAO;
import model.User;
import resources.MyConstants;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import controller.dbconnection.DbConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@WebServlet("/RetrieveUser")
public class RetrieveUser extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO;

	@Override
	public void init() throws ServletException {
		super.init();
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(MyConstants.DB_URL, MyConstants.DB_USER_NAME,
					MyConstants.DB_USER_PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		userDAO = new UserDAO(conn);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		System.out.println("Action " + action);
		System.out.println("doGet Called.");

		Connection conn = null;
		try {
			conn = DriverManager.getConnection(MyConstants.DB_URL, MyConstants.DB_USER_NAME,
					MyConstants.DB_USER_PASSWORD);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		userDAO = new UserDAO(conn);

		if (action == null) {
			action = "listUsers";
		}

		switch (action) {
		case "listUsers":
			listUsers(request, response, MyConstants.GET_ALL_USER_INFO);
			break;
		case "addProducts":
			response.sendRedirect(request.getContextPath() + "/pages/addProducts.jsp");
			break;
		case "getSingleUser":
			getSingleUser(request, response);
			break;
		case "getUserID":
			getUserID(request, response);
			break;
		default:
			listUsers(request, response, MyConstants.GET_ALL_USER_INFO);
			break;

		}
	}

	private void listUsers(HttpServletRequest request, HttpServletResponse response, String query)
			throws ServletException, IOException {
		List<User> allUsers = userDAO.getAllData(query);

		System.out.println("Number of users: " + allUsers.size()); // debug statement
		RequestDispatcher patcher = request.getRequestDispatcher(MyConstants.LIST_USER);

		request.setAttribute("allUsers", allUsers);
		patcher.forward(request, response);
//    	request.getRequestDispatcher("/home.jsp").forward(request, response);
	}

	private void deleteUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		System.out.println(username);
		userDAO.deleteUser(username);
		response.sendRedirect(request.getContextPath() + "/RetrieveUser?action=listUsers");
//        listUsers(request, response);
	}

	private void getSingleUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		User user = userDAO.getUserByUsername(username);
		System.out.println(user);
		request.setAttribute("user", user);
		RequestDispatcher rd = request.getRequestDispatcher("/pages/update.jsp");
		rd.forward(request, response);
	}

	public String getUserID(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Connection conn = null;
		try {
			conn = DriverManager.getConnection(MyConstants.DB_URL, MyConstants.DB_USER_NAME,
					MyConstants.DB_USER_PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		userDAO = new UserDAO(conn);

		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("user");
		System.out.println("getUserID: " + username);
		User user = userDAO.getUserID(username);
		return user.getUserID();
	}
	

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		System.out.println("Action " + action);
		switch (action) {
		case "deleteUser":
			deleteUser(request, response);
			break;
		}
	}

}
