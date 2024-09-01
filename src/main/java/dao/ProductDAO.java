package dao;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import controller.dbconnection.DbConnection;
import model.Products;
import model.User;
import resources.MyConstants;

public class ProductDAO {
	private Connection conn;

	public ProductDAO(Connection conn) {

		this.conn = conn;
	}
	
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

	public List<Products> getAllData() {
		List<Products> dataList = new ArrayList<>();

		try {
			Connection conn = getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(MyConstants.GET_ALL_PRODUCT_INFO);
			while (rs.next()) {
				Products data = new Products();
				data.setProductID(rs.getString("ProductID"));
				data.setProductName(rs.getString("ProductName"));
				data.setDescription(rs.getString("Description"));
				data.setPrice(rs.getBigDecimal("Price"));
				data.setDiscount(rs.getBigDecimal("Discount"));
				data.setStock(rs.getInt("Stock"));
				data.setBrand(rs.getString("Brand"));
				data.setCategory(rs.getString("Category"));
				data.setRating(rs.getBigDecimal("Rating"));
				data.setImageUrlFromPart(rs.getString("ImageUrl"));
				data.setDoes_exists(rs.getBoolean("does_exists"));

				dataList.add(data);
			}
			System.out.println("Number of products retrieved: " + dataList.size()); // debug statement
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("ERROR in PRODUCTDAO");
		}
		return dataList;
	}

	public void deleteProduct(String productID) {
		try {
			PreparedStatement statement = conn.prepareStatement(MyConstants.DELETE_PRODUCT);
			System.out.println("DAO Called");
			statement.setString(1, productID);
			statement.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	public List<Products> searchProduct(String query){
		List<Products> product = new ArrayList<>();
		System.out.println("searchRan");
		System.out.println("inside");
		PreparedStatement statement;
		Connection conn = getConnection();
		try {
			statement = conn.prepareStatement(MyConstants.SEARCH);
			statement.setString(1, "%" + query + "%");

			ResultSet result = statement.executeQuery();

			while (result.next()) {
				Products products = new Products();
				products.setImageUrl(result.getString("imageUrl"));
				products.setProductID(result.getString("productID"));
				products.setProductName(result.getString("productName"));
				products.setRating(result.getBigDecimal("rating"));
				products.setPrice(result.getBigDecimal("price"));

				product.add(products);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return product;
	}
	
	public static List<Products> getCartProducts(String userID){
		if (userID == null) {

			return null;
		}

		List<Products> products = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DbConnection.getConnection();
			String sql = "SELECT p.ProductID, p.ProductName, p.Description, p.Price, p.Discount, p.Stock, p.Brand, p.Category, p.Rating, p.ImageUrl, p.does_exists, cl.Quantity "
					+ "FROM Cart c " + "JOIN CartLine cl ON c.CartID = cl.CartID "
					+ "JOIN Products p ON cl.ProductID = p.ProductID "
					+ "WHERE c.UserID = ? AND c.is_CheckedOut = false";

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, userID);
			rs = stmt.executeQuery();

			while (rs.next()) {
				String productId = rs.getString("ProductID");
				String productName = rs.getString("ProductName");
				String description = rs.getString("Description");
				BigDecimal price = rs.getBigDecimal("Price");
				BigDecimal discount = rs.getBigDecimal("Discount");
				int stock = rs.getInt("Stock");
				String brand = rs.getString("Brand");
				String category = rs.getString("Category");
				BigDecimal rating = rs.getBigDecimal("Rating");
				Part imageUrlFromPart = null;
				boolean doesExist = rs.getBoolean("does_exists");
				String imageUrl = rs.getString("imageURL");
				int quantity = rs.getInt("Quantity");

				Products product = new Products(productId, productName, description, price, discount, stock, brand,
						category, rating, imageUrlFromPart, doesExist, imageUrl, quantity);
				products.add(product);
			}
		} catch (SQLException e) {
			System.out.println(e);
			return null;
		}
		return products;
	}

}
