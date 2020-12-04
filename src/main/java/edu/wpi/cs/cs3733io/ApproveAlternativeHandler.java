package edu.wpi.cs.cs3733io;

import java.util.LinkedList;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.wpi.cs.cs3733io.db.ApprovalDAO;
import edu.wpi.cs.cs3733io.db.DisapprovalDAO;
import edu.wpi.cs.cs3733io.http.ApproveAlternativeRequest;
import edu.wpi.cs.cs3733io.http.AllResponse;
import edu.wpi.cs.cs3733io.model.Approver;
import edu.wpi.cs.cs3733io.model.Disapprover;

public class ApproveAlternativeHandler
		implements RequestHandler<ApproveAlternativeRequest, AllResponse> {
	LambdaLogger logger;
	AllResponse response;

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
					approver.getUserName(), logger);

			if (disapprover == null) {
			    return false;
            }

            return approver.getAlternativeIndex() == disapprover.getAlternativeIndex()
                    && approver.getChoiceUuid().equals(disapprover.getChoiceUuid())
                    && approver.getUserName().equals(disapprover.getUserName());

		} catch (Exception e) {
			return true;
		}
	}

    boolean isApprover(Approver approver) {
        if (logger != null) {
            logger.log("in isDisapprover");
        }

        ApprovalDAO approvalDAO = new ApprovalDAO();

        try {
            Approver possibleApprover = approvalDAO.getApprover(approver.getChoiceUuid(), approver.getAlternativeIndex(),
                    approver.getUserName());
            if (possibleApprover == null)
                return false;

            return approver.getAlternativeIndex() == possibleApprover.getAlternativeIndex()
                    && approver.getChoiceUuid().equals(possibleApprover.getChoiceUuid())
                    && approver.getUserName().equals(possibleApprover.getUserName());

        } catch (Exception e) {
            return true;
        }
    }

	@Override
	public AllResponse handleRequest(ApproveAlternativeRequest approveRequest, Context context) {

		logger = context.getLogger();
		logger.log("Loading Java Lambda handler of Approval ");

		if (context != null) {
			context.getLogger();
		}

		Approver approver = new Approver(approveRequest.getAlternative(), approveRequest.getChoiceId(),
				approveRequest.getUser());

		try {
			if (!isDisapprover(approver) && !isApprover(approver)) {
				if (addApprover(approver)) {

					LinkedList<Approver> approvers = getApprovers(approver.getChoiceUuid(),
							approver.getAlternativeIndex());
					response = new AllResponse(approver.toString(approvers), 200);
				}
			} else {
			    if (isDisapprover(approver)) {
                    response = new AllResponse("User is on disapprove List", 400);
                } else {
                    response = new AllResponse("User is on approve List", 400);
                }

			}

		} catch (Exception e) {
			response = new AllResponse("Unable to add approver " + "(" + e.getMessage() + ")", 400);
			e.printStackTrace();
		}

		return response;

	}

}