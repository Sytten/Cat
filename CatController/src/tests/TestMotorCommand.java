package tests;

import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.*;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import execution.MotorCommand;
import execution.MotorCommand.MotorCommandType;
import execution.PythonMotorExecutor;

public class TestMotorCommand {
	@Mock
	PythonMotorExecutor commandPython;
	@Rule 
	public MockitoRule mockitoRule = MockitoJUnit.rule();
	
	@InjectMocks MotorCommand testMotorCommand;
	MotorCommandType commandtype=MotorCommandType.valueOf("FOOD");
	@Test
	public void testConstructor(){
		Mockito.when(commandPython.execute(commandtype.toString())).thenReturn(true);
		testMotorCommand=new MotorCommand(commandtype,commandPython);
		assertTrue(testMotorCommand.execute());
	}
}
