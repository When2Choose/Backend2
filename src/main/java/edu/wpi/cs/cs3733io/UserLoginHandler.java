package edu.wpi.cs.cs3733io;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.wpi.cs.cs3733io.db.ChoicesDAO;
import edu.wpi.cs.cs3733io.db.UsersDAO;
import edu.wpi.cs.cs3733io.http.UserLoginRequest;
import edu.wpi.cs.cs3733io.http.AllResponse;
import edu.wpi.cs.cs3733io.model.Choice;
import edu.wpi.cs.cs3733io.model.User;

public class UserLoginHandler implements RequestHandler<UserLoginRequest, AllResponse> {
	LambdaLogger logger;
	AllResponse response;
	Choice loginChoice;
	boolean flag = false;

	/**
	 * Adds a user to the database.
	 * 
	 * @param user User.
	 * @return Returns true if added, false otherwise.
	 */
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

	/**
	 * Gets the number of user for a Choice.
	 * 
	 * @param uuid String
	 * @return Integer. Number of users for a choice. -1 if error.
	 */
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

	/**
	 * Returns the max number of users for a Choice.
	 * 
	 * @param choiceID String.
	 * @return Integer. Max number of users for a choice. -1 if error.
	 */
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

	/**
	 * Determines if a Choice UUID is in the database.
	 * 
	 * @param choiceID String.
	 * @return Returns true if the Choice is in the database, false otherwise.
	 */
	private boolean choiceIDValid(String choiceID) {
		ChoicesDAO choicesDAO = new ChoicesDAO();
		try {
			loginChoice = choicesDAO.getChoice(choiceID);
			return choicesDAO.getChoice(choiceID) != null;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Determines if a user exists already.
	 * 
	 * @param user User.
	 * @return Returns true if the user exists, false otherwise.
	 */
	boolean userExists(User user) {
		UsersDAO usersDAO = new UsersDAO();
		try {
			return usersDAO.getUser(user) != null;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Generates a response for adding a User.
	 */
	@Override
	public AllResponse handleRequest(UserLoginRequest userRequest, Context context) {
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
				response = new AllResponse(user.toString(loginChoice.getIsCompleted()), 200);
			} else {
				// Create a new user, if possible
				if (getNumberOfUsers(userRequest.getChoiceId()) < getMaxMembers(userRequest.getChoiceId())) {
					boolean addNewUserSuccess = addUser(user);
					if (addNewUserSuccess) {
						// successfully added new user to table
						response = new AllResponse(user.toString(loginChoice.getIsCompleted()), 200);
					} else {
						// failure in SQL, probably
						response = new AllResponse("Error while creating new user. Check logs.", 400);
					}
				} else {
					// max users hit.
					response = new AllResponse("Too many users for given choice.", 400);
				}
			}
		} else {
			// Invalid choice ID
			response = new AllResponse("Choice ID: " + userRequest.getChoiceId() + " does not exist", 400);
		}
		return response;
	}

}