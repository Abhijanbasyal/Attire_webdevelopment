package controller.dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import model.PasswordEncryptionWithAes;
import model.Products;
import model.User;
import resources.MyConstants;

public class DbConnection {
	public static Connection getConnection() {
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

	// Start region Read operation
//	public List<User> getAllData(String query) {
//		List<User> dataList = new ArrayList();
//		Connection dbConnection = getConnection();
//		if (dbConnection != null) {
//			try {
//				Statement stmt = dbConnection.createStatement();
//				ResultSet rs = stmt.executeQuery(query);
//				while (rs.next()) {
//					User data = new User();
//					data.setFirstName(rs.getString("first_name"));
//					data.setLastName(rs.getString("last_name"));
//					data.setUsername(rs.getString("username"));
//					data.setEmail(rs.getString("email"));
//					data.setDob(rs.getDate("dob"));
//					data.setCode(rs.getString("code"));
//					data.setPhone(rs.getLong("phone"));
//					data.setRegion(rs.getString("region"));
//					dataList.add(data);
//				}
//				rs.close();
//				stmt.close();
//				dbConnection.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		return dataList;
//	}

	public ResultSet selectWhereQuery(String query, String id) {
		Connection dbConnection = getConnection();
		if (dbConnection != null) {
			try {
				PreparedStatement statement = dbConnection.prepareStatement(query);
				statement.setString(1, id);
				ResultSet result = statement.executeQuery();
				return result;
			} catch (SQLException e) {
				return null;
			}
		} else {
			return null;
		}
	}

	public Boolean isUserAlreadyRegistered(String username) {
		Connection dbConnection = getConnection();
		if (dbConnection != null) {
			try {
				PreparedStatement statement = dbConnection.prepareStatement(MyConstants.CHECK_LOGIN_INFO);
				statement.setString(1, username);
				ResultSet result = statement.executeQuery();
				if (result.next()) {
					return true;
				} else
					return false;
			} catch (SQLException e) {
				return null;
			}
		} else {
			return null;
		}
	}

	public Boolean isEmailExists(String email) {
		Connection dbConnection = getConnection();
		if (dbConnection != null) {
			try {
				PreparedStatement statement = dbConnection.prepareStatement(MyConstants.CHECK_EMAIL_EXISTS);
				statement.setString(1, email);
				ResultSet result = statement.executeQuery();
				if (result.next()) {
					return true;
				} else {
					return false;
				}
			} catch (SQLException e) {
				return false;
			}
		} else {
			return false;
		}
	}

	public Boolean isPhoneExists(String code, Long phoneNumber) {
		Connection dbConnection = getConnection();
		if (dbConnection != null) {
			try {
				PreparedStatement statement = dbConnection.prepareStatement(MyConstants.CHECK_PHONE_NUMBER);
				statement.setString(1, code);
				statement.setLong(2, phoneNumber);
				ResultSet result = statement.executeQuery();
				if (result.next()) {
					return true;
				} else {
					return false;
				}
			} catch (SQLException e) {
				return false;
			}
		} else {
			return false;
		}
	}

	public Boolean isUserRegistered(String query, String username, String password) {
		Connection dbConnection = getConnection();
		if (dbConnection != null) {
			try {
				PreparedStatement statement = dbConnection.prepareStatement(query);
				statement.setString(1, username);
				ResultSet result = statement.executeQuery();
				if (result.next()) {
					String userDb = result.getString("username");
					String passwordDb = result.getString("password");
					String decryptedPwd = PasswordEncryptionWithAes.decrypt(passwordDb, username);
					if (decryptedPwd != null && userDb.equals(username) && decryptedPwd.equals(password))
						return true;
					else
						return false;
				} else
					return false;
			} catch (SQLException e) {
				return null;
			}
		} else {
			return null;
		}
	}

//	public int isAdmin(String username) {
//		Connection dbConnection = getConnection();
//		if (dbConnection != null) {
//			try {
//				PreparedStatement statement = dbConnection.prepareStatement(MyConstants.IS_USER);
//				statement.setString(1, username);
//				ResultSet result = statement.executeQuery();
//				if (result.next()) {
//					String role = result.getString("role");
//					// if(role.toLowerCase() == MyConstants.ADMIN) return 1;
//					if (role.toLowerCase() == MyConstants.ADMIN)
//						return 1;
//					else
//						return 0;
//				} else
//					return -1;
//			} catch (SQLException e) {
//				return -2;
//			}
//		} else {
//			return -3;
//		}
//	}
	// End region Read operation

	// Start region Create operation
	public int registerUser(String query, User userModel) {
		Connection dbConnection = getConnection();
		if (dbConnection != null) {
			try {
				if (isUserAlreadyRegistered(userModel.getUsername()))
					return -1;
				if (isEmailExists(userModel.getEmail()))
					return -1;
				if (isPhoneExists(userModel.getCode(), userModel.getPhone()))
					return -1;
				
				// Get the maximum ProductID from the database
	            String maxId = null;
	            PreparedStatement maxIdStatement = dbConnection.prepareStatement(MyConstants.MAX_USERID);
	            ResultSet maxIdResult = maxIdStatement.executeQuery();
	            if (maxIdResult.next()) {
	                maxId = maxIdResult.getString(1);
	            }
	            maxIdResult.close();
	            maxIdStatement.close();

	            // Generate a new ProductID based on the maximum value in the database
	            String userID;
	            if (maxId == null) {
	            	userID = "U001";
	            } else {
	                int maxIdNumber = Integer.parseInt(maxId.substring(1));
	                int newIdNumber = maxIdNumber + 1;
	                userID = String.format("U%03d", newIdNumber);
	            }

				PreparedStatement statement = dbConnection.prepareStatement(query);
				statement.setString(1, userID);
				statement.setString(2, userModel.getFirstName());
				statement.setString(3, userModel.getLastName());
				statement.setString(4, userModel.getUsername());
				statement.setString(5,
						PasswordEncryptionWithAes.encrypt(userModel.getUsername(), userModel.getPassword()));
				statement.setString(6, userModel.getEmail());
				statement.setDate(7, userModel.getDob());
				statement.setString(8, userModel.getCode());
				statement.setLong(9, userModel.getPhone());
				statement.setString(10, userModel.getRegion());
				statement.setString(11, userModel.getImageUrlFromPart());
				statement.setDate(12, new java.sql.Date(Calendar.getInstance().getTime().getTime()));
//				statement.setString(10, "img");

				int result = statement.executeUpdate();
				if (result >= 0)
					return 1;
				else
					return 0;
			} catch (Exception e) {
				System.out.println("Exception:");
				System.out.println(e);
				return -2;
			}
		} else {
			return -3;
		}
	}
	// End region Create operation

	public Boolean isProductAdded(String productName) {
		Connection dbConnection = getConnection();
		if (dbConnection != null) {
			try {
				PreparedStatement statement = dbConnection.prepareStatement(MyConstants.CHECK_PRODUCT_INFO);
				statement.setString(1, productName);
				ResultSet result = statement.executeQuery();
				if (result.next()) {
					return true;
				} else {
					return false;
				}
			} catch (SQLException e) {
				return null;
			}
		} else {
			return null;
		}
	}

	public int addProduct(String query, Products productModel) {
	    Connection dbConnection = getConnection();
	    if (dbConnection != null) {
	        try {
	            if (isProductAdded(productModel.getProductName()))
	                return -1;

	         // Get the maximum ProductID from the database
	            String maxId = null;
	            PreparedStatement maxIdStatement = dbConnection.prepareStatement("SELECT MAX(ProductID) FROM products");
	            ResultSet maxIdResult = maxIdStatement.executeQuery();
	            if (maxIdResult.next()) {
	                maxId = maxIdResult.getString(1);
	            }
	            maxIdResult.close();
	            maxIdStatement.close();

	            // Generate a new ProductID based on the maximum value in the database
	            String productID;
	            if (maxId == null) {
	            	productID = "P001";
	            } else {
	                int maxIdNumber = Integer.parseInt(maxId.substring(1));
	                int newIdNumber = maxIdNumber + 1;
	                productID = String.format("P%03d", newIdNumber);
	            }

	            // Insert the new product with the generated ID
	            PreparedStatement statement = dbConnection.prepareStatement(query);
	            statement.setString(1, productID);
	            statement.setString(2, productModel.getProductName());
	            statement.setString(3, productModel.getDescription());
	            statement.setBigDecimal(4, productModel.getPrice());
	            statement.setBigDecimal(5, productModel.getDiscount());
	            statement.setLong(6, productModel.getStock());
	            statement.setString(7, productModel.getBrand());
	            statement.setString(8, productModel.getCategory());
	            statement.setBigDecimal(9, productModel.getRating());
	            statement.setString(10, productModel.getImageUrlFromPart());

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


	// Start region Update operation
	public Boolean updateUser(String query, String username) {
		Connection dbConnection = getConnection();
		if (dbConnection != null) {
			try {
				PreparedStatement statement = dbConnection.prepareStatement(query);
				statement.setString(1, username);
				int result = statement.executeUpdate();
				if (result >= 0)
					return true;
				else
					return false;
			} catch (SQLException e) {
				return null;
			}
		} else {
			return null;
		}
	}
	// End region Update operation

//	// Start region Delete operation
//	public Boolean deleteUser(String username) {
//		Connection dbConnection = getConnection();
//		if (dbConnection != null) {
//			try {
//				PreparedStatement statement = dbConnection.prepareStatement(MyConstants.DELETE_USER);
//				statement.setString(1, username);
//				int result = statement.executeUpdate();
//				if (result >= 0)
//					return true;
//				else
//					return false;
//			} catch (SQLException e) {
//				System.out.println(e);
//				return null;
//			}
//		} else {
//			return null;
//		}
//	}
//	// End region Delete operation
	
//	public Boolean deleteProduct(String query, String username) {
//		Connection dbConnection = getConnection();
//		if (dbConnection != null) {
//			try {
//				PreparedStatement statement = dbConnection.prepareStatement(query);
//				statement.setString(1, username);
//				int result = statement.executeUpdate();
//				if (result >= 0)
//					return true;
//				else
//					return false;
//			} catch (SQLException e) {
//				System.out.println(e);
//				return null;
//			}
//		} else {
//			return null;
//		}
//	}
}