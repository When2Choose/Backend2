package edu.wpi.cs.cs3733io.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import edu.wpi.cs.cs3733io.model.Approver;

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

	public LinkedList<Approver> getApprovers(String choiceUuid, int alternativeIndex) throws Exception {

		try {
			LinkedList<Approver> approvers = new LinkedList<Approver>();
			PreparedStatement ps = conn
					.prepareStatement("SELECT * FROM " + tblName + " WHERE choice_uuid=? AND alternative_index=?;");
			ps.setString(1, choiceUuid);
			ps.setInt(2, alternativeIndex);
			ResultSet resultSet = ps.executeQuery();

			while (resultSet.next()) {
				approvers.add(generateApprove(resultSet));
			}

			resultSet.close();
			ps.close();

			return approvers;

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Failed in getting approvers: " + e.getMessage());
		}
	}

	private Approver generateApprove(ResultSet resultSet) throws Exception {
		String userName = resultSet.getString("user_name");
		String choiceId = resultSet.getString("choice_uuid");
		int alternativeIndex = resultSet.getInt("alternative_index");
		return new Approver(alternativeIndex, choiceId, userName);
	}

}
