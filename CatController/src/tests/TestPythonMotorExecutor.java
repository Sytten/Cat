package tests;

import org.junit.Test;
import org.junit.Assert;
import execution.*;

public class TestPythonMotorExecutor {
	
	@Test
	public void testExecute(){
		String type = "FOOD";
		PythonMotorExecutor command = new PythonMotorExecutor("test.py");
		boolean test=command.execute(type);
		System.out.println(test);
		Assert.assertTrue(command.execute(type));
		
		type = "FALSE";
		command = new PythonMotorExecutor("test.py");
		Assert.assertFalse(command.execute(type));
	}
	
}
