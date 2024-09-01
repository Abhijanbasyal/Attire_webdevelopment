package controller.servlets;

import dao.CartDAO;
import dao.ProductDAO;
import model.Cart;
import model.CartLine;
import model.Products;
import model.User;
import resources.MyConstants;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.dbconnection.DbConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/cartController")
public class cartController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Connection getConnection() {
		try {
			Class.forName(MyConstants.DRIVER_NAME);
			Connection connection = DriverManager.getConnection(MyConstants.DB_URL, MyConstants.DB_USER_NAME,
					MyConstants.DB_USER_PASSWORD);
			return connection;
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		System.out.println("Do POST CART controller");
		System.out.println("Cart action: " + action);

		if (action == null) {
			action = "addCart";
		}

		switch (action) {
		case "addCart":
			try {
				addCart(request, response);
			} catch (ServletException | IOException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		case "listProducts":
			System.out.println("Is called");
			listProducts(request, response);
			break;
		}
	}

	public void addCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {

		RetrieveUser retireveServlet = new RetrieveUser();
		String userID = retireveServlet.getUserID(request, response);
		String productID = request.getParameter("productId");
		
		Boolean isCheckedOut= false;
		
		CartDAO cartDAO = new CartDAO(getConnection());
		boolean check = cartDAO.doesCartExists(userID);
		System.out.println(check);

		if (check == false) {
			String cartID = null;
			Cart cartModel = new Cart(cartID, userID, isCheckedOut);
			cartDAO.addCart(MyConstants.INSERT_CART, cartModel);
		}
		
		String cartID = cartDAO.getCartID(userID);
		String cartLineID = null;
		int Quantity = 1;
		
		CartLine cartLineModel = new CartLine(cartLineID, userID, productID, cartID, Quantity);
		cartDAO.addCartLine(MyConstants.INSERT_CARTLINE, cartLineModel);


		response.sendRedirect(request.getContextPath()+"/productsController?isUser=Yes");

	}

	

//	public String generateID() throws SQLException {
//		Connection dbConnection = getConnection();
//		// Get the maximum ProductID from the database
//		String maxId = null;
//		PreparedStatement maxIdStatement = dbConnection.prepareStatement(MyConstants.MAX_USERID);
//		ResultSet maxIdResult = maxIdStatement.executeQuery();
//		if (maxIdResult.next()) {
//			maxId = maxIdResult.getString(1);
//		}
//		maxIdResult.close();
//		maxIdStatement.close();
//
//		// Generate a new ProductID based on the maximum value in the database
//		String cartID;
//		if (maxId == null) {
//			cartID = "CL001";
//		} else {
//			int maxIdNumber = Integer.parseInt(maxId.substring(1));
//			int newIdNumber = maxIdNumber + 1;
//			cartID = String.format("CL%03d", newIdNumber);
//		}
//		return cartID;
//
//	}
	
	
	private void listProducts(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		RetrieveUser retireveServlet = new RetrieveUser();
		String userID = retireveServlet.getUserID(request, response);
		
		System.out.print("ajojx");
		List<Products> allCart = ProductDAO.getCartProducts(userID);
		String path;

		System.out.println("Number of Produts from Controller: " + allCart.size()); // debug statement

		path = MyConstants.LIST_CART_PRODUCTS;
		RequestDispatcher patcher = request.getRequestDispatcher(path);

		request.setAttribute("allProducts", allCart);
		patcher.forward(request, response);
//    	request.getRequestDispatcher("/home.jsp").forward(request, response);
	}

}
