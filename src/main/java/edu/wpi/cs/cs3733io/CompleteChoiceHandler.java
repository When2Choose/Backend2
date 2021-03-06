package edu.wpi.cs.cs3733io;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.wpi.cs.cs3733io.db.AlternativesDAO;
import edu.wpi.cs.cs3733io.db.ChoicesDAO;
import edu.wpi.cs.cs3733io.http.CompleteChoiceRequest;
import edu.wpi.cs.cs3733io.http.AllResponse;
import edu.wpi.cs.cs3733io.model.Alternative;
import edu.wpi.cs.cs3733io.model.Choice;

public class CompleteChoiceHandler implements RequestHandler<CompleteChoiceRequest, AllResponse> {
	LambdaLogger logger;
	AllResponse response;

	/**
	 * Gets a choice from the database.
	 * 
	 * @param uuidString String Choice UUID.
	 * @return Returns the choice from the database.
	 * @throws Exception
	 */
	Choice getChoice(String uuidString) throws Exception {
		if (logger != null) {
			logger.log("in get choice for complete choice");
		}
		ChoicesDAO dao = new ChoicesDAO();

		return dao.getChoice(uuidString);
	}

	/**
	 * Completes a choice in the database.
	 * 
	 * @param choice Choice.
	 * @return Returns true if the choice was completed, false otherwise.
	 * @throws Exception
	 */
	boolean completeChoice(Choice choice) throws Exception {
		if (logger != null) {
			logger.log("in complete choice for complete choice");
		}
		ChoicesDAO dao = new ChoicesDAO();

		dao.updateChoice(choice);

		return true;
	}

	/**
	 * Chooses an alternative to complete the choice.
	 * 
	 * @param alternativeIndex Integer
	 * @param choice           Choice
	 * @return Returns the alternative that is completing the choice.
	 * @throws Exception
	 */
	Alternative choseAlternative(int alternativeIndex, Choice choice) throws Exception {
		if (logger != null) {
			logger.log("in chose alternative for complete choice");
		}

		AlternativesDAO dao = new AlternativesDAO();

		dao.setChoseAlternative(alternativeIndex, choice.getUuidString());
		Alternative alternative = dao.getAlternative(alternativeIndex, choice.getUuidString());
		alternative.setIsChosen(true);
		return alternative;
	}

	/**
	 * Generates a response for completing a choice.
	 */
	@Override
	public AllResponse handleRequest(CompleteChoiceRequest completeRequest, Context context) {
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler of Complete Choice ");

		if (context != null) {
			context.getLogger();
		}

		try {
			Choice choice = getChoice(completeRequest.getChoiceId());
			choice.setIsCompleted(true);
			choice.setDateCompleted(completeRequest.getDateString());
			Alternative alternative = choseAlternative(completeRequest.getAlternativeInex(), choice);

			if (completeChoice(choice)) {
				response = new AllResponse(choice.toString(alternative), 200);
			}

		} catch (Exception e) {
			response = new AllResponse("Unable to complete choice " + "(" + e.getMessage() + ")", 400);
			e.printStackTrace();
		}
		return response;
	}

}
