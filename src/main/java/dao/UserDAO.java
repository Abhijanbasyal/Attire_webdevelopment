package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.User;
import resources.MyConstants;

public class UserDAO {
	private Connection conn;

	public UserDAO(Connection conn) {
		this.conn = conn;
	}

	public List<User> getAllData(String query) {
		List<User> dataList = new ArrayList<>();

		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				User data = new User();
				data.setFirstName(rs.getString("firstName"));
				data.setLastName(rs.getString("lastName"));
				data.setUsername(rs.getString("username"));
				data.setEmail(rs.getString("email"));
				data.setDob(rs.getDate("dob"));
				data.setCode(rs.getString("CountryCode"));
				data.setPhone(rs.getLong("PhoneNumber"));
				data.setRegion(rs.getString("region"));
				data.setIs_admin(rs.getBoolean("is_admin"));
				data.setDoes_exists(rs.getBoolean("does_exists"));
				data.setImageUrlFromPart(rs.getString("profilePicture"));
				data.setDateJoined(rs.getDate("DateJoined"));

				dataList.add(data);
			}
			System.out.println("Number of users retrieved: " + dataList.size()); // debug statement
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("ERROR in USERDAO");
		}
		return dataList;
	}

	public void deleteUser(String username) {
		try {
			System.out.println(username);
			PreparedStatement statement = conn.prepareStatement(MyConstants.DELETE_USER);
			statement.setString(1, username);
			statement.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public User getUserByUsername(String username) {
		User user = null;
		try {
			PreparedStatement statement = conn.prepareStatement(MyConstants.GET_USER_BY_USERNAME);
			statement.setString(1, username);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setUserID(rs.getString("UserID"));
				user.setFirstName(rs.getString("firstName"));
				user.setLastName(rs.getString("lastName"));
				user.setUsername(rs.getString("username"));
				user.setEmail(rs.getString("email"));
				user.setDob(rs.getDate("dob"));
				user.setCode(rs.getString("CountryCode"));
				user.setPhone(rs.getLong("PhoneNumber"));
				user.setRegion(rs.getString("region"));
				user.setIs_admin(rs.getBoolean("is_admin"));
				user.setDoes_exists(rs.getBoolean("does_exists"));
				user.setImageUrlFromPart(rs.getString("ProfilePicture"));
				user.setDateJoined(rs.getDate("DateJoined"));
			}
			rs.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("ERROR in USERDAO");
		}
		return user;
	}
	
	public User getUserID(String username) {
		User user = null;
		try {
			PreparedStatement statement = conn.prepareStatement(MyConstants.GET_USER_ID);
			statement.setString(1, username);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setUserID(rs.getString("UserID"));
			}
			rs.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("ERROR in USERDAO");
		}
		return user;
	}

	public void updateUser(User user) throws SQLException {
		try {
			PreparedStatement statement = conn.prepareStatement(MyConstants.UPDATE_USER_INFO);
			statement.setString(1, user.getFirstName());
			statement.setString(2, user.getLastName());
			statement.setString(3, user.getPassword());
			statement.setDate(4, user.getDob());
			statement.setString(5, user.getCode());
			statement.setLong(6, user.getPhone());
			statement.setString(7, user.getRegion());
			statement.setString(8, user.getUsername());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("ERROR in USERDAO");
			throw e;
		}
	}
	

}
