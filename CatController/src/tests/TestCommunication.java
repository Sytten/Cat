/**
 * Authors: Julien Larochelle 
 * Date: 27 juin 2016
 * Units Tests of ClientCommunication Class 
 */

package tests;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.junit.Assert;
import org.junit.Test;

import communication.*;

public class TestCommunication {

	@Test
	public void testClientVersServeur() {

		// Server
		ServerSocket socketserver;
		Socket socket;

		// Client
		ClientCommunication clientCommunication = new ClientCommunication("localhost", 2009);

		// Message
		JSONMessage message = new JSONMessage(true);
		JSONMessage messageRetour = null;

		// Communication Thread
		Thread communicationThread = new Thread(clientCommunication);
		communicationThread.start();

		try {
			// Initalise the server socket
			socketserver = new ServerSocket(2009);
			Thread.sleep(4000);

			// Connection client-server
			socket = socketserver.accept();
			System.out.println("Connecté !");

			// New Connection
			Connection connection = new Connection(socket);
			Thread connectionThread = new Thread(connection);
			connectionThread.start();
			Thread.sleep(4000);

			// Send message from client to server
			clientCommunication.push(message);

			// Looking the message in the server
			while (messageRetour == null) {
				messageRetour = (JSONMessage) connection.pop();
				Thread.sleep(1000);
			}

			// True if both are egals
			Assert.assertEquals(message.getType(), messageRetour.getType());

			// Killing the server
			socketserver.close();
			socket.close();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testServeurVersClient() {

		// Server
		ServerSocket socketserver;
		Socket socket;

		// Client
		ClientCommunication clientCommunication = new ClientCommunication("localhost", 2009);

		// Message
		JSONMessage message = new JSONMessage(true);
		JSONMessage messageRetour = null;

		// Communication Thread
		Thread communicationThread = new Thread(clientCommunication);
		communicationThread.start();

		try {
			// Initalise the server socket
			socketserver = new ServerSocket(2009);
			Thread.sleep(4000);

			// Connection client-server
			socket = socketserver.accept();
			System.out.println("Connecté !");

			// New Connection
			Connection connection = new Connection(socket);
			Thread connectionThread = new Thread(connection);
			connectionThread.start();
			Thread.sleep(4000);

			// Send message from server to client
			connection.push(message);

			// Looking the message in the client
			while (messageRetour == null) {
				messageRetour = (JSONMessage) clientCommunication.pop();
				Thread.sleep(1000);
			}

			// True if both are egals
			Assert.assertEquals(message.getType(), messageRetour.getType());

			// Killing the server
			socketserver.close();
			socket.close();
			
			socketserver = new ServerSocket(2009);
			Thread.sleep(4000);

			// Connection client-server
			//socket = socketserver.accept();
			System.out.println("Connecté !");

		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
