package edu.wpi.cs.cs3733io;

import java.util.LinkedList;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.wpi.cs.cs3733io.http.AddFeedbackRequest;
import edu.wpi.cs.cs3733io.http.AddFeedbackResponse;


public class AddFeedbackHandler implements RequestHandler<AddFeedbackRequest, AddFeedbackResponse> {
	LambdaLogger logger;
	AddFeedbackResponse response;

	@Override
	public AddFeedbackResponse handleRequest(AddFeedbackRequest feedbackRequest, Context context) {	
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler of feedback ");

		if (context != null) {
			context.getLogger();
		}

		//Feedback feedback = new Feedback(approveRequest.getAlternat);

		try {


		} catch (Exception e) {
			response = new AddFeedbackResponse("Unable to add feedback " + "(" + e.getMessage() + ")", 400);
			e.printStackTrace();
		}

		return response;
	}

}