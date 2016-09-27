package tests;

import org.junit.Assert;
import org.junit.Test;

import execution.motor.PythonMotorExecutor;

public class TestPythonMotorExecutor {

	@Test
	public void testExecute() {
		String type = "FOOD";
		PythonMotorExecutor command = new PythonMotorExecutor("ExternalFiles/test.py");
		boolean test = command.execute(type, "2");
		System.out.println(test);
		Assert.assertTrue(command.execute(type, "2"));

		type = "FALSE";
		command = new PythonMotorExecutor("ExternalFiles/test.py");
		Assert.assertFalse(command.execute(type, "2"));
	}

}
