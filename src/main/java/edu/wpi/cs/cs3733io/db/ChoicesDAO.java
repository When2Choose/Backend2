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

    public Choice getConstant(String name) throws Exception {
        
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

    private Choice generateChoice(ResultSet resultSet) throws Exception {
        int memberCount  = resultSet.getInt("memberCount");
        String description = resultSet.getString("description");
        String dateCompleted = resultSet.getString("dateCompleted");
        String uuid = resultSet.getString("uuid");
        LinkedList<String> alternativeNames = new LinkedList<String>();
        
        return new Choice (uuid, memberCount, description, dateCompleted, alternativeNames);
    }
	
}
