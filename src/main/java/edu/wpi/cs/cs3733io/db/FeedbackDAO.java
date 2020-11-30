package edu.wpi.cs.cs3733io.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import edu.wpi.cs.cs3733io.model.Alternative;
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
					+ " (username, description, uuidChoice, uuidAlternative) VALUES (?,?,?,?)");
			ps.setString(1, feedback.userName);
			ps.setString(2, feedback.description);
			ps.setString(3, feedback.uuidChoice);
			ps.setString(4, feedback.uuidAlternative);
			ps.execute();
			return true;

		} catch (Exception e) {
			throw new Exception("Failed to insert feedback: " + e.getMessage());
		}
	}
	
	
	public Feedback getFeedback(Feedback inputFeedback) throws Exception {

        try {
            Feedback feedback = null;
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName + " WHERE userName=?;");
            ps.setString(1,  inputFeedback.uuidAlternative);
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
	
	
    private Feedback generateFeedback(ResultSet resultSet) throws Exception {
        String userName = resultSet.getString("userName");
        String description = resultSet.getString("description");
        String uuidChoice = resultSet.getString("uuid_choice");
        String uuidAlternative = resultSet.getString("uuid_alternative");

        return new Feedback(userName, description, uuidChoice, uuidAlternative);
    }

}
