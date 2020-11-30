package edu.wpi.cs.cs3733io.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

import edu.wpi.cs.cs3733io.model.Approver;
import edu.wpi.cs.cs3733io.model.Disapprover;

public class DisapprovalDAO {

	java.sql.Connection conn;

	final String tblName = "disapprovals"; // Exact capitalization

	public DisapprovalDAO() {
		try {
			conn = DatabaseUtil.connect();
		} catch (Exception e) {
			conn = null;
		}
	}

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

	private Disapprover generateDisapprover(ResultSet resultSet) throws Exception {
		String userName = resultSet.getString("user_name");
		String choiceId = resultSet.getString("choice_uuid");
		int alternativeIndex = resultSet.getInt("alternative_index");
		return new Disapprover(alternativeIndex, choiceId, userName);
	}

}
