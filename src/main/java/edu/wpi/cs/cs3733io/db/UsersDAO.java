package edu.wpi.cs.cs3733io.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.wpi.cs.cs3733io.model.User;

public class UsersDAO {

	java.sql.Connection conn;
	
	final String tblName = "users";   // Exact capitalization

    public UsersDAO() {
    	try  {
    		conn = DatabaseUtil.connect();
    	} catch (Exception e) {
    		conn = null;
    	}
    }
    
	public User getUser(String name, String ChoiceId) throws Exception {

		try {
			User user = null;
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName + " WHERE (name = ?) AND (choiceId = ?);");
			ps.setString(1, name);
			ps.setString(2, ChoiceId);
			ResultSet resultSet = ps.executeQuery();

			while (resultSet.next()) {
				user = generateUser(resultSet);
			}
			resultSet.close();
			ps.close();

			return user;

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Failed in getting user: " + e.getMessage());
		}
	}
	
	public boolean updateUser(User user) throws Exception {
		try {
			String query = "UPDATE " + tblName + " SET value=? WHERE (name = ?) AND (choiceId = ?);";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, user.getName());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getChoiceId());
			int numAffected = ps.executeUpdate();
			ps.close();

			return (numAffected == 1);
		} catch (Exception e) {
			throw new Exception("Failed to update user: " + e.getMessage());
		}
	}
	
	public boolean deleteUser(User user) throws Exception {
		try {
			PreparedStatement ps = conn.prepareStatement("DELETE FROM " + tblName + " WHERE (name = ?) AND (choiceId = ?);");
			ps.setString(1, user.getName());
			ps.setString(2, user.getChoiceId());
			int numAffected = ps.executeUpdate();
			ps.close();

			return (numAffected == 1);

		} catch (Exception e) {
			throw new Exception("Failed to delete user: " + e.getMessage());
		}
	}
	
	public boolean addUser(User user) throws Exception {
		try {
			
			PreparedStatement ps = conn.prepareStatement("INSERT INTO " + tblName + " (name, password, choiceId) VALUES (?,?,?)");
			ps.setString(1, user.getName());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getChoiceId());
			ps.execute();
			return true;

		} catch (Exception e) {
			throw new Exception("Failed to insert user: " + e.getMessage());
		}
	}
	
	public List<User> getAllUsers() throws Exception {

		List<User> users = new ArrayList<>();
		try {
			Statement statement = conn.createStatement();
			String query = "SELECT * FROM " + tblName + ";";
			ResultSet resultSet = statement.executeQuery(query);

			while (resultSet.next()) {
				User c = generateUser(resultSet);
				users.add(c);
			}
			resultSet.close();
			statement.close();
			return users;

		} catch (Exception e) {
			throw new Exception("Failed in getting users: " + e.getMessage());
		}
	}
	
	private User generateUser(ResultSet resultSet) throws Exception {
		String name = resultSet.getString("name");
		String password = resultSet.getString("password");
		String choiceId = resultSet.getString("choiceId");

		return new User(name, password, choiceId);
	}
    
}