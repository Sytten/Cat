/**
 * Authors: Julien Larochelle 
 * Date: 27 juin 2016
 * Units Tests of ClientCommunication Class 
 */

package tests;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;


import org.junit.Assert;
import org.junit.Test;

import communication.*;

public class TestConnection {
	@Test
	public void testClientVersServeur() {
		// serveur
		ServerCommunication socketRun = null;
		Thread serverThread = null;
		
		socketRun = new ServerCommunication();
		serverThread = new Thread(socketRun);
		serverThread.start();
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		// client
		Socket socket = null;
		int port = 9999;
		Thread connectionThread = null;
		Connection connection = null;
	
			
		try {
			socket = new Socket("localhost", port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				

		connection = new Connection(socket);
		connectionThread = new Thread(connection);
		connectionThread.start();		
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
				
		JSONMessage message = new JSONMessage(true);
		JSONMessage messageRetour = null;
		
		// Send message from client to server
		connection.push(message);

		// Looking the message in the server
		while (messageRetour == null) {
			messageRetour = (JSONMessage) socketRun.pop(); 
		}

		messageRetour = null;
		
		socketRun.push(message);
		
		while (messageRetour == null) {
			messageRetour = (JSONMessage) connection.pop(); 
		}
		
		//serverThread.interrupt();
		connectionThread.interrupt();
		
	}
}
	
	
