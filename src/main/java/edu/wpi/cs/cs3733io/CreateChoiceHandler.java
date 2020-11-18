package edu.wpi.cs.cs3733io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.amazonaws.util.json.Jackson;
import com.fasterxml.jackson.databind.JsonNode;

import edu.wpi.cs.cs3733io.demo.http.CreateChoiceRequest;
import edu.wpi.cs.cs3733io.demo.http.CreateChoiceResponse;

public class CreateChoiceHandler implements RequestHandler<CreateChoiceRequest,CreateChoiceResponse>{
	LambdaLogger logger;
	CreateChoiceResponse response;
	
	@Override
	public CreateChoiceResponse handleRequest(CreateChoiceRequest input, Context context) {
		// TODO Auto-generated method stub
		response = new CreateChoiceResponse(123321, 200);
		return response;
	}
	
}

/*
public class CreateChoiceHandler implements RequestHandler<AddRequest, AddResponse> {

	LambdaLogger logger;
	/*
	 * @Override public void handleRequest(InputStream input, OutputStream output,
	 * Context context) throws IOException { logger = context.getLogger(); if
	 * (context != null) { context.getLogger(); }
	 * 
	 * // load entire input into a String (since it contains JSON) StringBuilder
	 * incoming = new StringBuilder(); try (BufferedReader br = new
	 * BufferedReader(new InputStreamReader(input))) { String line = null; while
	 * ((line = br.readLine()) != null) { incoming.append(line); } }
	 * 
	 * // When coming in from Lambda function is pure JSON. When coming from API //
	 * Gateway or the // real thing, then is wrapped inside more complicated JSON
	 * and you only want // the BODY // in most cases. JsonNode node =
	 * Jackson.fromJsonString(incoming.toString(), JsonNode.class);
	 * 
	 * if (node.has("body")) { node =
	 * Jackson.fromJsonString(node.get("body").asText(), JsonNode.class); }
	 * 
	 * int memberCount, choiceId; String description, alternatives, dateCompleted;
	 * boolean isCompleted;
	 * 
	 * String param = node.get("memberCount").asText(); memberCount =
	 * Integer.parseInt(param); param = node.get("description").asText();
	 * description = param; param = node.get("alternatives").asText(); alternatives
	 * = param; param = node.get("choiceId").asText(); choiceId =
	 * Integer.parseInt(param); param = node.get("isCompleted").asText();
	 * isCompleted = Boolean.parseBoolean(param); param =
	 * node.get("dateCompleted").asText(); dateCompleted = param;
	 * 
	 * // boolean error = false; // try { // arg1 = Double.parseDouble(param); // }
	 * catch (NumberFormatException nfe) { // try { // arg1 =
	 * getDoubleFromBucket(param); // } catch (Exception e) { //
	 * logger.log("Unable to parse:" + param + " as arg1"); // error = true; // } //
	 * }
	 * 
	 * // param = node.get("arg2").asText(); // try { // arg2 =
	 * Double.parseDouble(param); // } catch (NumberFormatException nfe) { // try {
	 * // arg2 = getDoubleFromBucket(param); // } catch (Exception e) { //
	 * logger.log("Unable to parse:" + param + " as arg2"); // error = true; // } //
	 * } // // PrintWriter pw = new PrintWriter(output); // // int statusCode; //
	 * double sum = 0; // if (error) { // statusCode = 400; // } else { // sum =
	 * arg1 + arg2; // statusCode = 200; // }
	 * 
	 * PrintWriter pw = new PrintWriter(output);
	 * 
	 * int statusCode = 200; String responseString = Integer.toString(memberCount) +
	 * " " + alternatives + " " + description + " " + choiceId + " " + isCompleted +
	 * " " + dateCompleted;
	 * 
	 * // Needed for CORS integration... // String response = "{ \n" +
	 * "  \"isBase64Encoded\" : false, \n" + "  \"statusCode\"      : " + statusCode
	 * // + ", \n" + "  \"headers\" : { \n " +
	 * "     \"Access-Control-Allow-Origin\" : \"*\", \n" // +
	 * "     \"Access-Control-Allow-Method\"  : \"GET,POST,OPTIONS\" \n" + "  }, \n"
	 * + "  \"body\" : \"" // + "{ \\\"result\\\" : \\\"" + responseString +
	 * "\\\" }" + "\" \n" + "}"; // // write out. // pw.print(response); //
	 * pw.close();
	 * 
	 * 
	 * 
	 * }
	 

	@Override
	public AddResponse handleRequest(AddRequest req, Context context) {
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler of RequestHandler");
		logger.log(req.toString());

		String param = node.get("memberCount").asText();
		memberCount = Integer.parseInt(param);
		param = node.get("description").asText();
		description = param;
		param = node.get("alternatives").asText();
		alternatives = param;
		param = node.get("choiceId").asText();
		choiceId = Integer.parseInt(param);
		param = node.get("isCompleted").asText();
		isCompleted = Boolean.parseBoolean(param);
		param = node.get("dateCompleted").asText();
		dateCompleted = param;

		boolean fail = false;
		String failMessage = "";
		AddResponse response;

		response = new AddResponse(123321, 200);
		return response;
	}

}
*/
//}
