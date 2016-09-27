package main;
/*
 * http://www.journaldev.com/1945/servlet-listener-example-servletcontextlistener-httpsessionlistener-and-servletrequestlistener
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;

public class ServerCommunication extends Thread {
	private boolean running = true;
	private Queue<String> outputBuffer = new LinkedList<String>();
	private Queue<String> inputBuffer = new LinkedList<String>();

	@Override
	public void run() {

		ServerSocket echoServer = null;
		BufferedReader is;
		PrintWriter os;
		Socket clientSocket = null;

		try {
			echoServer = new ServerSocket(9999);
		} catch (IOException e) {
			System.out.println(e);
		}

		try {
			clientSocket = echoServer.accept();
			System.out.println("Client: " + clientSocket.getInetAddress().toString());
			is = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			os = new PrintWriter(clientSocket.getOutputStream());

			Thread thread = new Thread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					while (true) {
						synchronized (outputBuffer) {
							while (!outputBuffer.isEmpty()) {
								String tmp = outputBuffer.remove();
								System.out.println("Sending: " + tmp);
								os.println(tmp);
								os.println("FIN");
								os.flush();
							}
						}

						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			});
			thread.start();
			// As long as we receive data, echo that data back to the client.
			while (true) {
				String input;
				while ((input = is.readLine()) != null) {;
					synchronized (inputBuffer) {
						System.out.println("Receving: " + input);
						inputBuffer.add(input);
					}

				}

				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	public void push(String s) {
		synchronized (outputBuffer) {
			outputBuffer.add(s);
		}
	}

	public String pop() {
		synchronized (inputBuffer) {
			if (!inputBuffer.isEmpty()) {
				return inputBuffer.remove();
			}
		}
		return null;
	}

	public void doShutdown() {
		running = false;
	}
	
	public Queue<String> getOutputBuffer() {
		return outputBuffer;
	}

	public Queue<String> getInputBuffer() {
		return inputBuffer;
	}

}
