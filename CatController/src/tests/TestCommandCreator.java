package tests;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import communication.JSONMessage;
import execution.Command;
import main.CommandCreator;

public class TestCommandCreator {

	/*
	 * @Rule public MockitoRule mockitoRule = MockitoJUnit.rule();
	 * 
	 * @Mock MotorCommand motorCommand;
	 * 
	 * @InjectMocks CommandCreator testCreator; //@InjectMocks Command
	 * testCommand;
	 */
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void testCommandWrong() {
		// Type
		String type = "ACK";

		// Params
		Map<String, String> params = new HashMap<String, String>();
		params.put("MOTOR", "FOOD");

		// Message
		JSONMessage json = new JSONMessage(type, params);

		Command testCommand;
		CommandCreator testCreator = new CommandCreator();
		testCommand = testCreator.createCommand(json);

		assertEquals(testCommand, null);
	}

	@Test
	public void testCommand() {
		// Type
		String type = "COMMAND";

		// Params
		Map<String, String> params = new HashMap<String, String>();
		params.put("MOTOR", "FOOD");

		// Message
		JSONMessage json = new JSONMessage(type, params);

		// JSONMessage messageErrone= new JSONMessage(true);
		Command testCommand;
		CommandCreator testCreator = new CommandCreator();
		testCommand = testCreator.createCommand(json);

		assertNotNull(testCommand);
	}

	@Test
	public void testMotorWrong() {
		// Type
		String type = "COMMAND";

		// Params
		Map<String, String> params = new HashMap<String, String>();
		params.put("MOTOR", "ACK");

		// Message
		JSONMessage json = new JSONMessage(type, params);

		Command testCommand;
		CommandCreator testCreator = new CommandCreator();
		testCommand = testCreator.createCommand(json);

		assertEquals(testCommand,null);
		//thrown.expect(IllegalArgumentException.class);
	}

	@Test
	public void testMotorNull() {
		// Type
		String type = "COMMAND";

		// Params
		Map<String, String> params = new HashMap<String, String>();
		params.put("MOTOR", null);

		// Message
		JSONMessage json = new JSONMessage(type, params);

		Command testCommand;
		CommandCreator testCreator = new CommandCreator();
		testCommand = testCreator.createCommand(json);

		// assertEquals(testCommand,null);
		assertEquals(testCommand, null);
	}
}
