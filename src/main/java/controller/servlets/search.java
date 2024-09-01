package controller.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProductDAO;
import dao.UserDAO;
import model.Products;
import model.User;
import resources.MyConstants;

@WebServlet("/search")
public class search extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ProductDAO productDAO;

	public void init() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(MyConstants.DB_URL, MyConstants.DB_USER_NAME,
					MyConstants.DB_USER_PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		productDAO = new ProductDAO(conn);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String query = request.getParameter("search");
		System.out.println(query);

		List<Products> product;
		product = productDAO.searchProduct(query);
		System.out.println("SEARCH REDIRECT");

		request.setAttribute("allProducts", product);
		request.getRequestDispatcher("pages/search.jsp").forward(request, response);
		
		
	}

}
