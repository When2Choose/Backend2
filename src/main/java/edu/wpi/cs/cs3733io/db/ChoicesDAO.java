package edu.wpi.cs.cs3733io.db;


public class ChoicesDAO {

	java.sql.Connection conn;
	
	final String tblName = "Constants";   // Exact capitalization

    public ChoicesDAO() {
    	try  {
    		conn = DatabaseUtil.connect();
    	} catch (Exception e) {
    		conn = null;
    	}
    }

	
}
