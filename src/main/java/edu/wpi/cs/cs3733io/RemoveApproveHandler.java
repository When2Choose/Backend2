package edu.wpi.cs.cs3733io;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;


import edu.wpi.cs.cs3733io.http.RemoveApproveRequest;
import edu.wpi.cs.cs3733io.http.RemoveApproveResponse;

public class RemoveApproveHandler implements RequestHandler<RemoveApproveRequest, RemoveApproveResponse> {
	LambdaLogger logger;
	RemoveApproveResponse response;

	@Override
	public RemoveApproveResponse handleRequest(RemoveApproveRequest input, Context context) {	
		return response;
	}

}
