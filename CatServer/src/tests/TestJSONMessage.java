package tests;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import communication.JSONMessage;

public class TestJSONMessage {

	@Test
	public void testType() {
		String type = "FEED";

		Map<String, String> JSONparams = new HashMap<String, String>();
		JSONparams.put("time", "10");
		JSONparams.put("motorSpeed", "20");

		JSONMessage message = new JSONMessage(type, JSONparams);
		;

		type = message.getType();

		Assert.assertTrue("type est fonctionnel", type.equals(message.getType()));

		HashMap<String, String> TESTmessage = (HashMap<String, String>) message.getParameter();
		System.out.print("\n");

		Assert.assertNotNull(TESTmessage);
		Assert.assertTrue("type est fonctionnel", message.getParameter("motorSpeed").equals("20"));
		Assert.assertTrue("type est fonctionnel", message.getParameter("time").equals("10"));

	}

	@Test
	public void testStringConstructor() {
		String type = "FEED";

		String Json = "{" + '"' + "parametres" + '"' + ":{" + '"' + "motorSpeed" + '"' + ":" + '"' + "20" + '"' + ","
				+ '"' + "time" + '"' + ":" + '"' + "10" + '"' + "}," + '"' + "type" + '"' + ":" + '"' + "FEED" + '"'
				+ "}";
		System.out.println("String constructor arg:" + Json);

		JSONMessage message = new JSONMessage(Json);
		;

		Assert.assertTrue("type est fonctionnel", type.equals(message.getType()));

		HashMap<String, String> TESTmessage = (HashMap<String, String>) message.getParameter();
		System.out.print("\n");

		Assert.assertNotNull(TESTmessage);
		Assert.assertTrue("type est fonctionnel", message.toString().equals(Json));

	}

	@Test
	public void testBooleanConstructor() {
		JSONMessage message = new JSONMessage(false);

		HashMap<String, String> TESTmessage = (HashMap<String, String>) message.getParameter();
		System.out.print("\n");

		Assert.assertNotNull(TESTmessage);
	}

}
