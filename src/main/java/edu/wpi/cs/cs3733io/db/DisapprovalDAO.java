package edu.wpi.cs.cs3733io.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

import com.amazonaws.services.lambda.runtime.LambdaLogger;

import edu.wpi.cs.cs3733io.model.Approver;
import edu.wpi.cs.cs3733io.model.Disapprover;

public class DisapprovalDAO {

	java.sql.Connection conn;

	final String tblName = "disapprovals"; // Exact capitalization

	/**
	 * Connect to the Disapprovals tables on the RDS.
	 */
	public DisapprovalDAO() {
		try {
			conn = DatabaseUtil.connect();
		} catch (Exception e) {
			conn = null;
		}
	}

	/**
	 * Adds a Disapprover to the database.
	 * 
	 * @param disapprover Disapprover.
	 * @return Returns true if added, false otherwise.
	 * @throws Exception
	 */
	public boolean addDisapprover(Disapprover disapprover) throws Exception {
		try {
			PreparedStatement ps = conn.prepareStatement(
					"INSERT INTO " + tblName + " (user_name, alternative_index, choice_uuid) VALUES (?,?,?)");
			ps.setString(1, disapprover.getUserName());
			ps.setInt(2, disapprover.getAlternativeIndex());
			ps.setString(3, disapprover.getChoiceUuid());
			ps.execute();
			return true;

		} catch (Exception e) {
			throw new Exception("Failed to insert disapprovers: " + e.getMessage());
		}
	}

	/**
	 * Removes a Disapprover from the database.
	 * 
	 * @param disapprover Disapprover.
	 * @return Returns true if Disapprover removed, false otherwise.
	 * @throws Exception
	 */
	public boolean removeDisapprover(Disapprover disapprover) throws Exception {
		try {
			PreparedStatement ps = conn.prepareStatement(
					"DELETE FROM " + tblName + " WHERE user_name =? AND alternative_index=? AND choice_uuid=?;");
			ps.setString(1, disapprover.getUserName());
			ps.setInt(2, disapprover.getAlternativeIndex());
			ps.setString(3, disapprover.getChoiceUuid());
			ps.execute();
			return true;

		} catch (Exception e) {
			throw new Exception("Failed to remove disapprover: " + e.getMessage());
		}
	}

	/**
	 * Gets a Disapprover from the database.
	 * 
	 * @param choiceUuid       String.
	 * @param alternativeIndex Integer.
	 * @param userName         String.
	 * @param logger           LambdaLogger.
	 * @return Returns a Disapprober.
	 * @throws Exception
	 */
	public Disapprover getDisapprover(String choiceUuid, int alternativeIndex, String userName, LambdaLogger logger)
			throws Exception {
		try {
			Disapprover disapprover = null;
			PreparedStatement ps = conn.prepareStatement(
					"SELECT * FROM " + tblName + " WHERE choice_uuid=? AND alternative_index=? AND user_name=?;");
			ps.setString(1, choiceUuid);
			ps.setInt(2, alternativeIndex);
			ps.setString(3, userName);
			ResultSet resultSet = ps.executeQuery();

			while (resultSet.next()) {
				disapprover = generateDisapprover(resultSet);
			}

			resultSet.close();
			ps.close();
			logger.log("successful disapprove search");
			return disapprover;

		} catch (Exception e) {
			e.printStackTrace();
			logger.log(e.getMessage());
			throw new Exception("Failed in getting disapprover: " + e.getMessage());
		}
	}

	/**
	 * Gets the Disapprovers for a Choice Alternative.
	 * 
	 * @param choiceUuid       String.
	 * @param alternativeIndex Integer.
	 * @return Returns a LinkedList of Disapprovers for the Choice Alternative.
	 * @throws Exception
	 */
	public LinkedList<Disapprover> getDisapprovers(String choiceUuid, int alternativeIndex) throws Exception {

		try {
			LinkedList<Disapprover> disapprovers = new LinkedList<Disapprover>();
			PreparedStatement ps = conn
					.prepareStatement("SELECT * FROM " + tblName + " WHERE choice_uuid=? AND alternative_index=?;");
			ps.setString(1, choiceUuid);
			ps.setInt(2, alternativeIndex);
			ResultSet resultSet = ps.executeQuery();

			while (resultSet.next()) {
				disapprovers.add(generateDisapprover(resultSet));
			}

			resultSet.close();
			ps.close();

			return disapprovers;

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Failed in getting disapprovers: " + e.getMessage());
		}
	}

	/**
	 * Generates a Disapprover.
	 * 
	 * @param resultSet Resultset.
	 * @return Returns a Disapprover from the resultset.
	 * @throws Exception
	 */
	private Disapprover generateDisapprover(ResultSet resultSet) throws Exception {
		String userName = resultSet.getString("user_name");
		String choiceId = resultSet.getString("choice_uuid");
		int alternativeIndex = resultSet.getInt("alternative_index");
		return new Disapprover(alternativeIndex, choiceId, userName);
	}

}
