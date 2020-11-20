package edu.wpi.cs.cs3733io;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.wpi.cs.cs3733io.http.GetChoiceRequest;
import edu.wpi.cs.cs3733io.http.GetChoiceResponse;

public class GetChoiceHandler implements RequestHandler<GetChoiceRequest, GetChoiceResponse> {
	LambdaLogger logger;
	GetChoiceResponse response;

	@Override
	public GetChoiceResponse handleRequest(GetChoiceRequest input, Context context) {
		return response;
	}

}
