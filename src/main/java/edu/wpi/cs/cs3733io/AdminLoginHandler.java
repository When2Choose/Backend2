package edu.wpi.cs.cs3733io;


import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.wpi.cs.cs3733io.db.AdminsDAO;
import edu.wpi.cs.cs3733io.http.AdminLoginRequest;
import edu.wpi.cs.cs3733io.http.AdminLoginResponse;
import edu.wpi.cs.cs3733io.model.Administrator;


public class AdminLoginHandler implements RequestHandler<AdminLoginRequest, AdminLoginResponse> {
	LambdaLogger logger;
	AdminLoginResponse response;

	boolean createAdmin(String name, String password) throws Exception {
		if (logger != null) {
			logger.log("in adminLogin");
		}
		AdminsDAO dao = new AdminsDAO();

		Administrator admin = new Administrator(name, password);

		return dao.addAdministrator(admin);
	}
	@Override	
	public AdminLoginResponse handleRequest(AdminLoginRequest adminRequest, Context context) {
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler of adminLoginHandler");
		logger.log(adminRequest.toString());

		if (context != null) {
			context.getLogger();
		}
		
		Administrator admin = new Administrator(adminRequest.getName(), adminRequest.getPassword());
		response = new AdminLoginResponse(admin.toString(), 300);
		
		try {
			if (createAdmin(admin.name, admin.getPassword()) {
				response = new AdminLoginResponse(admin.toString(), 200);
			}

		} catch (Exception e) {
			response = new AdminLoginResponse(
					"Unable to create choice " + "(" + e.getMessage() + ")", 400);
			e.printStackTrace();
		}

		return response;
	}

}