package edu.wpi.cs.cs3733io.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import edu.wpi.cs.cs3733io.model.Approver;
import edu.wpi.cs.cs3733io.model.Disapprover;

public class ApprovalDAO {

	java.sql.Connection conn;

	final String tblName = "approvals"; // Exact capitalization

	/**
	 * Connect to the Approvals tables on the RDS.
	 */
	public ApprovalDAO() {
		try {
			conn = DatabaseUtil.connect();
		} catch (Exception e) {
			conn = null;
		}
	}

	/**
	 * Add an approver to the database.
	 * 
	 * @param approver Approver.
	 * @return Returns true if Approver was added, false otherwise.
	 * @throws Exception
	 */
	public boolean addApprover(Approver approver) throws Exception {
		try {
			PreparedStatement ps = conn.prepareStatement(
					"INSERT INTO " + tblName + " (user_name, alternative_index, choice_uuid) VALUES (?,?,?)");
			ps.setString(1, approver.getUserName());
			ps.setInt(2, approver.getAlternativeIndex());
			ps.setString(3, approver.getChoiceUuid());
			ps.execute();
			return true;

		} catch (Exception e) {
			throw new Exception("Failed to insert approver: " + e.getMessage());
		}
	}

	/**
	 * Remove an Approver from the database.
	 * 
	 * @param approver Approver.
	 * @return Returns true if Approver was removed, false otherwise.
	 * @throws Exception
	 */
	public boolean removeApprove(Approver approver) throws Exception {
		try {
			PreparedStatement ps = conn.prepareStatement(
					"DELETE FROM " + tblName + " WHERE user_name =? AND alternative_index=? AND choice_uuid=?;");
			ps.setString(1, approver.getUserName());
			ps.setInt(2, approver.getAlternativeIndex());
			ps.setString(3, approver.getChoiceUuid());
			ps.execute();
			return true;

		} catch (Exception e) {
			throw new Exception("Failed to remove approver: " + e.getMessage());
		}
	}

	/**
	 * Gets an approver with the matching Choice, Alternative Index, and Username.
	 * 
	 * @param choiceUuid       String.
	 * @param alternativeIndex Integer.
	 * @param userName         String.
	 * @return Returns an Approver from the database.
	 * @throws Exception
	 */
	public Approver getApprover(String choiceUuid, int alternativeIndex, String userName) throws Exception {
		try {
			Approver approver = null;
			PreparedStatement ps = conn.prepareStatement(
					"SELECT * FROM " + tblName + " WHERE choice_uuid=? AND alternative_index=? AND user_name=?;");
			ps.setString(1, choiceUuid);
			ps.setInt(2, alternativeIndex);
			ps.setString(3, userName);
			ResultSet resultSet = ps.executeQuery();

			while (resultSet.next()) {
				approver = generateApprover(resultSet);
			}

			resultSet.close();
			ps.close();

			return approver;

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Failed in getting disapprover: " + e.getMessage());
		}
	}

	/**
	 * Gets the Approvers for a Choice Alterantive.
	 * 
	 * @param choiceUuid       String.
	 * @param alternativeIndex Integer.
	 * @return Returns a LinkedList of Approvers from the database.
	 * @throws Exception
	 */
	public LinkedList<Approver> getApprovers(String choiceUuid, int alternativeIndex) throws Exception {

		try {
			LinkedList<Approver> approvers = new LinkedList<Approver>();
			PreparedStatement ps = conn
					.prepareStatement("SELECT * FROM " + tblName + " WHERE choice_uuid=? AND alternative_index=?;");
			ps.setString(1, choiceUuid);
			ps.setInt(2, alternativeIndex);
			ResultSet resultSet = ps.executeQuery();

			while (resultSet.next()) {
				approvers.add(generateApprover(resultSet));
			}

			resultSet.close();
			ps.close();

			return approvers;

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Failed in getting approvers: " + e.getMessage());
		}
	}

	/**
	 * Generates an Approver from database information.
	 * 
	 * @param resultSet Resultset.
	 * @return Returns an Approver from the Resultset.
	 * @throws Exception
	 */
	private Approver generateApprover(ResultSet resultSet) throws Exception {
		String userName = resultSet.getString("user_name");
		String choiceId = resultSet.getString("choice_uuid");
		int alternativeIndex = resultSet.getInt("alternative_index");
		return new Approver(alternativeIndex, choiceId, userName);
	}

}
