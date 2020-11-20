package edu.wpi.cs.cs3733io;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;

import edu.wpi.cs.cs3733io.http.DeleteChoicesRequest;
import edu.wpi.cs.cs3733io.http.DeleteChoicesResponse;

public class RemoveDisapproveHandler implements RequestHandler<DeleteChoicesRequest, DeleteChoicesResponse> {
	LambdaLogger logger;
	DeleteChoicesResponse response;

	@Override
	public DeleteChoicesResponse handleRequest(DeleteChoicesRequest input, Context context) {
		return response;
	}

}
