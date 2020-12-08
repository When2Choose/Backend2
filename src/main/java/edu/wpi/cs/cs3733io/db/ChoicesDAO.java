package edu.wpi.cs.cs3733io.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import edu.wpi.cs.cs3733io.model.Alternative;
import edu.wpi.cs.cs3733io.model.Choice;

public class ChoicesDAO {

	java.sql.Connection conn;

	final String tblName = "choices"; // Exact capitalization

	/**
	 * Connect to the Choice tables on the RDS.
	 */
	public ChoicesDAO() {
		try {
			conn = DatabaseUtil.connect();
		} catch (Exception e) {
			conn = null;
		}
	}

	/**
	 * Gets a choice.
	 * 
	 * @param uuidString String.
	 * @return Returns a Choice with the given UUID.
	 * @throws Exception
	 */
	public Choice getChoice(String uuidString) throws Exception {

		try {
			Choice choice = null;
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName + " WHERE uuid=?;");
			ps.setString(1, uuidString);
			ResultSet resultSet = ps.executeQuery();

			while (resultSet.next()) {
				choice = generateChoice(resultSet);
			}
			resultSet.close();
			ps.close();

			return choice;

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Failed in getting choice: " + e.getMessage());
		}
	}

	/**
	 * Updates an existing choice.
	 * 
	 * @param choice Choice.
	 * @return Returns true if update in the database happened, false otherwise.
	 * @throws Exception
	 */
	public boolean updateChoice(Choice choice) throws Exception {
		try {
			String query = "UPDATE " + tblName
					+ " SET description=?, complete=?, date_completed=?, member_count=?, date_created=? WHERE uuid=?;";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, choice.getDescription());
			ps.setBoolean(2, choice.getIsCompleted());
			ps.setString(3, choice.getDateCompleted());
			ps.setInt(4, choice.memberCount);
			ps.setString(5, choice.getDateCreated());
			ps.setString(6, choice.getUuidString());
			int numAffected = ps.executeUpdate();
			ps.close();

			return (numAffected == 1);
		} catch (Exception e) {
			throw new Exception("Failed to update choice: " + e.getMessage());
		}
	}

	/**
	 * Deletes a choice from the database.
	 * 
	 * @param choice Choice.
	 * @return Returns true if Choice was deleted, false otherwise.
	 * @throws Exception
	 */
	public boolean deleteChoice(Choice choice) throws Exception {
		try {
			PreparedStatement ps = conn.prepareStatement("DELETE FROM " + tblName + " WHERE uuid = ?;");
			ps.setString(1, choice.getUuidString());
			int numAffected = ps.executeUpdate();
			ps.close();

			return (numAffected == 1);

		} catch (Exception e) {
			throw new Exception("Failed to insert choice: " + e.getMessage());
		}
	}

	/**
	 * Adds a choice to the database.
	 * 
	 * @param choice Choice.
	 * @return Returns true if choice was added, false otherwise.
	 * @throws Exception
	 */
	public boolean addChoice(Choice choice) throws Exception {
		try {

			PreparedStatement ps = conn.prepareStatement("INSERT INTO " + tblName
					+ " (uuid, description, complete, date_completed, member_count, date_created) VALUES (?,?,?,?,?,?)");
			ps.setString(1, choice.getUuidString());
			ps.setString(2, choice.getDescription());
			ps.setBoolean(3, choice.getIsCompleted());
			ps.setString(4, choice.getDateCompleted());
			ps.setInt(5, choice.memberCount);
			ps.setString(6, choice.getDateCreated());
			ps.execute();
			return true;

		} catch (Exception e) {
			throw new Exception("Failed to insert choice: " + e.getMessage());
		}
	}

	/**
	 * Gets all the Choices from the database.
	 * 
	 * @return Returns a LinkedList of Choices from the database.
	 * @throws Exception
	 */
	public LinkedList<Choice> getAllChoices() throws Exception {

		LinkedList<Choice> allChoices = new LinkedList<>();
		try {
			Statement statement = conn.createStatement();
			String query = "SELECT * FROM " + tblName + ";";
			ResultSet resultSet = statement.executeQuery(query);

			while (resultSet.next()) {
				Choice c = generateChoice(resultSet);
				allChoices.add(c);
			}
			resultSet.close();
			statement.close();
			return allChoices;

		} catch (Exception e) {
			throw new Exception("Failed in getting choices: " + e.getMessage());
		}
	}

	/**
	 * Generates a Choice.
	 * 
	 * @param resultSet Resultset.
	 * @return Returns a Choice from the Resultset.
	 * @throws Exception
	 */
	private Choice generateChoice(ResultSet resultSet) throws Exception {
		int memberCount = resultSet.getInt("member_count");
		boolean isCompleted = resultSet.getBoolean("complete");
		String description = resultSet.getString("description");
		String dateCompleted = resultSet.getString("date_completed");
		String dateCreated = resultSet.getString("date_created");
		String uuid = resultSet.getString("uuid");
		String[] alternativeNames = {};

		return new Choice(uuid, memberCount, description, dateCompleted, dateCreated, isCompleted, alternativeNames);
	}

}
