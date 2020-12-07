package edu.wpi.cs.cs3733io;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.wpi.cs.cs3733io.db.AlternativesDAO;
import edu.wpi.cs.cs3733io.db.ChoicesDAO;
import edu.wpi.cs.cs3733io.db.FeedbackDAO;
import edu.wpi.cs.cs3733io.http.GetChoiceRequest;
import edu.wpi.cs.cs3733io.http.AllResponse;
import edu.wpi.cs.cs3733io.model.Alternative;
import edu.wpi.cs.cs3733io.model.Choice;
import edu.wpi.cs.cs3733io.model.Feedback;

import java.util.LinkedList;

public class GetChoiceHandler implements RequestHandler<GetChoiceRequest, AllResponse> {
	LambdaLogger logger;
	AllResponse response;
	LinkedList<Alternative> alternatives;

	Choice getChoice(String uuidString) throws Exception {
		if (logger != null) {
			logger.log("in createChoice");
		}
		ChoicesDAO dao = new ChoicesDAO();

		return dao.getChoice(uuidString);
	}

	public LinkedList<Alternative> getAlternatives(String choiceUUID, Context context) throws Exception {
		logger.log("Getting alternatives");
		AlternativesDAO alternativesDAO = new AlternativesDAO();

		return alternativesDAO.getAlternatives(choiceUUID, context);
	}
	
	

	@Override
	public AllResponse handleRequest(GetChoiceRequest choiceRequest, Context context) {
		if (context != null) {
			context.getLogger();
		}

		logger = context.getLogger();
		logger.log("Loading Java Lambda handler of get choice");
		logger.log(choiceRequest.toString());

		alternatives = new LinkedList<>();

		try {
			Choice gotChoice = getChoice(choiceRequest.getUuidString());
			alternatives = getAlternatives(choiceRequest.getUuidString(), context);
			response = new AllResponse(gotChoice.toString(alternatives), 200);

		} catch (Exception e) {
			response = new AllResponse("Unable to get choice " + "(" + e.getMessage() + ")", 400);
			e.printStackTrace();
		}

		return response;
	}

}
