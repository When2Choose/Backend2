package edu.wpi.cs.cs3733io.db;


public class AdminsDAO {

	java.sql.Connection conn;

	final String tblName = "admins"; // Exact capitalization

	public AdminsDAO() {
		try {
			conn = DatabaseUtil.connect();
		} catch (Exception e) {
			conn = null;
		}
	}

//	public Administrator getChoice(String uuid) throws Exception {
//
//		try {
//			Administrator admin = null;
//			PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName + " WHERE uuid=?;");
//			ps.setString(1, uuid);
//			ResultSet resultSet = ps.executeQuery();
//
//			while (resultSet.next()) {
//				admin = generateChoice(resultSet);
//			}
//			resultSet.close();
//			ps.close();
//
//			return admin;
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new Exception("Failed in getting choice: " + e.getMessage());
//		}
//	}
//
//	public boolean updateChoice(Choice choice) throws Exception {
//		try {
//			String query = "UPDATE " + tblName + " SET value=? WHERE uuid=?;";
//			PreparedStatement ps = conn.prepareStatement(query);
//			ps.setString(1, choice.getUuidString());
//			ps.setString(2, choice.getDescription());
//			ps.setBoolean(3, choice.getIsCompleted());
//			ps.setString(4, choice.getDateCompleted());
//			ps.setInt(5, choice.memberCount);
//			int numAffected = ps.executeUpdate();
//			ps.close();
//
//			return (numAffected == 1);
//		} catch (Exception e) {
//			throw new Exception("Failed to update report: " + e.getMessage());
//		}
//	}
//
//	public boolean deleteChoice(Choice choice) throws Exception {
//		try {
//			PreparedStatement ps = conn.prepareStatement("DELETE FROM " + tblName + " WHERE uuid = ?;");
//			ps.setString(1, choice.getUuidString());
//			int numAffected = ps.executeUpdate();
//			ps.close();
//
//			return (numAffected == 1);
//
//		} catch (Exception e) {
//			throw new Exception("Failed to insert choice: " + e.getMessage());
//		}
//	}
//
//	public boolean addAdministrator(Administrator choice) throws Exception {
//		try {
////			PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName + " WHERE uuid = ?;");
////			ps.setString(1, choice.getUuidString());
////			ResultSet resultSet = ps.executeQuery();
////
////			// already present?
////			while (resultSet.next()) {
////			//	Choice c = generateChoice(resultSet);
////				resultSet.close();
////				return false;
////			}
//
//			PreparedStatement ps = conn.prepareStatement("INSERT INTO " + tblName + " (uuid, description, complete, date_completed, member_count) VALUES (?,?,?,?,?)");
//			ps.setString(1, choice.getUuidString());
//			ps.setString(2, choice.getDescription());
//			ps.setBoolean(3, choice.getIsCompleted());
//			ps.setString(4, choice.getDateCompleted());
//			ps.setInt(5, choice.memberCount);
//			ps.execute();
//			return true;
//
//		} catch (Exception e) {
//			throw new Exception("Failed to insert choice: " + e.getMessage());
//		}
//	}
//
//	public List<Choice> getAllChoices() throws Exception {
//
//		List<Choice> allChoices = new ArrayList<>();
//		try {
//			Statement statement = conn.createStatement();
//			String query = "SELECT * FROM " + tblName + ";";
//			ResultSet resultSet = statement.executeQuery(query);
//
//			while (resultSet.next()) {
//				Choice c = generateChoice(resultSet);
//				allChoices.add(c);
//			}
//			resultSet.close();
//			statement.close();
//			return allChoices;
//
//		} catch (Exception e) {
//			throw new Exception("Failed in getting choices: " + e.getMessage());
//		}
//	}
//
//	private Administrator generateAdministrator(ResultSet resultSet) throws Exception {
//		int memberCount = resultSet.getInt("member_count");
//		boolean isCompleted = resultSet.getBoolean("is_completed");
//		String description = resultSet.getString("description");
//		String dateCompleted = resultSet.getString("date_completed");
//		String uuid = resultSet.getString("uuid");
//		LinkedList<String> alternativeNames = new LinkedList<String>();
//
//		return new Administrator(uuid, memberCount, description, dateCompleted, isCompleted, alternativeNames);
//	}

}