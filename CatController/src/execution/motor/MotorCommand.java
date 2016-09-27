
package execution.motor;

import execution.Command;

public class MotorCommand implements Command {

	public enum MotorCommandType { FOOD, WATER }
	
	private MotorCommandType commandType;
	private MotorExecutor commandExecutor;
	private String time;
	
	public MotorCommand(MotorCommandType commandtype, String Time, MotorExecutor commandExecutor) {
		this.commandType = commandtype;
		this.commandExecutor = commandExecutor;
		this.time = Time;
	}

	@Override
	public boolean execute() {
		return commandExecutor.execute(commandType.toString(), time);
	}

}
