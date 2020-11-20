package edu.wpi.cs.cs3733io;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;


import edu.wpi.cs.cs3733io.http.UserLoginRequest;
import edu.wpi.cs.cs3733io.http.UserLoginResponse;

public class UserLoginHandler implements RequestHandler<UserLoginRequest, UserLoginResponse> {
	LambdaLogger logger;
	UserLoginResponse response;

	@Override
	public UserLoginResponse handleRequest(UserLoginRequest input, Context context) {	
		return response;
	}

}