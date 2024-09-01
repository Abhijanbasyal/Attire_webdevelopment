package controller.servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import controller.dbconnection.DbConnection;
import model.User;
import resources.MyConstants;


@WebServlet(asyncSupported = true, urlPatterns = { "/UserRegister" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
	maxFileSize = 1024 * 1024 * 10, // 10MB
	maxRequestSize = 1024 * 1024 * 50)
public class UserRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userID = null;
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
		Long  phone = Long.parseLong(request.getParameter("phone"));
		String  region = request.getParameter("region");
		Boolean is_admin = null;
		Boolean does_exists = null;
		
		System.out.println(firstName);
		System.out.println(lastName);
		System.out.println(username);
		System.out.println(password);
		System.out.println(email);
		System.out.println(dob);
		System.out.println(code);
		System.out.println(phone);
		System.out.println(region);
		
		
		Part imagePart = request.getPart("image");
		java.sql.Date dateJoined = null;
		String profilePicture=null;
		
		User userModel = new User(userID, firstName, lastName, username, password, email, dob, code, phone, region, is_admin, does_exists, imagePart, dateJoined, profilePicture);
		//User userModel = new User(firstName, lastName, username, password, email, dob, code, phone, region);
		
//	    String savePath = MyConstants.IMAGE_DIR_SAVE_PATH;
//	    String fileName = userModel.getImageUrlFromPart();
//	    if(!fileName.isEmpty() && fileName != null)
//    		imagePart.write(savePath + fileName);
	    
	    String savePath = MyConstants.IMAGE_DIR_SAVE_PATH;
	    String fileName = userModel.getImageUrlFromPart();
	    if (!fileName.isEmpty() && fileName != null)
	        imagePart.write(savePath + fileName);

		
		DbConnection con = new DbConnection();
		int result = con.registerUser(MyConstants.USER_REGISTER, userModel);
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
