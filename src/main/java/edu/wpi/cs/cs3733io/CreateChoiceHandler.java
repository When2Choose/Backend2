package edu.wpi.cs.cs3733io;

import java.util.ArrayList;
import java.util.LinkedList;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.wpi.cs.cs3733io.db.AlternativesDAO;
import edu.wpi.cs.cs3733io.db.ChoicesDAO;
import edu.wpi.cs.cs3733io.http.CreateChoiceRequest;
import edu.wpi.cs.cs3733io.http.CreateChoiceResponse;
import edu.wpi.cs.cs3733io.model.Alternative;
import edu.wpi.cs.cs3733io.model.Choice;

public class CreateChoiceHandler implements RequestHandler<CreateChoiceRequest, CreateChoiceResponse> {
	LambdaLogger logger;
	CreateChoiceResponse response;

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
	public CreateChoiceResponse handleRequest(CreateChoiceRequest choiceRequest, Context context) {

		logger = context.getLogger();
		logger.log("Loading Java Lambda handler of Create Choice");
		logger.log(choiceRequest.toString());

		if (context != null) {
			context.getLogger();
		}
		
		Choice choice = new Choice(choiceRequest.getMemberCount(), choiceRequest.getDescription(),
				choiceRequest.getAlternatives());
		response = new CreateChoiceResponse(choice.toString(choice.getAlternatives()), 300);
		
		try {
			if (createChoice(choice)) {
				response = new CreateChoiceResponse(choice.toString(choice.getAlternatives()), 200);
			}

		} catch (Exception e) {
			response = new CreateChoiceResponse(
					"Unable to create choice " + "(" + e.getMessage() + ")", 400);
			e.printStackTrace();
		}

		return response;
	}

}
