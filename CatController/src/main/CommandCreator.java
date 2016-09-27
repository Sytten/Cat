
package main;

import execution.Command;
import execution.audio.AudioCommand;
import execution.audio.OmxPlayerAudioExecutor;
import execution.motor.MotorCommand;
import execution.motor.PythonMotorExecutor;
import execution.motor.MotorCommand.MotorCommandType;

import java.io.File;
import java.io.IOException;

import communication.Message;

public class CommandCreator {

	public Command createCommand(Message message) {
		if (message.getType().equals("COMMAND")) {
			if (message.getParameter("MOTOR") != null)
				return createMotorCommand(message);
			else if (message.getParameter("AUDIO") != null)
				return createAudioCommand(message);
		}
		return null;
	}

	private Command createAudioCommand(Message message) {
		String audioData = message.getParameter("DATA");

		if (audioData != null) {
			try {
				File audioFile = AudioUtilities.convertAudioStringToFile(audioData);
				return new AudioCommand(audioFile, new OmxPlayerAudioExecutor());
			} catch (IOException e) {
				System.out.println("CommandCreator: Bad audio data");
			}
		}
		return null;
	}

	private Command createMotorCommand(Message message) {
		try {
			String time = TimeUtilities.parseTime(message, 0, 5);
			MotorCommandType type = MotorCommandType.valueOf(message.getParameter("MOTOR"));

			if (time != null && type != null) {

				return new MotorCommand(type, time, new PythonMotorExecutor());

			}
		} catch (IllegalArgumentException e) {
			System.out.println("CommandCreator: Bad motor argument");
		}
		return null;
	}
}
