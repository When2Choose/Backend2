package edu.wpi.cs.cs3733io;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;

import edu.wpi.cs.cs3733io.http.RemoveApproveRequest;
import edu.wpi.cs.cs3733io.http.RemoveApproveResponse;

public class RemoveApproveHandler implements RequestHandler<RemoveApproveRequest, RemoveApproveResponse> {
	LambdaLogger logger;
	RemoveApproveResponse response;

	@Override
	public RemoveApproveResponse handleRequest(RemoveApproveRequest input, Context context) {	
		return response;
	}

}
