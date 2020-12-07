package edu.wpi.cs.cs3733io;

import java.util.LinkedList;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.wpi.cs.cs3733io.db.ApprovalDAO;
import edu.wpi.cs.cs3733io.db.DisapprovalDAO;
import edu.wpi.cs.cs3733io.http.AllResponse;
import edu.wpi.cs.cs3733io.http.DisapproveAlternativeRequest;
import edu.wpi.cs.cs3733io.http.AllResponse;
import edu.wpi.cs.cs3733io.model.Approver;
import edu.wpi.cs.cs3733io.model.Disapprover;

public class DisapproveAlternativeHandler implements RequestHandler<DisapproveAlternativeRequest, AllResponse> {
	LambdaLogger logger;
	AllResponse response;

	/**
	 * Adds a disapprover to the database.
	 * 
	 * @param disapprover
	 * @return
	 * @throws Exception
	 */
	boolean addDisapprover(Disapprover disapprover) throws Exception {
		if (logger != null) {
			logger.log("in addDispprove");
		}

		DisapprovalDAO disapprovalDAO = new DisapprovalDAO();

		return disapprovalDAO.addDisapprover(disapprover);
	}

	/**
	 * Gets all the disapproves for a Choice Alternative.
	 * 
	 * @param choiceUuid       String.
	 * @param alternativeIndex Integer.
	 * @return Returns a LinkedList of Disapprovers.
	 * @throws Exception
	 */
	LinkedList<Disapprover> getDisapprovers(String choiceUuid, int alternativeIndex) throws Exception {
		if (logger != null) {
			logger.log("in getDispprove");
		}

		DisapprovalDAO disapprovalDAO = new DisapprovalDAO();

		return disapprovalDAO.getDisapprovers(choiceUuid, alternativeIndex);
	}

	/**
	 * Determinss if a disapprover is already on the disapprover list.
	 * 
	 * @param disapprover Disapprover.
	 * @return Returns true if the dsiapprover is alrady on the disapprover list,
	 *         false otherwise.
	 */
	boolean isDisapprover(Disapprover disapprover) {
		if (logger != null) {
			logger.log("in isDisapprover");
		}

		DisapprovalDAO disapproveDAO = new DisapprovalDAO();

		try {
			Disapprover possibleDisapprover = disapproveDAO.getDisapprover(disapprover.getChoiceUuid(),
					disapprover.getAlternativeIndex(), disapprover.getUserName(), logger);

			if (possibleDisapprover == null) {
				return false;
			}

			return disapprover.getAlternativeIndex() == possibleDisapprover.getAlternativeIndex()
					&& disapprover.getChoiceUuid().equals(possibleDisapprover.getChoiceUuid())
					&& disapprover.getUserName().equals(possibleDisapprover.getUserName());

		} catch (Exception e) {
			return true;
		}
	}

	/**
	 * Determines if a disapprover is on the approver list.
	 * 
	 * @param disapprover Disapprover
	 * @return Returns true if on approver list, false otherwise.
	 */
	boolean isApprover(Disapprover disapprover) {
		if (logger != null) {
			logger.log("in isDisapprover");
		}

		ApprovalDAO approvalDAO = new ApprovalDAO();

		try {
			Approver possibleApprover = approvalDAO.getApprover(disapprover.getChoiceUuid(),
					disapprover.getAlternativeIndex(), disapprover.getUserName());
			if (possibleApprover == null)
				return false;

			return disapprover.getAlternativeIndex() == possibleApprover.getAlternativeIndex()
					&& disapprover.getChoiceUuid().equals(possibleApprover.getChoiceUuid())
					&& disapprover.getUserName().equals(possibleApprover.getUserName());

		} catch (Exception e) {
			return true;
		}
	}

	/**
	 * Generates a response for disapproving a Choice.
	 */
	@Override
	public AllResponse handleRequest(DisapproveAlternativeRequest disApproveRequest, Context context) {
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler of Disapproval ");

		if (context != null) {
			context.getLogger();
		}

		Disapprover disapprover = new Disapprover(disApproveRequest.getAlternative(), disApproveRequest.getChoiceId(),
				disApproveRequest.getUser());

		try {
			if (!isApprover(disapprover) && !isDisapprover(disapprover)) {
				if (addDisapprover(disapprover)) {

					LinkedList<Disapprover> disapprovers = getDisapprovers(disapprover.getChoiceUuid(),
							disapprover.getAlternativeIndex());
					response = new AllResponse(disapprover.toString(disapprovers), 200);
				}
			} else {
				response = new AllResponse("User is on approver List", 400);
			}

		} catch (Exception e) {
			response = new AllResponse("Unable to add disapprover " + "(" + e.getMessage() + ")", 400);
			e.printStackTrace();
		}

		return response;

	}

}