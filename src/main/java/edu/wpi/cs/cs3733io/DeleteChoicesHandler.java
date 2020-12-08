package edu.wpi.cs.cs3733io;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.LinkedList;
import java.util.TimeZone;
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

	/**
	 * Deletes all choices more than days old in the database.
	 * 
	 * @param dayd       Integer.
	 * @param allChoices LinkedList<Choice>
	 * @return Returns true if the choices were deleted, false otherwise.
	 * @throws Exception
	 */
	boolean deleteChoices(double days, LinkedList<Choice> allChoices) throws Exception {
		if (logger != null) {
			logger.log("in createChoice");
		}
		ChoicesDAO choicesDAO = new ChoicesDAO();
		Date today = java.util.Calendar.getInstance().getTime();
		long time = today.getTime();
		for (Choice c : allChoices) {
			SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			simpleDate.setTimeZone(TimeZone.getTimeZone("EST"));
			Date creation = simpleDate.parse(c.getDateCreated());

			logger.log("Creation Date" + creation.toString());
			logger.log("Current Time" + Long.toString(time));
			logger.log("Creation Time" + Long.toString(creation.getTime()));

			long diffInMillies = Math.abs(time - creation.getTime());
			long diff = TimeUnit.SECONDS.convert(diffInMillies, TimeUnit.MILLISECONDS);

			logger.log("Different in Days Milliseconds" + Long.toString(diffInMillies));
			logger.log("Days Seconds" + Double.toString(days * 24 * 60 * 60));

			if (diff >= (long) (days * 24 * 60 * 60)) {
				choicesDAO.deleteChoice(c);

			}
			logger.log(" Difference in seconds " + Long.toString(diff));
		}

		return true;
	}

	/**
	 * Gets all the choices in the database.
	 * 
	 * @return Returns a LinkedList of all the choices in the database.
	 * @throws Exception
	 */
	LinkedList<Choice> allChoices() throws Exception {
		if (logger != null) {
			logger.log("in createChoice");
		}
		ChoicesDAO choicesDAO = new ChoicesDAO();

		return choicesDAO.getAllChoices();
	}

	/**
	 * Generates a response for deleting a choice.
	 */
	@Override
	public AllResponse handleRequest(DeleteChoicesRequest deleteRequest, Context context) {
		if (context != null) {
			context.getLogger();
		}

		logger = context.getLogger();
		logger.log("Loading Java Lambda handler of get choice");

		double days = deleteRequest.getDays();

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