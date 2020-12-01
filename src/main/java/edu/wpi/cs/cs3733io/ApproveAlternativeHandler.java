package edu.wpi.cs.cs3733io;

import java.util.LinkedList;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.wpi.cs.cs3733io.db.ApprovalDAO;
import edu.wpi.cs.cs3733io.db.DisapprovalDAO;
import edu.wpi.cs.cs3733io.http.ApproveAlternativeRequest;
import edu.wpi.cs.cs3733io.http.ApproveAlternativeResponse;
import edu.wpi.cs.cs3733io.model.Approver;
import edu.wpi.cs.cs3733io.model.Disapprover;

public class ApproveAlternativeHandler
		implements RequestHandler<ApproveAlternativeRequest, ApproveAlternativeResponse> {
	LambdaLogger logger;
	ApproveAlternativeResponse response;

	boolean addApprover(Approver approver) throws Exception {
		if (logger != null) {
			logger.log("in addApprove");
		}

		ApprovalDAO approveDAO = new ApprovalDAO();

		return approveDAO.addApprover(approver);
	}

	LinkedList<Approver> getApprovers(String choiceUuid, int alternativeIndex) throws Exception {
		if (logger != null) {
			logger.log("in getApprove");
		}

		ApprovalDAO approveDAO = new ApprovalDAO();

		return approveDAO.getApprovers(choiceUuid, alternativeIndex);
	}

	boolean isDisapprover(Approver approver) {
		if (logger != null) {
			logger.log("in isDisapprover");
		}

		DisapprovalDAO disapproveDAO = new DisapprovalDAO();

		try {
			Disapprover disapprover = disapproveDAO.getDisapprover(approver.getChoiceUuid(), approver.getAlternativeIndex(),
					approver.getUserName());

			if (approver.getAlternativeIndex() == disapprover.getAlternativeIndex()
					&& approver.getChoiceUuid().equals(disapprover.getChoiceUuid())
					&& approver.getUserName().equals(disapprover.getUserName())) {
				return false;
			} else
				return true;

		} catch (Exception e) {
			return true;
		}

	}

	@Override
	public ApproveAlternativeResponse handleRequest(ApproveAlternativeRequest approveRequest, Context context) {

		logger = context.getLogger();
		logger.log("Loading Java Lambda handler of Approval ");

		if (context != null) {
			context.getLogger();
		}

		Approver approver = new Approver(approveRequest.getAlternative(), approveRequest.getChoiceId(),
				approveRequest.getUser());

		try {
			if (isDisapprover(approver)) {
				if (addApprover(approver)) {

					LinkedList<Approver> approvers = getApprovers(approver.getChoiceUuid(),
							approver.getAlternativeIndex());
					response = new ApproveAlternativeResponse(approver.toString(approvers), 200);
				}
			} else {
				response = new ApproveAlternativeResponse("User is on disapprove List", 400);
			}

		} catch (Exception e) {
			response = new ApproveAlternativeResponse("Unable to add approver " + "(" + e.getMessage() + ")", 400);
			e.printStackTrace();
		}

		return response;

	}

}