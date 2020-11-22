package edu.wpi.cs.cs3733io;



import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.wpi.cs.cs3733io.db.ChoicesDAO;
import edu.wpi.cs.cs3733io.http.GetChoiceRequest;
import edu.wpi.cs.cs3733io.http.GetChoiceResponse;
import edu.wpi.cs.cs3733io.model.Choice;

public class GetChoiceHandler implements RequestHandler<GetChoiceRequest, GetChoiceResponse> {
	LambdaLogger logger;
	GetChoiceResponse response;

	Choice getChoice(String uuidString) throws Exception {
		if (logger != null) {
			logger.log("in createChoice");
		}
		ChoicesDAO dao = new ChoicesDAO();

		return dao.getChoice(uuidString);
	}

	@Override
	public GetChoiceResponse handleRequest(GetChoiceRequest choiceRequest, Context context) {
		if (context != null) {
			context.getLogger();
		}

		logger = context.getLogger();
		logger.log("Loading Java Lambda handler of get choice");
		logger.log(choiceRequest.toString());

		try {
			Choice gotChoice = getChoice(choiceRequest.getUuidString());
			response = new GetChoiceResponse(gotChoice.toString(), 200);

		} catch (Exception e) {
			response = new GetChoiceResponse("Unable to get choice " + "(" + e.getMessage() + ")", 400);
			e.printStackTrace();
		}

		return response;
	}

}
