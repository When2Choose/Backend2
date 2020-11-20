package edu.wpi.cs.cs3733io;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.wpi.cs.cs3733io.http.AdminLoginRequest;
import edu.wpi.cs.cs3733io.http.AdminLoginResponse;

public class AdminLoginHandler implements RequestHandler<AdminLoginRequest, AdminLoginResponse> {
	LambdaLogger logger;
	AdminLoginResponse response;

	@Override
	public AdminLoginResponse handleRequest(AdminLoginRequest input, Context context) {	
		return response;
	}

}