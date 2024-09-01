package controller.servlets;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import controller.dbconnection.DbConnection;
import model.Products;
import model.User;
import resources.MyConstants;

@WebServlet(asyncSupported = true, urlPatterns = { "/addProducts" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
	maxFileSize = 1024 * 1024 * 10, // 10MB
	maxRequestSize = 1024 * 1024 * 50)
public class addProducts extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String productID = null;
		String productName = request.getParameter("productName");
		String description = request.getParameter("description");
		BigDecimal price = new BigDecimal(request.getParameter("price"));
		BigDecimal discount = new BigDecimal(request.getParameter("discount"));
		int stock = Integer.parseInt(request.getParameter("stock"));
		String brand = request.getParameter("brand");
		String category = request.getParameter("category");
		BigDecimal rating = new BigDecimal(request.getParameter("rating"));
		Boolean does_exists = null;


		Part imagePart = request.getPart("productImage"); 
		String imageUrl = null;
		int quantity = 0;
		Products productModel = new Products(productID, productName, description, price, discount, stock, brand, category, rating, imagePart, does_exists, imageUrl, quantity);

		 String savePath = MyConstants.PRODUCT_IMAGE_DIR_SAVE_PATH;
		    String fileName = productModel.getImageUrlFromPart();
		    if (!fileName.isEmpty() && fileName != null)
		        imagePart.write(savePath + fileName);

			
			DbConnection con = new DbConnection();
			int result = con.addProduct(MyConstants.INSERT_PRODUCT, productModel);
			if(result == 1) {
				request.setAttribute("registerMessage", "Successfully Registered");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}else if(result == -1) {
				request.setAttribute("registerMessage", "User Already Exists");
				request.getRequestDispatcher("/pages/register.jsp").forward(request, response);
			}else {
				request.getRequestDispatcher("/pages/register.jsp").forward(request, response);
			}
			
		}

	}