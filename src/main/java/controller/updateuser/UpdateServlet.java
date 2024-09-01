package controller.updateuser;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import dao.UserDAO;
import model.User;
import resources.MyConstants;

@WebServlet(asyncSupported = true, urlPatterns = { "/UpdateServlet" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
		maxFileSize = 1024 * 1024 * 10, // 10MB
		maxRequestSize = 1024 * 1024 * 50)
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO;
	
	@Override
	public void init() throws ServletException {
		super.init();
		System.out.println("RetrieveUser initialized.");
		try {
			Class.forName(MyConstants.DRIVER_NAME);
			System.out.println("No ERROR:");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("RU ERROR:");
		}
	}
	
	@SuppressWarnings("null")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String dobString = request.getParameter("dob");
		java.sql.Date dob = null;
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date parsed = format.parse(dobString);
			dob = new java.sql.Date(parsed.getTime());
		} catch (ParseException e) {
			// handle the exception
		}
		String[] codes = request.getParameterValues("code");
		String code = (codes != null && codes.length > 0) ? codes[0] : null;
		Long phone = Long.parseLong(request.getParameter("phone"));
		String region = request.getParameter("region");
		Boolean is_admin = false;
		Boolean does_exists = true;

		System.out.println(firstName);
		System.out.println(lastName);
		System.out.println(username);
		System.out.println(password);
		System.out.println(email);
		System.out.println(dob);
		System.out.println(code);
		System.out.println(phone);
		System.out.println("Region"+region);

		Part imagePart = request.getPart("image");
		java.sql.Date dateJoined = null;
		
		System.out.println("First step");
		User userModel = new User(firstName, lastName, username, password, email, dob, code, phone, region, is_admin,
				does_exists, imagePart, dateJoined);
		// User userModel = new User(firstName, lastName, username, password, email,
		// dob, code, phone, region);

//	    String savePath = MyConstants.IMAGE_DIR_SAVE_PATH;
//	    String fileName = userModel.getImageUrlFromPart();
//	    if(!fileName.isEmpty() && fileName != null)
//    		imagePart.write(savePath + fileName);

		String savePath = MyConstants.IMAGE_DIR_SAVE_PATH;
		String fileName = userModel.getImageUrlFromPart();
		if (!fileName.isEmpty() && fileName != null)
			imagePart.write(savePath + fileName);
		
		System.out.println("Step two");
		UserDAO user = new UserDAO((Connection) getServletContext().getAttribute("dbConnection"));
		System.out.println("Step three");
		try {
			user.updateUser(userModel);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e);
		}
		request.setAttribute("registerMessage", "Successfully Registered");
		request.getRequestDispatcher("login.jsp").forward(request, response);

	}

}
