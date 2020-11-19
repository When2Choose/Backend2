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

public class CreateChoiceHandler implements RequestHandler<CreateChoiceRequest, CreateChoiceResponse> {
	LambdaLogger logger;
	CreateChoiceResponse response;

	// This function needs to return a new page for the user to go to along with a
	// json containing the alternatives, 
	@Override
	public CreateChoiceResponse handleRequest(CreateChoiceRequest input, Context context) {
		if (context != null) {
			context.getLogger();
		}

		response = new CreateChoiceResponse(123321, 200);
		return response;
	}

}

//	@Override
//	public AddResponse handleRequest(AddRequest req, Context context) {
//		logger = context.getLogger();
//		logger.log("Loading Java Lambda handler of RequestHandler");
//		logger.log(req.toString());
//
//		String param = node.get("memberCount").asText();
//		memberCount = Integer.parseInt(param);
//		param = node.get("description").asText();
//		description = param;
//		param = node.get("alternatives").asText();
//		alternatives = param;
//		param = node.get("choiceId").asText();
//		choiceId = Integer.parseInt(param);
//		param = node.get("isCompleted").asText();
//		isCompleted = Boolean.parseBoolean(param);
//		param = node.get("dateCompleted").asText();
//		dateCompleted = param;
//
//		boolean fail = false;
//		String failMessage = "";
//		AddResponse response;
//
//		response = new AddResponse(123321, 200);
//		return response;
//	}
//
//}
