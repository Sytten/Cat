
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

		ServerSocket socketserver;
		Socket socket;

		ClientCommunication clientCommunication = new ClientCommunication("localhost", 2009);

		JSONMessage message = new JSONMessage(true);
		JSONMessage messageRetour = null;

		Thread communicationThread = new Thread(clientCommunication);
		communicationThread.start();

		try {
			socketserver = new ServerSocket(2009);
			Thread.sleep(4000);

			socket = socketserver.accept();
			System.out.println("Connecté !");

			Connection connection = new Connection(socket);
			Thread connectionThread = new Thread(connection);
			connectionThread.start();
			Thread.sleep(4000);

			clientCommunication.push(message);

			while (messageRetour == null) {
				messageRetour = (JSONMessage) connection.pop();
				Thread.sleep(1000);
			}

			Assert.assertEquals(message.getType(), messageRetour.getType());

			socketserver.close();
			socket.close();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testServeurVersClient() {

		ServerSocket socketserver;
		Socket socket;

		ClientCommunication clientCommunication = new ClientCommunication("localhost", 2009);

		JSONMessage message = new JSONMessage(true);
		JSONMessage messageRetour = null;

		Thread communicationThread = new Thread(clientCommunication);
		communicationThread.start();

		try {
			socketserver = new ServerSocket(2009);
			Thread.sleep(4000);

			socket = socketserver.accept();
			System.out.println("Connecté !");

			Connection connection = new Connection(socket);
			Thread connectionThread = new Thread(connection);
			connectionThread.start();
			Thread.sleep(4000);

			connection.push(message);

			while (messageRetour == null) {
				messageRetour = (JSONMessage) clientCommunication.pop();
				Thread.sleep(1000);
			}

			Assert.assertEquals(message.getType(), messageRetour.getType());

			socketserver.close();
			socket.close();
			
			socketserver = new ServerSocket(2009);
			Thread.sleep(4000);

			System.out.println("Connecté !");

		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
