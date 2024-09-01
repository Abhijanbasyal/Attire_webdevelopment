package controller.servlets;

import dao.ProductDAO;
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

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/productsController")
public class productsController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ProductDAO ProductDAO;

	@Override
	public void init() throws ServletException {
		super.init();
		System.out.println("RetrieveUser initialized.");
		Connection conn;
		try {
			Class.forName(MyConstants.DRIVER_NAME);
			conn = DriverManager.getConnection(MyConstants.DB_URL, MyConstants.DB_USER_NAME,
					MyConstants.DB_USER_PASSWORD);
			ProductDAO = new ProductDAO(conn);
			System.out.println("No ERROR:");
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("RU ERROR:");
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		System.out.println("doGet Called.");

		if (action == null) {
			action = "listProducts";
		}

		switch (action) {
		case "listProducts":
			Connection conn = null;
			try {
				conn = DriverManager.getConnection(MyConstants.DB_URL, MyConstants.DB_USER_NAME,
						MyConstants.DB_USER_PASSWORD);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ProductDAO = new ProductDAO(conn);
			listProducts(request, response);
			break;
		case "deleteProduct":
			deleteProduct(request, response);
			break;
		default:
			listProducts(request, response);
			break;
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		System.out.println("Action " + action);
		switch (action) {
		case "deleteProduct":
			deleteProduct(request, response);
			break;
		}
	}

	private void listProducts(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Products> allProducts = ProductDAO.getAllData();
		String path;

		System.out.println("Number of Produts from Controller: " + allProducts.size()); // debug statement
		String isUser = request.getParameter("isUser");
		System.out.println(isUser);
		if (isUser != null) {
			path = MyConstants.LIST_USER_PRODUCTS;
		} else {
			path = MyConstants.LIST_ADMIN_PRODUCTS;
		}
		RequestDispatcher patcher = request.getRequestDispatcher(path);

		request.setAttribute("allProducts", allProducts);
		patcher.forward(request, response);
//    	request.getRequestDispatcher("/home.jsp").forward(request, response);
	}

	private void deleteProduct(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Controller Called");
		String productID = request.getParameter("productID");
		System.out.println(productID);
		ProductDAO.deleteProduct(productID);
		response.sendRedirect(request.getContextPath()+"/productsController");
//        listUsers(request, response);
	}
}
