package communication;
/*
 * http://www.journaldev.com/1945/servlet-listener-example-servletcontextlistener-httpsessionlistener-and-servletrequestlistener
 */

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

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

			try {
				while (true) {

					clientSocket = serverSocket.accept();

					System.out.println(
							"ServerCommunication: client connected " + clientSocket.getInetAddress().toString());

					if (connectionThread != null) {
						connectionThread.interrupt();
					}

					connection = new Connection(clientSocket);

					connectionThread = new Thread(connection);
					connectionThread.start();
				}
			} catch (Exception e) {
				System.out.println("ServerCommunication: can't read or write to remote "
						+ clientSocket.getInetAddress().toString());
			}
		} catch (IOException e) {
			System.out.println("ServerCommunication: can't open server socket");
		}
	}

	public boolean push(Message msg) {
		if (connection != null) {
			return connection.push(msg);
		}
		return false;
	}

	public Message pop() {
		if (connection != null) {
			return connection.pop();
		}
		return null;
	}
}
