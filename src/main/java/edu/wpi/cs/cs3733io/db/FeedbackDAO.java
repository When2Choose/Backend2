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

	public FeedbackDAO() {
		try {
			conn = DatabaseUtil.connect();
		} catch (Exception e) {
			conn = null;
		}
	}

	public boolean addFeedback(Feedback feedback) throws Exception {
		try {
			PreparedStatement ps = conn.prepareStatement("INSERT INTO " + tblName
					+ " (user_name, description, choice_uuid, alternative_index) VALUES (?,?,?,?)");
			ps.setString(1, feedback.userName);
			ps.setString(2, feedback.description);
			ps.setString(3, feedback.uuidChoice);
			ps.setInt(4, feedback.alternativeIndex);
			ps.execute();
			return true;

		} catch (Exception e) {
			throw new Exception("Failed to insert feedback: " + e.getMessage());
		}
	}
	
	
	public Feedback getFeedback(Feedback inputFeedback) throws Exception {

        try {
            Feedback feedback = null;
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName + " WHERE user_name=? AND choice_uuid=? AND alternative_index=?;");
            ps.setString(1,  inputFeedback.userName);
            ps.setString(2,  inputFeedback.uuidChoice);
            ps.setInt(3,  inputFeedback.alternativeIndex);
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
	
	public LinkedList<Feedback> getAlternativeFeedback(int alternativeIndex, String choiceUuid) throws Exception{
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
	
	
	
    private Feedback generateFeedback(ResultSet resultSet) throws Exception {
        String userName = resultSet.getString("user_name");
        String description = resultSet.getString("description");
        String uuidChoice = resultSet.getString("choice_uuid");
        int alternativeIndex = resultSet.getInt("alternative_index");

        return new Feedback(userName, description, uuidChoice, alternativeIndex);
    }

}
