package edu.wpi.cs.cs3733io;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.wpi.cs.cs3733io.db.AlternativesDAO;
import edu.wpi.cs.cs3733io.db.ChoicesDAO;
import edu.wpi.cs.cs3733io.http.CreateChoiceRequest;
import edu.wpi.cs.cs3733io.http.AllResponse;
import edu.wpi.cs.cs3733io.model.Alternative;
import edu.wpi.cs.cs3733io.model.Choice;

public class CreateChoiceHandler implements RequestHandler<CreateChoiceRequest, AllResponse> {
	LambdaLogger logger;
	AllResponse response;

	boolean createChoice(Choice choice) throws Exception {
		if (logger != null) {
			logger.log("in createChoice");
		}
		ChoicesDAO dao = new ChoicesDAO();
		AlternativesDAO alternativesDAO = new AlternativesDAO();

		choice.createAlternatives();

		boolean success = true;

		for (Alternative alternative : choice.getAlternatives()) {
			success &= alternativesDAO.addAlternative(alternative);
		}

		return dao.addChoice(choice) && success;
	}

	@Override
	public AllResponse handleRequest(CreateChoiceRequest choiceRequest, Context context) {

		logger = context.getLogger();
		logger.log("Loading Java Lambda handler of Create Choice");
		logger.log(choiceRequest.toString());

		if (context != null) {
			context.getLogger();
		}

		Choice choice = new Choice(choiceRequest.getMemberCount(), choiceRequest.getDescription(),
				choiceRequest.getAlternatives());

		try {
			if (createChoice(choice)) {
				response = new AllResponse(choice.toString(choice.getAlternatives()), 200);
			}

		} catch (Exception e) {
			response = new AllResponse("Unable to create choice " + "(" + e.getMessage() + ")", 400);
			e.printStackTrace();
		}

		return response;
	}

}
