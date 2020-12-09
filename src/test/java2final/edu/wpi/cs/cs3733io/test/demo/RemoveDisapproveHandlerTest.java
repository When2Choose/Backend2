package edu.wpi.cs.cs3733io.test.demo;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import edu.wpi.cs.cs3733io.ApproveAlternativeHandler;
import edu.wpi.cs.cs3733io.CreateChoiceHandler;
import edu.wpi.cs.cs3733io.DisapproveAlternativeHandler;
import edu.wpi.cs.cs3733io.RemoveApproveHandler;
import edu.wpi.cs.cs3733io.RemoveDisapproveHandler;
import edu.wpi.cs.cs3733io.http.AllResponse;
import edu.wpi.cs.cs3733io.http.ApproveAlternativeRequest;
import edu.wpi.cs.cs3733io.http.CreateChoiceRequest;
import edu.wpi.cs.cs3733io.http.DisapproveAlternativeRequest;
import edu.wpi.cs.cs3733io.http.RemoveApproveRequest;
import edu.wpi.cs.cs3733io.http.RemoveDisapproveRequest;

public class RemoveDisapproveHandlerTest extends LambdaTest {
	
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
		DisapproveAlternativeRequest aar = new DisapproveAlternativeRequest(choiceID, "user" + rnd, rndAlt);
		AllResponse a_resp = new DisapproveAlternativeHandler().handleRequest(aar, createContext("approve"));
		Assert.assertEquals(200, a_resp.statusCode);
		
		RemoveDisapproveRequest rar = new RemoveDisapproveRequest(choiceID, "user" + rnd, rndAlt);
		AllResponse rd_resp = new RemoveDisapproveHandler().handleRequest(rar, createContext("removeDisapprove"));
		Assert.assertEquals(200, rd_resp.statusCode);
	}

}
