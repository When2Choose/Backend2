package edu.wpi.cs.cs3733io;

import java.util.LinkedList;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.wpi.cs.cs3733io.db.ChoicesDAO;
import edu.wpi.cs.cs3733io.http.CreateChoiceRequest;
import edu.wpi.cs.cs3733io.http.CreateChoiceResponse;
import edu.wpi.cs.cs3733io.model.Choice;

public class CreateChoiceHandler implements RequestHandler<CreateChoiceRequest, CreateChoiceResponse> {
	LambdaLogger logger;
	CreateChoiceResponse response;

	boolean createChoice(int memberCount, String description, LinkedList<String> alternativeNames) throws Exception {
		if (logger != null) {
			logger.log("in createChoice");
		}
		ChoicesDAO dao = new ChoicesDAO();

		Choice choice = new Choice(memberCount, description, alternativeNames);

		return dao.addChoice(choice);
	}

	@Override
	public CreateChoiceResponse handleRequest(CreateChoiceRequest choiceRequest, Context context) {

		logger = context.getLogger();
		logger.log("Loading Java Lambda handler of CalculatorHandler");
		logger.log(choiceRequest.toString());

		if (context != null) {
			context.getLogger();
		}
		
		Choice choice = new Choice(choiceRequest.getMemberCount(), choiceRequest.getDescription(),
				choiceRequest.getAlternativeNames());
		response = new CreateChoiceResponse(choice.toString(), 100);
		
		try {
			if (createChoice(choice.getMemberCount(), choice.description, choice.alternativeNames)) {
				response = new CreateChoiceResponse(choice.toString(), 200);
			}

		} catch (Exception e) {
			response = new CreateChoiceResponse(
					"Unable to create choice " + "(" + e.getMessage() + ")", 400);
			e.printStackTrace();
		}

		return response;
	}

}
