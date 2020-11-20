package edu.wpi.cs.cs3733io;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.wpi.cs.cs3733io.http.AddFeedbackRequest;
import edu.wpi.cs.cs3733io.http.AddFeedbackResponse;


public class AddFeedbackHandler implements RequestHandler<AddFeedbackRequest, AddFeedbackResponse> {
	LambdaLogger logger;
	AddFeedbackResponse response;

	@Override
	public AddFeedbackResponse handleRequest(AddFeedbackRequest input, Context context) {	
		return response;
	}

}