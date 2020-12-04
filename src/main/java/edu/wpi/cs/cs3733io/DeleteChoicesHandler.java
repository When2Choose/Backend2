package edu.wpi.cs.cs3733io;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.wpi.cs.cs3733io.db.ChoicesDAO;
import edu.wpi.cs.cs3733io.http.DeleteChoicesRequest;
import edu.wpi.cs.cs3733io.http.AllResponse;
import edu.wpi.cs.cs3733io.model.Choice;

public class DeleteChoicesHandler implements RequestHandler<DeleteChoicesRequest, AllResponse> {
	LambdaLogger logger;
	AllResponse response;

	boolean deleteChoices(int days, LinkedList<Choice> allChoices) throws Exception {
		if (logger != null) {
			logger.log("in createChoice");
		}
		ChoicesDAO choicesDAO = new ChoicesDAO();
		Date today = new Date();
		for (Choice c : allChoices) {
			Date creation = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse(c.getDateCreated());

			long diffInMillies = Math.abs(today.getTime() - creation.getTime());
			long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

			if (diff >= days) {
				choicesDAO.deleteChoice(c);
			}
		}

		return true;
	}

	LinkedList<Choice> allChoices() throws Exception {
		if (logger != null) {
			logger.log("in createChoice");
		}
		ChoicesDAO choicesDAO = new ChoicesDAO();

		return choicesDAO.getAllChoices();
	}

	@Override
	public AllResponse handleRequest(DeleteChoicesRequest deleteRequest, Context context) {
		if (context != null) {
			context.getLogger();
		}

		logger = context.getLogger();
		logger.log("Loading Java Lambda handler of get choice");

		int days = deleteRequest.getDays();

		try {
			LinkedList<Choice> allChoices = allChoices();
			if (deleteChoices(days, allChoices)) {
				response = new AllResponse(deleteRequest.successMessage(), 200);
			}

		} catch (Exception e) {
			response = new AllResponse("Unable to delete choices " + "(" + e.getMessage() + ")", 400);
			e.printStackTrace();
		}

		return response;
	}

}