package tests;

import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.*;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import execution.motor.MotorCommand;
import execution.motor.PythonMotorExecutor;
import execution.motor.MotorCommand.MotorCommandType;

public class TestMotorCommand {
	@Mock
	PythonMotorExecutor commandPython;
	@Rule 
	public MockitoRule mockitoRule = MockitoJUnit.rule();
	
	@InjectMocks MotorCommand testMotorCommand;
	MotorCommandType commandtype=MotorCommandType.valueOf("FOOD");
	@Test
	public void testConstructor(){
		Mockito.when(commandPython.execute(Mockito.any(String.class), Mockito.any(String.class))).thenReturn(true);
		testMotorCommand=new MotorCommand(commandtype, "2",commandPython);
		assertTrue(testMotorCommand.execute());
	}
}
