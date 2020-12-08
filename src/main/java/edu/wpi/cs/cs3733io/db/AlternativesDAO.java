package edu.wpi.cs.cs3733io.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.UUID;

import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.Context;

import edu.wpi.cs.cs3733io.model.Alternative;
import edu.wpi.cs.cs3733io.model.Choice;

public class AlternativesDAO {

	java.sql.Connection conn;

	final String tblName = "alternatives"; // Exact capitalization

	/**
	 * Connect to the Alternatives tables on the RDS.
	 */
	public AlternativesDAO() {
		try {
			conn = DatabaseUtil.connect();
		} catch (Exception e) {
			conn = null;
		}
	}

	/**
	 * Determines if an alternative was added to the database.
	 * 
	 * @param alternative Alternative.
	 * @return Returns true if the alternative was added, false otherwise.
	 * @throws Exception
	 */
	public boolean addAlternative(Alternative alternative) throws Exception {
		try {
			PreparedStatement ps = conn.prepareStatement("INSERT INTO " + tblName
					+ " (alternative_uuid, choice_uuid, index_number, description, chosen) VALUES (?,?,?,?,?)");
			ps.setString(1, alternative.alternativeUUID.toString());
			ps.setString(2, alternative.choiceUUID);
			ps.setInt(3, alternative.index);
			ps.setString(4, alternative.name);
			ps.setBoolean(5, alternative.getIsChosen());
			ps.execute();
			return true;

		} catch (Exception e) {
			throw new Exception("Failed to insert alternative: " + e.getMessage());
		}
	}

	/**
	 * Determines the alternatives for a Choice.
	 * 
	 * @param choiceUUID String.
	 * @param context    Context.
	 * @return Returns a Linked List of Alternatives for a Choice.
	 * @throws Exception
	 */
	public LinkedList<Alternative> getAlternatives(String choiceUUID, Context context) throws Exception {

		try {
			LinkedList<Alternative> alternatives = new LinkedList<Alternative>();
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName + " WHERE choice_uuid=?;");
			ps.setString(1, choiceUUID);
			ResultSet resultSet = ps.executeQuery();

			while (resultSet.next()) {
				alternatives.add(generateAlternative(resultSet));
			}

			resultSet.close();
			ps.close();

			return alternatives;

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Failed in getting alternatives: " + e.getMessage());
		}
	}

	/**
	 * Sets an Alternative to be chosen.
	 * 
	 * @param alternativeIndex Integer.
	 * @param choiceUuid       String.
	 * @return Returns true if set to chosen, false otherwise.
	 * @throws Exception
	 */
	public boolean setChoseAlternative(int alternativeIndex, String choiceUuid) throws Exception {
		try {
			String query = "UPDATE " + tblName + " SET chosen=? WHERE index_number=? AND choice_uuid=?;";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setBoolean(1, true);
			ps.setInt(2, alternativeIndex);
			ps.setString(3, choiceUuid);

			int numAffected = ps.executeUpdate();
			ps.close();

			return (numAffected == 1);
		} catch (Exception e) {
			throw new Exception("Failed to update alternative: " + e.getMessage());
		}
	}

	/**
	 * Gets an alterantive.
	 * 
	 * @param index      Integer.
	 * @param uuidChoice String.
	 * @return Returns the Alternative that maches that Choice and Index.
	 * @throws Exception
	 */
	public Alternative getAlternative(int index, String uuidChoice) throws Exception {
		try {
			Alternative alternative = null;
			PreparedStatement ps = conn
					.prepareStatement("SELECT * FROM " + tblName + " WHERE choice_uuid=? AND index_number=?;");
			ps.setString(1, uuidChoice);
			ps.setInt(2, index);
			ResultSet resultSet = ps.executeQuery();

			while (resultSet.next()) {
				alternative = generateAlternative(resultSet);
			}

			resultSet.close();
			ps.close();

			return alternative;

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Failed in getting alternative: " + e.getMessage());
		}
	}

	/**
	 * Generates a Result from database information.
	 * 
	 * @param resultSet Resultset.
	 * @return Returns an Alternative from the database information.
	 * @throws Exception
	 */
	public Alternative generateAlternative(ResultSet resultSet) throws Exception {
		String alternativeUUID = resultSet.getString("alternative_uuid");
		String choiceUuid = resultSet.getString("choice_uuid");
		int alternativeIndex = resultSet.getInt("index_number");
		String description = resultSet.getString("description");
		boolean isChosen = resultSet.getBoolean("chosen");

		Alternative alternative = new Alternative(alternativeUUID, choiceUuid, alternativeIndex, description, isChosen);

		// Get approvers
		ApprovalDAO approvalDao = new ApprovalDAO();
		alternative.setApproverNames(approvalDao.getApprovers(choiceUuid, alternativeIndex));

		// Get disapprovers
		DisapprovalDAO disapprovalDao = new DisapprovalDAO();
		alternative.setDisapproverNames(disapprovalDao.getDisapprovers(choiceUuid, alternativeIndex));

		// Get feedback
		FeedbackDAO feedbackDAO = new FeedbackDAO();
		alternative.setFeedback(feedbackDAO.getAlternativeFeedback(alternativeIndex, choiceUuid));

		return alternative;
	}

}
