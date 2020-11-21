package edu.wpi.cs.cs3733io.db;

public class FeedbackDAO {

	java.sql.Connection conn;
	
	final String tblName = "feedback";   // Exact capitalization

    public FeedbackDAO() {
    	try  {
    		conn = DatabaseUtil.connect();
    	} catch (Exception e) {
    		conn = null;
    	}
    }
    
}
