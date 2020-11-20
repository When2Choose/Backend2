package edu.wpi.cs.cs3733io;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.wpi.cs.cs3733io.http.CreateChoiceRequest;
import edu.wpi.cs.cs3733io.http.CreateChoiceResponse;
import edu.wpi.cs.cs3733io.model.Choice;

public class CreateChoiceHandler implements RequestHandler<CreateChoiceRequest, CreateChoiceResponse> {
	LambdaLogger logger;
	CreateChoiceResponse response;

	// This function needs to return a new page for the user to go to along with a
	// json containing the alternatives, and alterantive information
	@Override
	public CreateChoiceResponse handleRequest(CreateChoiceRequest choiceRequest, Context context) {
		if (context != null) {
			context.getLogger();
		}
		
		Choice choice = new Choice(choiceRequest.getMemberCount(), choiceRequest.getDescription(), choiceRequest.getAlternativeNames());

		response = new CreateChoiceResponse(choice.toString(), 200);
		return response;
	}

}

