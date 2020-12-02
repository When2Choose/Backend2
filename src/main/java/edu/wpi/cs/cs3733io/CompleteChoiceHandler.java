package edu.wpi.cs.cs3733io;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.wpi.cs.cs3733io.http.CompleteChoiceRequest;
import edu.wpi.cs.cs3733io.http.CompleteChoiceResponse;



public class CompleteChoiceHandler implements RequestHandler<CompleteChoiceRequest, CompleteChoiceResponse>  {
	LambdaLogger logger;
	CompleteChoiceResponse response;
	@Override
	public CompleteChoiceResponse handleRequest(CompleteChoiceRequest completeRequest, Context context) {
		
		return response;
	}

}
