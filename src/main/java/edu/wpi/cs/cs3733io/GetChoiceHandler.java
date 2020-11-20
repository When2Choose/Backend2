package edu.wpi.cs.cs3733io;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.wpi.cs.cs3733io.http.DeleteChoicesRequest;
import edu.wpi.cs.cs3733io.http.DeleteChoicesResponse;

public class GetChoiceHandler implements RequestHandler<DeleteChoicesRequest, DeleteChoicesResponse> {
	LambdaLogger logger;
	DeleteChoicesResponse response;

	@Override
	public DeleteChoicesResponse handleRequest(DeleteChoicesRequest input, Context context) {
		return response;
	}

}