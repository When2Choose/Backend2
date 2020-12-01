package edu.wpi.cs.cs3733io;

import java.util.LinkedList;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.wpi.cs.cs3733io.db.ApprovalDAO;
import edu.wpi.cs.cs3733io.db.DisapprovalDAO;
import edu.wpi.cs.cs3733io.http.ApproveAlternativeResponse;
import edu.wpi.cs.cs3733io.http.DisapproveAlternativeRequest;
import edu.wpi.cs.cs3733io.http.DisapproveAlternativeResponse;
import edu.wpi.cs.cs3733io.model.Approver;
import edu.wpi.cs.cs3733io.model.Disapprover;

public class DisapproveAlternativeHandler
		implements RequestHandler<DisapproveAlternativeRequest, DisapproveAlternativeResponse> {
	LambdaLogger logger;
	DisapproveAlternativeResponse response;

	boolean addDisapprover(Disapprover disapprover) throws Exception {
		if (logger != null) {
			logger.log("in addDispprove");
		}

		DisapprovalDAO disapprovalDAO = new DisapprovalDAO();

		return disapprovalDAO.addDisapprover(disapprover);
	}

	LinkedList<Disapprover> getDisapprovers(String choiceUuid, int alternativeIndex) throws Exception {
		if (logger != null) {
			logger.log("in getDispprove");
		}

		DisapprovalDAO disapprovalDAO = new DisapprovalDAO();

		return disapprovalDAO.getDisapprovers(choiceUuid, alternativeIndex);
	}

	boolean isApprover(Disapprover disapprover) {
		if (logger != null) {
			logger.log("in isApprover");
		}

		ApprovalDAO approveDAO = new ApprovalDAO();

		try {
			Approver approver = approveDAO.getApprover(disapprover.getChoiceUuid(), disapprover.getAlternativeIndex(),
					disapprover.getUserName());

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
	public DisapproveAlternativeResponse handleRequest(DisapproveAlternativeRequest disApproveRequest,
			Context context) {
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler of Disapproval ");

		if (context != null) {
			context.getLogger();
		}

		Disapprover disapprover = new Disapprover(disApproveRequest.getAlternative(), disApproveRequest.getChoiceId(),
				disApproveRequest.getUser());

		try {
			if (isApprover(disapprover)) {
				if (addDisapprover(disapprover)) {

					LinkedList<Disapprover> disapprovers = getDisapprovers(disapprover.getChoiceUuid(),
							disapprover.getAlternativeIndex());
					response = new DisapproveAlternativeResponse(disapprover.toString(disapprovers), 200);
				}
			} else {
				response = new DisapproveAlternativeResponse("User is on approver List", 400);
			}

		} catch (Exception e) {
			response = new DisapproveAlternativeResponse("Unable to add disapprover " + "(" + e.getMessage() + ")",
					400);
			e.printStackTrace();
		}

		return response;

	}

}