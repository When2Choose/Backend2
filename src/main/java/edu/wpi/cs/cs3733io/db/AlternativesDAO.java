package edu.wpi.cs.cs3733io.db;

import java.sql.PreparedStatement;
import java.util.LinkedList;

import edu.wpi.cs.cs3733io.model.Alternative;
import edu.wpi.cs.cs3733io.model.Choice;

public class AlternativesDAO {

	java.sql.Connection conn;

	final String tblName = "alternatives"; // Exact capitalization

	public AlternativesDAO() {
		try {
			conn = DatabaseUtil.connect();
		} catch (Exception e) {
			conn = null;
		}
	}
	
	
	public static LinkedList<String> getAlternatives(String choiceUUID) {
		return null;
	}

	public boolean addAlternative(Alternative alternative) throws Exception {
		try {
			PreparedStatement ps = conn.prepareStatement("INSERT INTO " + tblName + " (alternative_uuid, choice_uuid, description, chosen) VALUES (?,?,?,?)");
			ps.setString(1, alternative.alternativeUUID.toString());
			ps.setString(2, alternative.choiceUUID);
			ps.setString(3, alternative.name);
			ps.setBoolean(4, alternative.isChosen);
			ps.execute();
			return true;

		} catch (Exception e) {
			throw new Exception("Failed to insert alternative: " + e.getMessage());
		}
	}

}
