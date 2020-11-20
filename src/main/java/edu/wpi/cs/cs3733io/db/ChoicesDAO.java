package edu.wpi.cs.cs3733io.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.UUID;

import edu.wpi.cs.cs3733io.db.DatabaseUtil;
import edu.wpi.cs.cs3733io.model.Choice;

public class ChoicesDAO {

	java.sql.Connection conn;
	
	final String tblName = "choices";   // Exact capitalization

    public ChoicesDAO() {
    	try  {
    		conn = DatabaseUtil.connect();
    	} catch (Exception e) {
    		conn = null;
    	}
    }

    public Choice getChoice(String name) throws Exception {
        
        try {
            Choice choice = null;
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName + " WHERE name=?;");
            ps.setString(1,  name);
            ResultSet resultSet = ps.executeQuery();
            
            while (resultSet.next()) {
            	choice = generateChoice(resultSet);
            }
            resultSet.close();
            ps.close();
            
            return choice;

        } catch (Exception e) {
        	e.printStackTrace();
            throw new Exception("Failed in getting choice: " + e.getMessage());
        }
    }
    
    public boolean updateChoice(Choice choice) throws Exception {
        try {
        	String query = "UPDATE " + tblName + " SET value=? WHERE name=?;";
        	PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, choice.getUuidString());
            ps.setString(2, choice.getDescription());
            ps.setBoolean(3, choice.getIsCompleted());
            ps.setString(4, choice.getDateCompleted());
            ps.setInt(5, choice.memberCount);
            int numAffected = ps.executeUpdate();
            ps.close();
            
            return (numAffected == 1);
        } catch (Exception e) {
            throw new Exception("Failed to update report: " + e.getMessage());
        }
    }
    

    private Choice generateChoice(ResultSet resultSet) throws Exception {
        int memberCount  = resultSet.getInt("member_count");
        boolean isCompleted = resultSet.getBoolean("is_completed");
        String description = resultSet.getString("description");
        String dateCompleted = resultSet.getString("date_completed");
        String uuid = resultSet.getString("uuid");
        LinkedList<String> alternativeNames = new LinkedList<String>();
        
        return new Choice (uuid, memberCount, description, dateCompleted, isCompleted, alternativeNames);
    }
	
}
