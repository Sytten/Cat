/**
 * Authors: Julien Larochelle 
 * Date: 27 juin 2016
 * Units Tests of ClientCommunication Class 
 */

package tests;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import org.junit.Test;

import communication.Connection;
import communication.JSONMessage;
import communication.ServerCommunication;

public class TestConnection {
	@Test
	public void testClientVersServeur() throws InterruptedException, UnknownHostException, IOException {
		ServerCommunication socketRun = null;
		Thread serverThread = null;

		socketRun = new ServerCommunication();
		serverThread = new Thread(socketRun);
		serverThread.start();
		Thread.sleep(4000);

		Socket socket = null;
		int port = 9999;
		Thread connectionThread = null;
		Connection connection = null;

		socket = new Socket("localhost", port);

		connection = new Connection(socket);
		connectionThread = new Thread(connection);
		connectionThread.start();
		Thread.sleep(4000);

		JSONMessage message = new JSONMessage(true);
		JSONMessage messageRetour = null;

		connection.push(message);

		while (messageRetour == null) {
			messageRetour = (JSONMessage) socketRun.pop();
		}

		messageRetour = null;

		socketRun.push(message);

		while (messageRetour == null) {
			messageRetour = (JSONMessage) connection.pop();
		}

		connectionThread.interrupt();
	}

	@Test
	public void testNull() throws UnknownHostException, IOException {
		Socket socket = null;
		int port = 9999;

		Connection connection = null;

		socket = new Socket("localhost", port);

		connection = new Connection(socket);
		connection.pop();

		JSONMessage message = new JSONMessage(true);
		connection.push(message);
	}
}
