package edu.wpi.cs.cs3733io.test.demo;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;

import edu.wpi.cs.cs3733io.CreateChoiceHandler;
import edu.wpi.cs.cs3733io.http.AllResponse;
import edu.wpi.cs.cs3733io.http.CreateChoiceRequest;
import edu.wpi.cs.cs3733io.http.DeleteChoicesRequest;

/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
public class CreateChoiceHandlerTest extends LambdaTest{
	
	void testSuccessInput(String incoming) throws IOException {
    	CreateChoiceHandler handler = new CreateChoiceHandler();
    	CreateChoiceRequest req = new Gson().fromJson(incoming, CreateChoiceRequest.class);
       
        AllResponse resp = handler.handleRequest(req, createContext("create"));
        Assert.assertEquals(200, resp.statusCode);
    }
	
    void testFailInput(String incoming, int failureCode) throws IOException {
    	CreateChoiceHandler handler = new CreateChoiceHandler();
    	CreateChoiceRequest req = new Gson().fromJson(incoming, CreateChoiceRequest.class);

    	AllResponse resp = handler.handleRequest(req, createContext("create"));
        Assert.assertEquals(failureCode, resp.statusCode);
    }
	
	@Test
	public void testShoudlBeOk() {
		String[] alts = {"alt1", "alt2", "alt3", "alt4", "alt5"};
		CreateChoiceRequest ccr = new CreateChoiceRequest(10, "choice description", alts);
		String SAMPLE_INPUT_STRING = new Gson().toJson(ccr);
		System.out.println(SAMPLE_INPUT_STRING);
		
		try {
			testSuccessInput(SAMPLE_INPUT_STRING);
		} catch (IOException ioe) {
			Assert.fail("Invalid: " + ioe.getMessage());
		}
	}
}
