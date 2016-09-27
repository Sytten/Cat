package communication;

import java.net.ServerSocket;
import java.net.Socket;

public class ServerCommunication implements Runnable {

	private Connection connection = null;
	private Thread connectionThread = null;
	private int port = 9999;

	public ServerCommunication(int port) {
		this.port = port;
	}

	public ServerCommunication() {
	}

	@Override
	public void run() {

		ServerSocket serverSocket = null;
		Socket clientSocket = null;

		try {
			serverSocket = new ServerSocket(port);
			while (true) {

				clientSocket = serverSocket.accept();

				System.out.println("ServerCommunication: client connected " + clientSocket.getInetAddress().toString());

				if (connectionThread != null) {
					connectionThread.interrupt();
				}

				connection = new Connection(clientSocket);
				connectionThread = new Thread(connection);
				connectionThread.start();
			}

		} catch (Exception e) {
			System.out.println("ServerCommunication: can't open server socket");
		}
	}

	public Message pop() {
		return (connection != null) ? connection.pop() : null;
	}

	public boolean push(Message msg) {
		return (connection != null) ? connection.push(msg) : false;
	}
}
