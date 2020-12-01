package edu.wpi.cs.cs3733io;

import java.util.LinkedList;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.wpi.cs.cs3733io.db.AlternativesDAO;
import edu.wpi.cs.cs3733io.db.ApprovalDAO;
import edu.wpi.cs.cs3733io.db.DisapprovalDAO;
import edu.wpi.cs.cs3733io.http.ApproveAlternativeRequest;
import edu.wpi.cs.cs3733io.http.ApproveAlternativeResponse;
import edu.wpi.cs.cs3733io.model.Alternative;
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

            return approver.getAlternativeIndex() != disapprover.getAlternativeIndex()
                    || !approver.getChoiceUuid().equals(disapprover.getChoiceUuid())
                    || !approver.getUserName().equals(disapprover.getUserName());

        } catch (Exception e) {
            return true;
        }
    }

    private boolean alternativeExists(ApproveAlternativeRequest approveAlternativeRequest, Context context) {
        AlternativesDAO alternativesDAO = new AlternativesDAO();
        try {
            Alternative alternative = alternativesDAO.getAlternative(approveAlternativeRequest.getChoiceId(), Integer.toString(approveAlternativeRequest.getAlternative()), context);
            return true;
        } catch (Exception e) {
            logger.log(e.getStackTrace());
            return false;
        }
    }

    private String getAlternativeJSON(ApproveAlternativeRequest approveAlternativeRequest, Context context) {
        AlternativesDAO alternativesDAO = new AlternativesDAO();
        try {
            Alternative alternative = alternativesDAO.getAlternative(approveAlternativeRequest.getChoiceId(), Integer.toString(approveAlternativeRequest.getAlternative()), context);
        } catch (Exception e) {
            // shouldn't, as alternativeExists() should be used first.
        }
        return alternative.toJSON();
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
            if (alternativeExists(approveRequest, context)) {
                if (isDisapprover(approver)) {
                    if (addApprover(approver)) {
                        response = new ApproveAlternativeResponse(getAlternativeJSON(approveRequest, context), 200);
                    }
                } else {
                    response = new ApproveAlternativeResponse("User is on disapprove List", 400);
                }
            } else {
                response = new ApproveAlternativeResponse("Alternative not found", 400);
            }

        } catch (Exception e) {
            response = new ApproveAlternativeResponse("Unable to add approver " + "(" + e.getMessage() + ")", 400);
            e.printStackTrace();
        }

        return response;

    }

}