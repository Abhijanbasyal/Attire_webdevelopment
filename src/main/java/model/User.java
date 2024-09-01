package model;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.Part;

import resources.MyConstants;

public class User {
	String userID, firstName, lastName, username, password, email, code, region, imageUrlFromPart, profilePicture;
	Long phone;
	java.sql.Date dob, dateJoined;
	boolean is_admin, does_exists;

	public User() {
	}

	public User(String userID, String firstName, String lastName, String username, String password, String email, java.sql.Date dob,
			String code, Long phone, String region, boolean is_admin, boolean does_exists, Part part,
			java.sql.Date dateJoined, String profilePicture) {
		// String password, String email, java.sql.Date dob, String code, String phone,
		// String region){
		this.userID = userID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.email = email;
		this.dob = dob;
		this.code = code;
		this.phone = phone;
		this.region = region;
		this.is_admin = is_admin;
		this.does_exists = does_exists;
		this.imageUrlFromPart = getImageUrl(part, username);
		this.dateJoined = dateJoined;
		this.profilePicture = profilePicture;
	}

	
	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getImageUrlFromPart() {
		return imageUrlFromPart;
	}

	public void setImageUrlFromPart(String imageUrlFromPart) {
		this.imageUrlFromPart = imageUrlFromPart;
	}

	public Long getPhone() {
		return phone;
	}

	public void setPhone(Long phone) {
		this.phone = phone;
	}

	public java.sql.Date getDob() {
		return dob;
	}

	public void setDob(java.sql.Date dob) {
		this.dob = dob;
	}

	public java.sql.Date getDateJoined() {
		return dateJoined;
	}

	public void setDateJoined(java.sql.Date dateJoined) {
		this.dateJoined = dateJoined;
	}

	public boolean isIs_admin() {
		return is_admin;
	}

	public void setIs_admin(boolean is_admin) {
		this.is_admin = is_admin;
	}

	public boolean isDoes_exists() {
		return does_exists;
	}

	public void setDoes_exists(boolean does_exists) {
		this.does_exists = does_exists;
	}

	private String getImageUrl(Part part, String username) {
		String savePath = MyConstants.IMAGE_DIR_SAVE_PATH;
		File fileSaveDir = new File(savePath);
		String imageUrlFromPart = null;
		if (!fileSaveDir.exists()) {
			fileSaveDir.mkdir();
		}
		String contentDisp = part.getHeader("content-disposition");
		String[] items = contentDisp.split(";");
		for (String s : items) {
			if (s.trim().startsWith("filename")) {
				imageUrlFromPart = s.substring(s.indexOf("=") + 2, s.length() - 1);
			}
		}
		if (imageUrlFromPart == null || imageUrlFromPart.isEmpty()) {
			imageUrlFromPart = "download.png";
		} else {
			String extension = imageUrlFromPart.substring(imageUrlFromPart.lastIndexOf("."));
			String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
			imageUrlFromPart = username + "_" + timestamp + extension;
		}
		return imageUrlFromPart;
	}

	public String getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}
	
	

}
