package tests;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import communication.JSONMessage;
import execution.Command;
import main.CommandCreator;

public class TestCommandCreator {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void testCommandWrong() {
		String type = "ACK";

		Map<String, String> params = new HashMap<String, String>();
		params.put("MOTOR", "FOOD");

		JSONMessage json = new JSONMessage(type, params);

		Command testCommand;
		CommandCreator testCreator = new CommandCreator();
		testCommand = testCreator.createCommand(json);

		assertEquals(testCommand, null);
	}

	@Test
	public void testCommand() {
		String type = "COMMAND";

		Map<String, String> params = new HashMap<String, String>();
		params.put("MOTOR", "FOOD");
		params.put("QTY", "2");

		JSONMessage json = new JSONMessage(type, params);

		Command testCommand = null;
		CommandCreator testCreator = new CommandCreator();
		testCommand = testCreator.createCommand(json);

		assertNotNull(testCommand);
	}

	@Test
	public void testMotorWrong() {
		String type = "COMMAND";

		Map<String, String> params = new HashMap<String, String>();
		params.put("MOTOR", "ACK");
		params.put("QTY", "5");

		JSONMessage json = new JSONMessage(type, params);

		Command testCommand;
		CommandCreator testCreator = new CommandCreator();
		testCommand = testCreator.createCommand(json);

		assertEquals(testCommand,null);
	}

	@Test
	public void testMotorNull() {
		String type = "COMMAND";

		Map<String, String> params = new HashMap<String, String>();
		params.put("MOTOR", null);

		JSONMessage json = new JSONMessage(type, params);

		Command testCommand;
		CommandCreator testCreator = new CommandCreator();
		testCommand = testCreator.createCommand(json);

		assertEquals(testCommand, null);
	}
	
	@Test
	public void testAudioCommand() {
		String audioData = "";
		Path currentRelativePath = Paths.get("");
		String path = currentRelativePath.toAbsolutePath().toString();
		File audioFile = new File(path + File.separator + "ExternalFiles" + File.separator + "AudioBase64.txt");

		try (BufferedReader br = new BufferedReader(new FileReader(audioFile))) {
			String line;
			while ((line = br.readLine()) != null) {
				audioData += line;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String type = "COMMAND";

		Map<String, String> params = new HashMap<String, String>();
		params.put("AUDIO", "audio");
		params.put("DATA", audioData);

		JSONMessage json = new JSONMessage(type, params);

		Command testCommand;
		CommandCreator testCreator = new CommandCreator();
		testCommand = testCreator.createCommand(json);

		assertNotNull(testCommand);
		
		
		try {

			File file = new File(System.getProperty("user.home") + File.separator + "VoiceData.ogg");

			if (file.delete()) {
				System.out.println(file.getName() + " is deleted!");
			} else {
				System.out.println("Delete operation is failed.");
			}

		} catch (Exception e) {

			e.printStackTrace();

		}
	}
	
	
}
