package edu.wpi.cs.cs3733io;

import java.util.LinkedList;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.wpi.cs.cs3733io.db.FeedbackDAO;
import edu.wpi.cs.cs3733io.http.AddFeedbackRequest;
import edu.wpi.cs.cs3733io.http.AllResponse;
import edu.wpi.cs.cs3733io.http.AllResponse;
import edu.wpi.cs.cs3733io.model.Feedback;

public class AddFeedbackHandler implements RequestHandler<AddFeedbackRequest, AllResponse> {
	LambdaLogger logger;
	AllResponse response;

	boolean addFeedback(Feedback feedback) throws Exception {
		if (logger != null) {
			logger.log("in addApprove");
		}

		FeedbackDAO feedbackDAO = new FeedbackDAO();

		return feedbackDAO.addFeedback(feedback);
	}

	LinkedList<Feedback> getFeedback(String choiceUuid, int alternativeIndex) throws Exception {
		if (logger != null) {
			logger.log("in getApprove");
		}

		FeedbackDAO feedbackDAO = new FeedbackDAO();

		return feedbackDAO.getAlternativeFeedback(alternativeIndex, choiceUuid);
	}

	@Override
	public AllResponse handleRequest(AddFeedbackRequest feedbackRequest, Context context) {
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler of feedback ");

		if (context != null) {
			context.getLogger();
		}

		Feedback feedback = new Feedback(feedbackRequest.getUser(), feedbackRequest.getFeedbackText(),
				feedbackRequest.getChoiceId(), feedbackRequest.getAlternativeIndex());

		try {

			if (addFeedback(feedback)) {
				LinkedList<Feedback> allFeedback = getFeedback(feedback.getUuidChoice(),
						feedback.getAlternativeIndex());
				response = new AllResponse(feedback.toString(allFeedback), 200);
			}

		} catch (Exception e) {
			response = new AllResponse("Unable to add feedback " + "(" + e.getMessage() + ")", 400);
			e.printStackTrace();
		}

		return response;
	}

}