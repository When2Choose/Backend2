package edu.wpi.cs.cs3733io.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import edu.wpi.cs.cs3733io.model.Approver;
import edu.wpi.cs.cs3733io.model.Disapprover;

public class ApprovalDAO {

	java.sql.Connection conn;

	final String tblName = "approvals"; // Exact capitalization

	public ApprovalDAO() {
		try {
			conn = DatabaseUtil.connect();
		} catch (Exception e) {
			conn = null;
		}
	}

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
	
	public Approver getApprover(String choiceUuid, int alternativeIndex, String userName) throws Exception {
		try {
			Approver approver = null;
			PreparedStatement ps = conn
					.prepareStatement("SELECT * FROM " + tblName + " WHERE choice_uuid=? AND alternative_index=? AND user_name=?;");
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

	private Approver generateApprover(ResultSet resultSet) throws Exception {
		String userName = resultSet.getString("user_name");
		String choiceId = resultSet.getString("choice_uuid");
		int alternativeIndex = resultSet.getInt("alternative_index");
		return new Approver(alternativeIndex, choiceId, userName);
	}

}
