package tests;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import main.ServerCommunication;


public class ServerCommunicationTest {
	
	private ServerCommunication serverCom;
	
	@Before
	public void initServerCommunication() {
		serverCom = new ServerCommunication();
	}
	
	@After
	public void clearServerCommunication() {
		serverCom = null;
	}
	
	@Test
	public void testServerCommunication() {
		assertEquals(0, serverCom.getOutputBuffer().size());
		serverCom.push("allo");
		assertEquals(1, serverCom.getOutputBuffer().size());
		serverCom.push("allo2");
		assertEquals(2, serverCom.getOutputBuffer().size());
		
		serverCom.getInputBuffer().add("HELLO");
		assertEquals(1, serverCom.getInputBuffer().size());
		serverCom.getInputBuffer().add("HELLO2");
		assertEquals(2, serverCom.getInputBuffer().size());
		serverCom.pop();
		assertEquals(1, serverCom.getInputBuffer().size());
		serverCom.pop();
		assertEquals(0, serverCom.getInputBuffer().size());
		
		// try to re remove
		serverCom.pop();
		assertEquals(0, serverCom.getInputBuffer().size());
	}
	
	@Test
	public void testRun() {
		Thread thread = new Thread(serverCom);
		thread.start();
		try {
			Thread.sleep(1000);

			Socket socket = new Socket("127.0.0.1", 9999);
			PrintWriter os = new PrintWriter(socket.getOutputStream());
			BufferedReader is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			serverCom.push("HEY");
			System.out.println(is.readLine());
			
			os.println("HELLO");
			os.flush();
			
			while(serverCom.getInputBuffer().isEmpty())
				Thread.sleep(1000);
			
			System.out.println(serverCom.pop());
			
			os.close();
			is.close();
			socket.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
