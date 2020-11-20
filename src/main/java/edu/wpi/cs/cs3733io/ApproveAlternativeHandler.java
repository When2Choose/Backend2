package edu.wpi.cs.cs3733io;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.wpi.cs.cs3733io.http.ApproveAlternativeRequest;
import edu.wpi.cs.cs3733io.http.ApproveAlternativeResponse;

public class ApproveAlternativeHandler implements RequestHandler<ApproveAlternativeRequest, ApproveAlternativeResponse> {
	LambdaLogger logger;
	ApproveAlternativeResponse response;

	@Override
	public ApproveAlternativeResponse handleRequest(ApproveAlternativeRequest input, Context context) {	
		return response;
	}

}