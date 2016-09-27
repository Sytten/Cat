/**
 * Authors: Emile Fugulin
 * Date: 27 juin 2016
 * Create command from given message (Almost a factory)
 */

package main;

import execution.*;
import execution.MotorCommand.MotorCommandType;
import communication.*;

public class CommandCreator {

	public Command createCommand(Message message) {
		if (message.getType().equals("COMMAND")) {
			if (message.getParameter("MOTOR") != null)
				return createMotorCommand(message);
			else
				return null;
		}
		return null;
	}

	private Command createMotorCommand(Message message) {
		MotorCommand command = null;

		try {
			MotorCommandType type = MotorCommandType.valueOf(message.getParameter("MOTOR"));
			command = new MotorCommand(type, new PythonMotorExecutor());

		} catch (IllegalArgumentException e) {
			System.out.println("CommandCreator: Bad motor argument");
		}

		return command;
	}
}
