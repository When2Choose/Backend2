package edu.wpi.cs.cs3733io.db;

public class UsersDAO {

	java.sql.Connection conn;
	
	final String tblName = "users";   // Exact capitalization

    public UsersDAO() {
    	try  {
    		conn = DatabaseUtil.connect();
    	} catch (Exception e) {
    		conn = null;
    	}
    }
    
}
