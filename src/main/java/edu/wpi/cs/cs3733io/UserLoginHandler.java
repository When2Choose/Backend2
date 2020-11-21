package edu.wpi.cs.cs3733io;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.wpi.cs.cs3733io.db.UsersDAO;
import edu.wpi.cs.cs3733io.http.UserLoginRequest;
import edu.wpi.cs.cs3733io.http.UserLoginResponse;
import edu.wpi.cs.cs3733io.model.User;

public class UserLoginHandler implements RequestHandler<UserLoginRequest, UserLoginResponse> {
	LambdaLogger logger;
	UserLoginResponse response;

	boolean createUser(String name, String choiceId) throws Exception {
		if (logger != null) {
			logger.log("in loginUser");
		}
		UsersDAO dao = new UsersDAO();

		User user = new User(name, choiceId);

		return dao.addUser(user);
	}
	
	boolean createUser(String name,  String password, String choiceId) throws Exception {
		if (logger != null) {
			logger.log("in loginUser");
		}
		UsersDAO dao = new UsersDAO();

		User user = new User(name, password, choiceId);

		return dao.addUser(user);
	}
	
	@Override
	public UserLoginResponse handleRequest(UserLoginRequest userRequest, Context context) {
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler of UserLoginHandler");
		logger.log(userRequest.toString());

		if (context != null) {
			context.getLogger();
		}

		User user = new User(userRequest.getName(), userRequest.getChoiceId(), userRequest.getPassword());
		response = new UserLoginResponse(user.toString(), 300);

		try {
			if (user.getPassword() == null || user.getPassword() == "") {
				if (createUser(user.name, user.choiceId)) {
					response = new UserLoginResponse(user.toString(), 200);
				}
			} else {
				if (createUser(user.name, user.getPassword(), user.choiceId)) {
					response = new UserLoginResponse(user.toString(), 200);
				}
			}

		} catch (Exception e) {
			response = new UserLoginResponse("Unable to create user " + "(" + e.getMessage() + ")", 400);
			e.printStackTrace();
		}

		return response;
	}

}