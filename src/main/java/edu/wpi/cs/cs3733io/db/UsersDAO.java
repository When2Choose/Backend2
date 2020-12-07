package edu.wpi.cs.cs3733io.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.wpi.cs.cs3733io.model.User;

public class UsersDAO {

	java.sql.Connection conn;

	final String tblName = "users"; // Exact capitalization

	/**
	 * Connect to the User tables on the RDS.
	 */
	public UsersDAO() {
		try {
			conn = DatabaseUtil.connect();
		} catch (Exception e) {
			conn = null;
		}
	}

	/**
	 * Gets a user from the database.
	 * 
	 * @param inputUser User.
	 * @return Returns a user from the database.
	 * @throws Exception
	 */

	public User getUser(User inputUser) throws Exception {

		try {
			User user = null;
			PreparedStatement ps = conn.prepareStatement(
					"SELECT * FROM " + tblName + " WHERE (name = ?) AND (choiceId = ?) AND (password = ?);");
			ps.setString(1, inputUser.getName());
			ps.setString(2, inputUser.getChoiceId());
			ps.setString(3, inputUser.getPassword());
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

	/**
	 * Updates a User.
	 * 
	 * @param user User.
	 * @return Returns true if User updated, false otherwise.
	 * @throws Exception
	 */
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

	/**
	 * Deletes a User from the database.
	 * 
	 * @param user User.
	 * @return Returns true if deleted, false otherwise.
	 * @throws Exception
	 */
	public boolean deleteUser(User user) throws Exception {
		try {
			PreparedStatement ps = conn
					.prepareStatement("DELETE FROM " + tblName + " WHERE (name = ?) AND (choiceId = ?);");
			ps.setString(1, user.getName());
			ps.setString(2, user.getChoiceId());
			int numAffected = ps.executeUpdate();
			ps.close();

			return (numAffected == 1);

		} catch (Exception e) {
			throw new Exception("Failed to delete user: " + e.getMessage());
		}
	}

	/**
	 * Adds a User from the database.
	 * 
	 * @param user User.
	 * @return Returns true if added, false otherwise.
	 * @throws Exception
	 */
	public boolean addUser(User user) throws Exception {
		try {

			PreparedStatement ps = conn
					.prepareStatement("INSERT INTO " + tblName + " (name, password, choiceId) VALUES (?,?,?)");
			ps.setString(1, user.getName());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getChoiceId());
			ps.execute();
			return true;

		} catch (Exception e) {
			throw new Exception("Failed to insert user: " + e.getMessage());
		}
	}

	/**
	 * Gets all the Users from the database.
	 * 
	 * @return Returns a List of all the Users.
	 * @throws Exception
	 */
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

	/**
	 * Gets the number of Users for a Choice.
	 * 
	 * @param choiceId String.
	 * @return Returns an Integer of the number of Users.
	 * @throws Exception
	 */

	public int getNumberOfUsers(String choiceId) throws Exception {

		List<User> users = new ArrayList<>();
		try {
			PreparedStatement statement = conn.prepareStatement("SELECT * FROM " + tblName + " WHERE choiceId = ?;");
			statement.setString(1, choiceId);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				User c = generateUser(resultSet);
				users.add(c);
			}
			resultSet.close();
			statement.close();
			return users.size();

		} catch (Exception e) {
			throw new Exception("Failed in getting users: " + e.getMessage());
		}
	}

	/**
	 * Generates a User.
	 * 
	 * @param resultSet Resultset.
	 * @return Returns a User from the resultset.
	 * @throws Exception
	 */
	private User generateUser(ResultSet resultSet) throws Exception {
		String name = resultSet.getString("name");
		String password = resultSet.getString("password");
		String choiceId = resultSet.getString("choiceId");

		return new User(name, password, choiceId);
	}

}
