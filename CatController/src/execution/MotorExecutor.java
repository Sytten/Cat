/**
 * Authors: Emile Fugulin
 * Date: 27 juin 2016
 * Interface to launch motor scripts (Adaptor Pattern)
 */

package execution;

public interface MotorExecutor {

	public boolean execute(String argument);
}
