package resources;

import java.io.File;

public class MyConstants {
	// Start Region: Database Configuration
	public static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
	public static final String DB_URL = "jdbc:mysql://localhost:3306/attire";
	public static final String DB_USER_NAME = "root"; 		
	public static final String DB_USER_PASSWORD= ""; 
	
	public static final String ADMIN = "admin";
	public static final String USER = "user";
	
	
	public static final String IMAGE_DIR = "xampp\\tomcat\\webapps\\images\\";
	public static final String IMAGE_DIR_SAVE_PATH = "C:" + File.separator + IMAGE_DIR+"Users\\";
	public static final String PRODUCT_IMAGE_DIR_SAVE_PATH = "C:" + File.separator + IMAGE_DIR+"Products\\";
	public static final String MAIN_PATH = "/attire";

	
//	public static final String IMAGE_DIR = "xampp/tomcat/webapps/images/";
//	public static final String IMAGE_DIR_SAVE_PATH = "/Users/ryanlepcha/Library/Tomcat/apache-tomcat-8.5.86/webapps" + IMAGE_DIR;

	// End Region: Database Configuration
	
	// Start Region: Select Query
	//public static final String CHECK_LOGIN_INFO = "SELECT username, password FROM Register WHERE username = ?";
	public static final String CHECK_LOGIN_INFO = "SELECT username, password FROM Register WHERE username = ? AND does_exists = 1";
	public static final String CHECK_ADMIN_LOGIN_INFO = "SELECT username, password FROM Register WHERE username = ? AND does_exists = 1 AND is_admin = 1";
	public static final String CHECK_PRODUCT_INFO = "SELECT productName FROM PGET_USER_BY_USERNAMEroducts WHERE productName = ?";
	public static final String CHECK_EMAIL_EXISTS = "SELECT email FROM Register WHERE email = ? AND does_exists = 1";
	public static final String CHECK_PHONE_NUMBER = "SELECT phonenumber FROM Register WHERE countryCode = ? AND phoneNumber = ? AND does_exists = 1";


	public static final String GET_ALL_USER_INFO = "SELECT * FROM register WHERE does_exists=1";
	public static final String GET_USER_BY_USERNAME = "SELECT * FROM register WHERE username = ? AND does_exists=1";
	public static final String GET_ALL_INFO_BY_ID = "SELECT * FROM  WHERE id = ?";
	public static final String GET_USER_ID = "SELECT UserID FROM register WHERE username = ?";
	
	public static final String GET_ALL_PRODUCT_INFO = "SELECT * FROM products WHERE does_exists=1";
	
	// Start Region: Insert Query
	public static final String USER_REGISTER = "INSERT INTO Register"
			+ "(UserID, FirstName, LastName, Username, Password, Email, DOB, CountryCode, PhoneNumber, Region, ProfilePicture, DateJoined)"
			+ " VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
	public static final String INSERT_PRODUCT = "INSERT INTO Products"
			+ "(ProductID, ProductName, Description, Price, Discount, Stock, Brand, Category, Rating, ImageUrl)"
			+ " VALUES(?,?,?,?,?,?,?,?,?,?)";
	public static final String INSERT_CART = "INSERT INTO Cart"
			+ "(CartID, UserID, is_CheckedOut)"
			+ " VALUES(?,?,?)";
	public static final String INSERT_CARTLINE = "INSERT INTO CartLine"
			+ "(CartLineID, UserID, ProductID, CartID, Quantity)"
			+ " VALUES(?,?,?,?,?)";
	// End Region: Insert Query
	
	// Start Region: Update Query
	public static final String UPDATE_USER_INFO = "UPDATE users SET firstName=?, lastName=?, password=?, dob=?, code=?, phone=?, region=? WHERE username=?";
	// End Region: Update Query
		
	// Start Region: Delete Query
	public static final String DELETE_USER = "UPDATE register SET does_exists=0 WHERE username=?";
	public static final String DELETE_PRODUCT = "UPDATE products SET does_exists=0 WHERE productID=?";
	// End Region: Delete Query
	
	public static final String LIST_USER = "pages/viewUsers.jsp";
	public static final String LIST_ADMIN_PRODUCTS = "pages/viewProducts.jsp";
	public static final String LIST_USER_PRODUCTS = "pages/products.jsp";
	public static final String LIST_CART_PRODUCTS = "pages/cart.jsp";
	
	public static final String MAX_USERID = "SELECT MAX(UserID) FROM register";
	public static final String MAX_CartID = "SELECT MAX(CartID) FROM cart";
	public static final String MAX_CartLineID = "SELECT MAX(CartLineID) FROM cartline";
	
	public static final String CART_CHECK = "SELECT COUNT(*) AS count FROM cart WHERE UserID=? AND is_CheckedOut = 0";
	public static final String GET_CARTID = "SELECT CartID FROM cart WHERE UserID=? AND is_CheckedOut = 0";

	public static final String SEARCH = "SELECT * FROM products WHERE ProductName LIKE ?";
}
