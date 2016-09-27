package tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import main.ClientCommunication;

public class ClientCommunicationTest {
	
	private ClientCommunication clientCom;
	private ClientCommunication clientCom2;
	
	@Before
	public void initClientCommunication() {	
		String[] args = {"localhost"};
		clientCom = new ClientCommunication(args);
	}	
	
	@After
	public void clearClientCommunication() {
		clientCom = null;
		clientCom2 = null;
	}

	@Test
	public void testClientCommunication() {
		assertEquals(0, clientCom.getOutputBuffer().size());
		clientCom.pushOutput("allo");
		assertEquals(1, clientCom.getOutputBuffer().size());
		clientCom.pushOutput("allo2");
		assertEquals(2, clientCom.getOutputBuffer().size());
		
		clientCom.getInputBuffer().add("HELLO");
		assertEquals(1, clientCom.getInputBuffer().size());
		clientCom.getInputBuffer().add("HELLO2");
		assertEquals(2, clientCom.getInputBuffer().size());
		clientCom.popInput();
		assertEquals(1, clientCom.getInputBuffer().size());
		clientCom.popInput();
		assertEquals(0, clientCom.getInputBuffer().size());
		
		// try to re remove
		clientCom.popInput();
		assertEquals(0, clientCom.getInputBuffer().size());
	}
	
	@Test
	public void testRun() {
		Thread thread = new Thread(clientCom);
		thread.start();
		clientCom.pushOutput("HELLO");
		try {
			System.out.println("Waiting for the answer");
			Thread.sleep(5000);
		} catch (InterruptedException e) {
		}
		clientCom.pushOutput("QUIT");
		try {
			System.out.println("Waiting for the answer QUIT");
			Thread.sleep(5000);
		} catch (InterruptedException e) {
		}
		
	}
	
	@Test
	public void testExceptions() {
		clientCom = null;
		String []args = {null};
		clientCom2 = new ClientCommunication(args);
		String []args2 = {};
		clientCom2 = new ClientCommunication(args2);
	}

	@Test
	public void testServer() {
		String []args = {"localhost"};
		clientCom2 = new ClientCommunication(args);
		//  TODO simulate server
	}
	
	
	
}
