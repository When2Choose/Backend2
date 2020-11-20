package edu.wpi.cs.cs3733io;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.wpi.cs.cs3733io.http.AdministratorRequest;
import edu.wpi.cs.cs3733io.http.AdministratorResponse;

public class AdministratorHandler implements RequestHandler<AdministratorRequest, AdministratorResponse> {
	LambdaLogger logger;
	AdministratorResponse response;

	@Override
	public AdministratorResponse handleRequest(AdministratorRequest input, Context context) {	
		return response;
	}

}