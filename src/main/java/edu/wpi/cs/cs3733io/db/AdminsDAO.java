package edu.wpi.cs.cs3733io.db;

public class AdminsDAO {

	java.sql.Connection conn;
	
	final String tblName = "admins";   // Exact capitalization

    public AdminsDAO() {
    	try  {
    		conn = DatabaseUtil.connect();
    	} catch (Exception e) {
    		conn = null;
    	}
    }
}
