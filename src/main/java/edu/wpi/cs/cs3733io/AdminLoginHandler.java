package edu.wpi.cs.cs3733io;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.wpi.cs.cs3733io.http.AdminLoginRequest;
import edu.wpi.cs.cs3733io.http.AdminLoginResponse;
import edu.wpi.cs.cs3733io.http.CreateChoiceResponse;
import edu.wpi.cs.cs3733io.model.Administrator;
import edu.wpi.cs.cs3733io.model.Choice;

public class AdminLoginHandler implements RequestHandler<AdminLoginRequest, AdminLoginResponse> {
	LambdaLogger logger;
	AdminLoginResponse response;

	// This function needs to return a new page for the user to go to along with a
	// json containing the alternatives, and alterantive information
	@Override	
	public AdminLoginResponse handleRequest(AdminLoginRequest adminRequest, Context context) {
		if (context != null) {
			context.getLogger();
		}
		
		Administrator admin = new Administrator(adminRequest.getName(), adminRequest.getPassword());
		
		response = new AdminLoginResponse(admin.toString(), 200);
		return response;
	}

}