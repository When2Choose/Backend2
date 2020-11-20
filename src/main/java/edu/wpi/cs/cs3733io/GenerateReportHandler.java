package edu.wpi.cs.cs3733io;


import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;


import edu.wpi.cs.cs3733io.http.GenerateReportRequest;
import edu.wpi.cs.cs3733io.http.GenerateReportResponse;

public class GenerateReportHandler implements RequestHandler<GenerateReportRequest, GenerateReportResponse> {
	LambdaLogger logger;
	GenerateReportResponse response;

	@Override
	public GenerateReportResponse handleRequest(GenerateReportRequest input, Context context) {	
		return response;
	}

}