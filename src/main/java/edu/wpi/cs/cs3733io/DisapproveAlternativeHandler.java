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

	@Override
	public DisapproveAlternativeResponse handleRequest(DisapproveAlternativeRequest disApproveRequest, Context context) {
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler of Disapproval ");

		if (context != null) {
			context.getLogger();
		}

		Disapprover disapprover = new Disapprover(disApproveRequest.getAlternative(), disApproveRequest.getChoiceId(),
				disApproveRequest.getUser());

		try {

			if (addDisapprover(disapprover)) {

				LinkedList<Disapprover> disapprovers = getDisapprovers(disapprover.getChoiceUuid(), disapprover.getAlternativeIndex());
				response = new DisapproveAlternativeResponse(disapprover.toString(disapprovers), 200);
			}

		} catch (Exception e) {
			response = new DisapproveAlternativeResponse("Unable to add disapprover " + "(" + e.getMessage() + ")", 400);
			e.printStackTrace();
		}

		return response;

	}

}