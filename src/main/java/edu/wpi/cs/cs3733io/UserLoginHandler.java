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

	boolean addUser(User user) {
		if (logger != null) {
			logger.log("in loginUser FOR A PASSWORD ");
		}
		UsersDAO dao = new UsersDAO();
		try {
			return dao.addUser(user);
		} catch (Exception e) {
			return false;
		}
	}

	int getNumberOfUsers(String uuid) {
		if (logger != null) {
			logger.log("in getNumberOfUsers");
		}
		UsersDAO usersDAO = new UsersDAO();
		try {
			return usersDAO.getNumberOfUsers(uuid);
		} catch (Exception e) {
			return -1;
		}
	}

	int getMaxMembers(String choiceID) {
		if (logger != null) {
			logger.log("in getMaxMembers");
		}
		ChoicesDAO choicesDAO = new ChoicesDAO();
		try {
			return choicesDAO.getChoice(choiceID).getMemberCount();
		} catch (Exception e) {
			return -1;
		}
	}

	private boolean choiceIDValid(String choiceID) {
		ChoicesDAO choicesDAO = new ChoicesDAO();
		try {
			return choicesDAO.getChoice(choiceID) != null;
		} catch (Exception e) {
			return false;
		}
	}

	boolean userExists(User user) {
		UsersDAO usersDAO = new UsersDAO();
		try {
			return usersDAO.getUser(user) != null;
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

		if (choiceIDValid(userRequest.getChoiceId())) {
			User user = new User(userRequest.getName(), userRequest.getPassword(), userRequest.getChoiceId());
			// Choice ID exists
			if (userExists(user)) {
				// Time to log in user
				response = new UserLoginResponse(user.toString(), 200);
			} else {
				// Create a new user, if possible
				if (getNumberOfUsers(userRequest.getChoiceId()) < getMaxMembers(userRequest.getChoiceId())) {
					boolean addNewUserSuccess = addUser(user);
					if (addNewUserSuccess) {
						// successfully added new user to table
						response = new UserLoginResponse(user.toString(), 200);
					} else {
						// failure in SQL, probably
						response = new UserLoginResponse("Error while creating new user. Check logs.", 400);
					}
				} else {
					// max users hit.
					response = new UserLoginResponse("Too many users for given choice.", 400);
				}
			}
		} else {
			// Invalid choice ID
			response = new UserLoginResponse("Choice ID: " + userRequest.getChoiceId() + " does not exist",
					400);
		}
		return response;
	}

}