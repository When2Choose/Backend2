package edu.wpi.cs.cs3733io;

import java.util.LinkedList;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.wpi.cs.cs3733io.db.ApprovalDAO;
import edu.wpi.cs.cs3733io.http.RemoveApproveRequest;
import edu.wpi.cs.cs3733io.http.AllResponse;
import edu.wpi.cs.cs3733io.model.Approver;

public class RemoveApproveHandler implements RequestHandler<RemoveApproveRequest, AllResponse> {
	LambdaLogger logger;
	AllResponse response;

	/**
	 * Removes an Approver from the database.
	 * 
	 * @param approver Approver.
	 * @return Returns true if removed, false otherwise.
	 * @throws Exception
	 */
	boolean removeApprover(Approver approver) throws Exception {
		if (logger != null) {
			logger.log("in removeApprove");
		}

		ApprovalDAO approveDAO = new ApprovalDAO();

		return approveDAO.removeApprove(approver);
	}

	/**
	 * Gets all the approvers for a Choice Alternative.
	 * 
	 * @param choiceUuid       String.
	 * @param alternativeIndex Integer.
	 * @return Returns a LinkedList of Approvers.
	 * @throws Exception
	 */
	LinkedList<Approver> getApprovers(String choiceUuid, int alternativeIndex) throws Exception {
		if (logger != null) {
			logger.log("in getApprove");
		}

		ApprovalDAO approveDAO = new ApprovalDAO();

		return approveDAO.getApprovers(choiceUuid, alternativeIndex);
	}

	/**
	 * Generates a response for removing an Approver.
	 */
	@Override
	public AllResponse handleRequest(RemoveApproveRequest removeRequest, Context context) {

		logger = context.getLogger();
		logger.log("Loading Java Lambda handler of Approval ");

		if (context != null) {
			context.getLogger();
		}

		Approver approver = new Approver(removeRequest.getAlternative(), removeRequest.getChoiceId(),
				removeRequest.getUser());

		try {

			if (removeApprover(approver)) {

				LinkedList<Approver> approvers = getApprovers(approver.getChoiceUuid(), approver.getAlternativeIndex());
				response = new AllResponse(approver.toString(approvers), 200);
			}

		} catch (Exception e) {
			response = new AllResponse("Unable to remove approver " + "(" + e.getMessage() + ")", 400);
			e.printStackTrace();
		}

		return response;

	}

}
