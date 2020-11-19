package edu.wpi.cs.cs3733io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.amazonaws.util.json.Jackson;
import com.fasterxml.jackson.databind.JsonNode;

import edu.wpi.cs.cs3733io.demo.http.CreateChoiceRequest;
import edu.wpi.cs.cs3733io.demo.http.CreateChoiceResponse;
import edu.wpi.cs.cs3733io.model.Choice;

public class CreateChoiceHandler implements RequestHandler<CreateChoiceRequest, CreateChoiceResponse> {
	LambdaLogger logger;
	CreateChoiceResponse response;

	// This function needs to return a new page for the user to go to along with a
	// json containing the alternatives,
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

