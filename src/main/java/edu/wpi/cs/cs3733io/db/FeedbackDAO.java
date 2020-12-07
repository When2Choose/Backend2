package edu.wpi.cs.cs3733io.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

import edu.wpi.cs.cs3733io.model.Alternative;
import edu.wpi.cs.cs3733io.model.Approver;
import edu.wpi.cs.cs3733io.model.Choice;
import edu.wpi.cs.cs3733io.model.Feedback;

public class FeedbackDAO {

	java.sql.Connection conn;

	final String tblName = "feedback"; // Exact capitalization

	/**
	 * Connect to the Feedback tables on the RDS.
	 */
	public FeedbackDAO() {
		try {
			conn = DatabaseUtil.connect();
		} catch (Exception e) {
			conn = null;
		}
	}

	/**
	 * Add Feedback to the database.
	 * 
	 * @param feedback Feedback.
	 * @return Returns true if the Feedback was added, false otherwise.
	 * @throws Exception
	 */
	public boolean addFeedback(Feedback feedback) throws Exception {
		try {
			PreparedStatement ps = conn.prepareStatement("INSERT INTO " + tblName
					+ " (user_name, description, choice_uuid, alternative_index, date_created) VALUES (?,?,?,?,?)");
			ps.setString(1, feedback.userName);
			ps.setString(2, feedback.description);
			ps.setString(3, feedback.uuidChoice);
			ps.setInt(4, feedback.alternativeIndex);
			ps.setString(5, feedback.dateCreated);
			ps.execute();
			return true;

		} catch (Exception e) {
			throw new Exception("Failed to insert feedback: " + e.getMessage());
		}
	}

	/**
	 * Gets Feedback from the database.
	 * 
	 * @param inputFeedback Feedback.
	 * @return Returns Feedback that matches the inputFeedback.
	 * @throws Exception
	 */
	public Feedback getFeedback(Feedback inputFeedback) throws Exception {

		try {
			Feedback feedback = null;
			PreparedStatement ps = conn.prepareStatement(
					"SELECT * FROM " + tblName + " WHERE user_name=? AND choice_uuid=? AND alternative_index=?;");
			ps.setString(1, inputFeedback.userName);
			ps.setString(2, inputFeedback.uuidChoice);
			ps.setInt(3, inputFeedback.alternativeIndex);
			ResultSet resultSet = ps.executeQuery();

			while (resultSet.next()) {
				feedback = generateFeedback(resultSet);
			}
			resultSet.close();
			ps.close();

			return feedback;

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Failed in getting feedback: " + e.getMessage());
		}
	}

	/**
	 * Gets the Feedback for a certain Choice Alternative.
	 * 
	 * @param alternativeIndex Integer.
	 * @param choiceUuid       String.
	 * @return Returns a LinkedList of Feedback for that Choice Alternative.
	 * @throws Exception
	 */
	public LinkedList<Feedback> getAlternativeFeedback(int alternativeIndex, String choiceUuid) throws Exception {
		try {
			LinkedList<Feedback> feedback = new LinkedList<Feedback>();
			PreparedStatement ps = conn
					.prepareStatement("SELECT * FROM " + tblName + " WHERE choice_uuid=? AND alternative_index=?;");
			ps.setString(1, choiceUuid);
			ps.setInt(2, alternativeIndex);
			ResultSet resultSet = ps.executeQuery();

			while (resultSet.next()) {
				feedback.add(generateFeedback(resultSet));
			}

			resultSet.close();
			ps.close();

			return feedback;

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Failed in getting approvers: " + e.getMessage());
		}
	}

	/**
	 * Generates Feedback.
	 * 
	 * @param resultSet Resultset.
	 * @return Returns Feedback generated from the Resultset.
	 * @throws Exception
	 */
	private Feedback generateFeedback(ResultSet resultSet) throws Exception {
		String userName = resultSet.getString("user_name");
		String description = resultSet.getString("description");
		String uuidChoice = resultSet.getString("choice_uuid");
		int alternativeIndex = resultSet.getInt("alternative_index");
		String dateCreated = resultSet.getNString("date_created");

		return new Feedback(userName, description, uuidChoice, alternativeIndex, dateCreated);
	}

}
