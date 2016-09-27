package tests;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.mockito.Mockito;

import communication.ClientCommunication;
import communication.JSONMessage;
import communication.Message;
import execution.Command;
import main.CommandCreator;
import main.Controller;


public class TestController {
	
	@Test
	public void testRun() {
		
		CommandCreator creator = mock(CommandCreator.class);
		Command command = Mockito.mock(Command.class);
		when(command.execute()).thenReturn(true).thenReturn(false);
		when(creator.createCommand(any(Message.class))).thenReturn(null).thenReturn(command);
		
		ClientCommunication clientCom = Mockito.mock(ClientCommunication.class);
		when(clientCom.pop()).thenReturn(null).thenReturn(new JSONMessage(true));
		when(clientCom.push(any(Message.class))).thenReturn(true);
		Mockito.doNothing().when(clientCom).run();
		
		Controller controller = spy(new Controller());
		when(controller.createClientCommunication(any(String[].class))).thenReturn(clientCom);
		when(controller.createCommandCreator()).thenReturn(creator);
		when(controller.isRunning()).thenReturn(true).thenReturn(true).thenReturn(true).thenReturn(true).thenReturn(false);
		
		controller.run(new String[0]);
	}

	@Test
	public void testCreateClientCommunication() {
		Controller controller = new Controller();
		
		assertNotEquals(controller.createClientCommunication(new String[] {}), null);
		assertNotEquals(controller.createClientCommunication(new String[] {"localhost", "9999"}), null);
		
	}
}
