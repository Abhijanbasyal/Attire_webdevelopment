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

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import controller.dbconnection.DbConnection;
import model.Cart;
import model.CartLine;
import model.Products;
import resources.MyConstants;

public class CartDAO {
	private Connection conn;

	public CartDAO(Connection conn) {
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

	public int addCart(String query, Cart cartModel) {
		Connection dbConnection = getConnection();
		if (dbConnection != null) {
			try {
				// Get the maximum CartID from the database
				String maxId = null;
				PreparedStatement maxIdStatement = dbConnection.prepareStatement(MyConstants.MAX_CartID);
				ResultSet maxIdResult = maxIdStatement.executeQuery();
				if (maxIdResult.next()) {
					maxId = maxIdResult.getString(1);
				}
				maxIdResult.close();
				maxIdStatement.close();

				// Generate a new ProductID based on the maximum value in the database
				String cartID;
				if (maxId == null) {
					cartID = "C001";
				} else {
					int maxIdNumber = Integer.parseInt(maxId.substring(1));
					int newIdNumber = maxIdNumber + 1;
					cartID = String.format("C%03d", newIdNumber);
				}

				// Insert the new product with the generated ID
				PreparedStatement statement = dbConnection.prepareStatement(query);
				statement.setString(1, cartID);
				statement.setString(2, cartModel.getUserID());
				statement.setBoolean(3, cartModel.getIs_CheckedOut());

				int result = statement.executeUpdate();
				if (result >= 0)
					return 1;
				else
					return 0;
			} catch (Exception e) {
				System.out.println("DBConnection:");
				System.out.println(e);
				return -2;
			}
		} else {
			return -3;
		}
	}

	public String getCartID(String userID) {
		String cartID = null;
		try {
			PreparedStatement statement = conn.prepareStatement(MyConstants.GET_CARTID);
			System.out.println("DAO Called");
			statement.setString(1, userID);
			statement.executeQuery();
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				cartID = resultSet.getString("CartID");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cartID;
	}

	public boolean doesCartExists(String userID) {
		boolean cartExists = null != null;
		Connection conn = getConnection();
		System.out.println(conn);
		if (conn != null) {
			try {
				PreparedStatement statement = conn.prepareStatement(MyConstants.CART_CHECK);
				statement.setString(1, userID);
				ResultSet resultSet = statement.executeQuery();
				resultSet.next();
				int count = resultSet.getInt("count");
				if (count > 0) {
					cartExists = true;
				}
				System.out.println(count);
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return cartExists;
	}

	public int addCartLine(String query, CartLine cartLineModel) {
		Connection dbConnection = getConnection();
		if (dbConnection != null) {
			try {
				// Get the maximum CartID from the database
				String maxId = null;
				PreparedStatement maxIdStatement = dbConnection.prepareStatement(MyConstants.MAX_CartLineID);
				ResultSet maxIdResult = maxIdStatement.executeQuery();
				if (maxIdResult.next()) {
					maxId = maxIdResult.getString(1);
				}
				maxIdResult.close();
				maxIdStatement.close();

				// Generate a new ProductID based on the maximum value in the database
				String cartLineID;
				if (maxId == null) {
					cartLineID = "L001";
				} else {
					int maxIdNumber = Integer.parseInt(maxId.substring(1));
					int newIdNumber = maxIdNumber + 1;
					cartLineID = String.format("L%03d", newIdNumber);
				}

				// Insert the new product with the generated ID
				PreparedStatement statement = dbConnection.prepareStatement(query);
				statement.setString(1, cartLineID);
				statement.setString(2, cartLineModel.getUserID());
				statement.setString(3, cartLineModel.getProductID());
				statement.setString(4, cartLineModel.getCartID());
				statement.setInt(5, cartLineModel.getQuantity());

				int result = statement.executeUpdate();
				if (result >= 0)
					return 1;
				else
					return 0;
			} catch (Exception e) {
				System.out.println("DBConnection:");
				System.out.println(e);
				return -2;
			}
		} else {
			return -3;
		}
	}


}
