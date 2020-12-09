package edu.wpi.cs.cs3733io.test.demo;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import edu.wpi.cs.cs3733io.ApproveAlternativeHandler;
import edu.wpi.cs.cs3733io.CreateChoiceHandler;
import edu.wpi.cs.cs3733io.RemoveApproveHandler;
import edu.wpi.cs.cs3733io.UserLoginHandler;
import edu.wpi.cs.cs3733io.http.AllResponse;
import edu.wpi.cs.cs3733io.http.ApproveAlternativeRequest;
import edu.wpi.cs.cs3733io.http.CreateChoiceRequest;
import edu.wpi.cs.cs3733io.http.RemoveApproveRequest;
import edu.wpi.cs.cs3733io.http.UserLoginRequest;

public class UserLoginHandlerTest extends LambdaTest {
	
	@Test
	public void testCreateAndLogin() {
		String[] alts = {"alt1", "alt2", "alt3", "alt4", "alt5"};
		CreateChoiceRequest ccr = new CreateChoiceRequest(1000, "choice description", alts);
		
		AllResponse resp = new CreateChoiceHandler().handleRequest(ccr, createContext("create"));
		Assert.assertEquals(resp.statusCode, 200);
		
		String choiceID = "";
		
		JsonObject jsonObject = new Gson().fromJson(resp.response, JsonObject.class);
		choiceID = jsonObject.get("ID").getAsString();
		UserLoginRequest ulr = new UserLoginRequest("user1", choiceID, "test");
		AllResponse a_resp = new UserLoginHandler().handleRequest(ulr, createContext("login"));
		Assert.assertEquals(200, a_resp.statusCode);
	}
}
