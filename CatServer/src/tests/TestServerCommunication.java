
package tests;

import java.io.IOException;
import java.net.UnknownHostException;

import org.junit.Test;

import communication.JSONMessage;
import communication.ServerCommunication;

public class TestServerCommunication {

	@Test
	public void testConstruct() throws UnknownHostException, IOException {
		@SuppressWarnings("unused")
		ServerCommunication serverCom = new ServerCommunication();
	}

	@Test
	public void testConstructPort() throws UnknownHostException, IOException {
		int port = 9999;
		@SuppressWarnings("unused")
		ServerCommunication serverCom = new ServerCommunication(port);
	}

	@Test
	public void testNull() throws UnknownHostException, IOException {
		int port = 9999;
		ServerCommunication serverCom = new ServerCommunication(port);
		serverCom.pop();
		JSONMessage message = new JSONMessage(true);
		serverCom.push(message);
	}
}
