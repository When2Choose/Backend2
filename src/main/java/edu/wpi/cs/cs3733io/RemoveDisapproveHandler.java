package edu.wpi.cs.cs3733io;

import java.util.LinkedList;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.wpi.cs.cs3733io.db.DisapprovalDAO;
import edu.wpi.cs.cs3733io.http.DisapproveAlternativeResponse;
import edu.wpi.cs.cs3733io.http.RemoveDisapproveRequest;
import edu.wpi.cs.cs3733io.http.RemoveDisapproveResponse;
import edu.wpi.cs.cs3733io.model.Disapprover;

public class RemoveDisapproveHandler implements RequestHandler<RemoveDisapproveRequest, RemoveDisapproveResponse> {
	LambdaLogger logger;
	RemoveDisapproveResponse response;

	boolean removeDisapprover(Disapprover disapprover) throws Exception {
		if (logger != null) {
			logger.log("in removeDispprove");
		}

		DisapprovalDAO disapprovalDAO = new DisapprovalDAO();

		return disapprovalDAO.removeDisapprover(disapprover);
	}

	LinkedList<Disapprover> getDisapprovers(String choiceUuid, int alternativeIndex) throws Exception {
		if (logger != null) {
			logger.log("in getDispprove");
		}

		DisapprovalDAO disapprovalDAO = new DisapprovalDAO();

		return disapprovalDAO.getDisapprovers(choiceUuid, alternativeIndex);
	}

	@Override
	public RemoveDisapproveResponse handleRequest(RemoveDisapproveRequest removeRequest, Context context) {
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler of Disapproval ");

		if (context != null) {
			context.getLogger();
		}

		Disapprover disapprover = new Disapprover(removeRequest.getAlternative(), removeRequest.getChoiceId(),
				removeRequest.getUser());

		try {

			if (removeDisapprover(disapprover)) {

				LinkedList<Disapprover> disapprovers = getDisapprovers(disapprover.getChoiceUuid(),
						disapprover.getAlternativeIndex());
				response = new RemoveDisapproveResponse(disapprover.toString(disapprovers), 200);
			}

		} catch (Exception e) {
			response = new RemoveDisapproveResponse("Unable to remove disapprover " + "(" + e.getMessage() + ")",
					400);
			e.printStackTrace();
		}

		return response;

	}

}
