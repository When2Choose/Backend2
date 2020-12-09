package edu.wpi.cs.cs3733io.test.demo;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import edu.wpi.cs.cs3733io.ApproveAlternativeHandler;
import edu.wpi.cs.cs3733io.CreateChoiceHandler;
import edu.wpi.cs.cs3733io.RemoveApproveHandler;
import edu.wpi.cs.cs3733io.http.AllResponse;
import edu.wpi.cs.cs3733io.http.ApproveAlternativeRequest;
import edu.wpi.cs.cs3733io.http.CreateChoiceRequest;
import edu.wpi.cs.cs3733io.http.RemoveApproveRequest;

public class RemoveApproveHandlerTest extends LambdaTest {
	
	@Test
	public void testCreateAndApproveChoice() {
		String[] alts = {"alt1", "alt2", "alt3", "alt4", "alt5"};
		CreateChoiceRequest ccr = new CreateChoiceRequest(1000, "choice description", alts);
		
		AllResponse resp = new CreateChoiceHandler().handleRequest(ccr, createContext("create"));
		Assert.assertEquals(resp.statusCode, 200);
		
		//now approve
		String choiceID = "";
		
		JsonObject jsonObject = new Gson().fromJson(resp.response, JsonObject.class);
		choiceID = jsonObject.get("ID").getAsString();
		System.out.println(choiceID);
		int rnd = (int) (Math.random() * 1000);
		int rndAlt = (int) ((Math.random() * 5) + 1);
		ApproveAlternativeRequest aar = new ApproveAlternativeRequest(choiceID, "user" + rnd, rndAlt);
		AllResponse a_resp = new ApproveAlternativeHandler().handleRequest(aar, createContext("approve"));
		Assert.assertEquals(200, a_resp.statusCode);
		
		RemoveApproveRequest rar = new RemoveApproveRequest(choiceID, "user" + rnd, rndAlt);
		AllResponse ra_resp = new RemoveApproveHandler().handleRequest(rar, createContext("removeApprove"));
		Assert.assertEquals(200, ra_resp.statusCode);
	}

}
