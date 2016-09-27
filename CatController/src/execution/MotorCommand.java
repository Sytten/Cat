/**
 * Authors: Emile Fugulin
 * Date: 27 juin 2016
 * Concrete class for a motor command (Command Pattern)
 */

package execution;

public class MotorCommand implements Command {

	public enum MotorCommandType { FOOD, WATER }
	
	private MotorCommandType commandType;
	private MotorExecutor commandExecutor;
	
	public MotorCommand(MotorCommandType commandtype, MotorExecutor commandExecutor) {
		this.commandType = commandtype;
		this.commandExecutor = commandExecutor;
	}

	@Override
	public boolean execute() {
		return commandExecutor.execute(commandType.toString());
	}

}
