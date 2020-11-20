package edu.wpi.cs.cs3733io;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;


import edu.wpi.cs.cs3733io.http.DisapproveAlternativeRequest;
import edu.wpi.cs.cs3733io.http.DisapproveAlternativeResponse;

public class DisapproveAlternativeHandler implements RequestHandler<DisapproveAlternativeRequest, DisapproveAlternativeResponse> {
	LambdaLogger logger;
	DisapproveAlternativeResponse response;

	@Override
	public DisapproveAlternativeResponse handleRequest(DisapproveAlternativeRequest input, Context context) {	
		return response;
	}

}