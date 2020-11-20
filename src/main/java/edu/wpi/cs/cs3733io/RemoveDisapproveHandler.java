package edu.wpi.cs.cs3733io;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;


import edu.wpi.cs.cs3733io.http.RemoveDisapproveRequest;
import edu.wpi.cs.cs3733io.http.RemoveDisapproveResponse;

public class RemoveDisapproveHandler implements RequestHandler<RemoveDisapproveRequest, RemoveDisapproveResponse> {
	LambdaLogger logger;
	RemoveDisapproveResponse response;

	@Override
	public RemoveDisapproveResponse handleRequest(RemoveDisapproveRequest input, Context context) {
		return response;
	}

}
