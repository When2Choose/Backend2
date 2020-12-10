package edu.wpi.cs.cs3733io;

import java.util.LinkedList;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.wpi.cs.cs3733io.db.ChoicesDAO;
import edu.wpi.cs.cs3733io.db.FeedbackDAO;
import edu.wpi.cs.cs3733io.http.AddFeedbackRequest;
import edu.wpi.cs.cs3733io.http.AllResponse;
import edu.wpi.cs.cs3733io.model.Feedback;

public class AddFeedbackHandler implements RequestHandler<AddFeedbackRequest, AllResponse> {
	LambdaLogger logger;
	AllResponse response;

	/**
	 * Adds feedback to database.
	 * 
	 * @param feedback: Feedback.
	 * @return True if successful.
	 * @throws Exception
	 */
	boolean addFeedback(Feedback feedback) throws Exception {
		if (logger != null) {
			logger.log("in addApprove");
		}

		FeedbackDAO feedbackDAO = new FeedbackDAO();

		return feedbackDAO.addFeedback(feedback);
	}

	/**
	 * Generates a list of feedback for a given Choice UUID and Alternative Index
	 * from the database.
	 * 
	 * @param choiceUuid:      Choice UUID as string.
	 * @param alternativeIndex Alternative Index as an integer.
	 * @return
	 * @throws Exception
	 */
	LinkedList<Feedback> getFeedback(String choiceUuid, int alternativeIndex) throws Exception {
		if (logger != null) {
			logger.log("in getApprove");
		}

		FeedbackDAO feedbackDAO = new FeedbackDAO();

		return feedbackDAO.getAlternativeFeedback(alternativeIndex, choiceUuid);
	}

	/**
	 * Checks to see if choice is complete
	 * 
	 * @param choiceUUID String.
	 * @return Returns true if choice complete, false otherwise.
	 */
	boolean choiceNotComplete(String choiceUUID) throws Exception {
		if (logger != null) {
			logger.log("in getApprove");
		}

		ChoicesDAO choice = new ChoicesDAO();

		return !choice.getChoice(choiceUUID).getIsCompleted();
	}

	/**
	 * Generates a response for adding Feedback.
	 */
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

			if (addFeedback(feedback) && choiceNotComplete(feedback.getUuidChoice())) {
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