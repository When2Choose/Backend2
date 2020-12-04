package edu.wpi.cs.cs3733io;

import java.util.List;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.wpi.cs.cs3733io.db.ChoicesDAO;
import edu.wpi.cs.cs3733io.http.AdministratorRequest;
import edu.wpi.cs.cs3733io.http.AllResponse;
import edu.wpi.cs.cs3733io.model.Choice;

public class AdministratorHandler implements RequestHandler<AdministratorRequest, AllResponse> {
	LambdaLogger logger;
	AllResponse response;

	/**
	 * Returns a list of all the Choices in the database.
	 * 
	 * @return
	 */
	List<Choice> getChoices() {
		if (logger != null) {
			logger.log("in loginUser FOR A PASSWORD ");
		}
		ChoicesDAO dao = new ChoicesDAO();
		try {
			return dao.getAllChoices();
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Generates a response for the Administrator Generating a Report.
	 */
	@Override
	public AllResponse handleRequest(AdministratorRequest input, Context context) {
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler of Admin");
		if (context != null) {
			context.getLogger();
		}
		if (getChoices() == null) {
			response = new AllResponse("No choices got", 400);
		} else {
			List<Choice> choices = getChoices();
			Choice nullChoice = new Choice(-1, null, null);
			response = new AllResponse(nullChoice.toStringForGeneratingReport(choices), 200);
		}
		return response;

	}

}