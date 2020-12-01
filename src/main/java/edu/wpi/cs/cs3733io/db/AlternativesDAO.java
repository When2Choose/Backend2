package edu.wpi.cs.cs3733io.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.UUID;

import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.Context;

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


    public boolean addAlternative(Alternative alternative) throws Exception {
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO " + tblName + " (alternative_uuid, choice_uuid, index_number, description, chosen) VALUES (?,?,?,?,?)");
            ps.setString(1, alternative.alternativeUUID.toString());
            ps.setString(2, alternative.choiceUUID);
            ps.setInt(3, alternative.index);
            ps.setString(4, alternative.name);
            ps.setBoolean(5, alternative.isChosen);
            ps.execute();
            return true;

        } catch (Exception e) {
            throw new Exception("Failed to insert alternative: " + e.getMessage());
        }
    }

    public LinkedList<Alternative> getAlternatives(String choiceUUID, Context context) throws Exception {

        try {
            LinkedList<Alternative> alternatives = new LinkedList<Alternative>();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName + " WHERE choice_uuid=?;");
            ps.setString(1, choiceUUID);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                alternatives.add(generateAlternative(resultSet));
            }

            resultSet.close();
            ps.close();

            return alternatives;

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Failed in getting alternatives: " + e.getMessage());
        }
    }

    private Alternative generateAlternative(ResultSet resultSet) throws Exception {
        String alternativeUUID = resultSet.getString("alternative_uuid");
        String choiceUuid = resultSet.getString("choice_uuid");
        int alternativeIndex = resultSet.getInt("index_number");
        String description = resultSet.getString("description");
        boolean isChosen = resultSet.getBoolean("chosen");

        Alternative alternative = new Alternative(alternativeUUID, choiceUuid, alternativeIndex, description, isChosen);
        
        ApprovalDAO approvalDao = new ApprovalDAO();
        alternative.setApproverNames(approvalDao.getApprovers(choiceUuid, alternativeIndex));
        
        DisapprovalDAO disapprovalDao = new DisapprovalDAO();
        alternative.setDisapproverNames(disapprovalDao.getDisapprovers(choiceUuid, alternativeIndex));
        
        return alternative;
    }

}
