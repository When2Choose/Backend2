package edu.wpi.cs.cs3733io;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.wpi.cs.cs3733io.db.ChoicesDAO;
import edu.wpi.cs.cs3733io.db.UsersDAO;
import edu.wpi.cs.cs3733io.http.UserLoginRequest;
import edu.wpi.cs.cs3733io.http.UserLoginResponse;
import edu.wpi.cs.cs3733io.model.Choice;
import edu.wpi.cs.cs3733io.model.User;

public class UserLoginHandler implements RequestHandler<UserLoginRequest, UserLoginResponse> {
	LambdaLogger logger;
	UserLoginResponse response;
    boolean flag = false;
    
	boolean createUser(String name, String choiceId) throws Exception {
		if (logger != null) {

			logger.log("in loginUser FOR NO PASSWORD ");

		}
		UsersDAO dao = new UsersDAO();

		User user = new User(name, choiceId);

		return dao.addUser(user);
	}
	boolean createUser(String name, String password, String choiceId) throws Exception {
		if (logger != null) {
			logger.log("in loginUser FOR A PASSWORD ");
		}
		UsersDAO dao = new UsersDAO();

		User user = new User(name, password, choiceId);

		return dao.addUser(user);
	}

	boolean getNumberOfUsers(String uuid) {
		if (logger != null) {
			logger.log("in getNumberOfUsers");
		}
		ChoicesDAO dao = new ChoicesDAO();
		UsersDAO dao2 = new UsersDAO();
		try {dao.getChoice(uuid).getMemberCount();} catch (Exception e) {
			flag = true;
		}
		try {
			if (dao2.getNumberOfUsers(uuid) < dao.getChoice(uuid).getMemberCount()) {
				return true;
			} else
				return false;
		} catch (Exception e) {
			return false;
		}
	}
	@Override
	public UserLoginResponse handleRequest(UserLoginRequest userRequest, Context context) {	
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler of UserLoginHandler");
		logger.log(userRequest.toString());

		if (context != null) {
			context.getLogger();
		}

		try {
			if (getNumberOfUsers(userRequest.getChoiceId())) {
				if (userRequest.getPassword() == null || userRequest.getPassword() == "") {

					User user = new User(userRequest.getName(), userRequest.getChoiceId());
					response = new UserLoginResponse(user.toString(), 300);
					try {
						if (createUser(user.name, user.choiceId)) {
							response = new UserLoginResponse(user.toString(), 200);
							logger.log("if function worked");
						}
					} catch (Exception e) {
						response = new UserLoginResponse(
								"Unable to create user without password " + "(" + e.getMessage() + ")", 400);
						e.printStackTrace();
					}
				} else {
					User user = new User(userRequest.getName(), userRequest.getPassword(), userRequest.getChoiceId());
					response = new UserLoginResponse(user.toString(), 300);
					try {
						if (createUser(user.name, user.getPassword(), user.choiceId)) {
							response = new UserLoginResponse(user.toString(), 200);
						}
					} catch (Exception e) {
						response = new UserLoginResponse(
								"Unable to create user with password " + "(" + e.getMessage() + ")", 400);
						e.printStackTrace();

					}
				}
			} else {
				if(flag) {
					response = new UserLoginResponse("Choice ID: "+ userRequest.getChoiceId() +" does not exist", 400);
				}else
				response = new UserLoginResponse("Max number of users hit for choice Id: "+ userRequest.getChoiceId(), 400);
			}
		} catch (Exception e) {
			response = new UserLoginResponse("Unable to get choice", 400);

			e.printStackTrace();
		}

		return response;
	}

}