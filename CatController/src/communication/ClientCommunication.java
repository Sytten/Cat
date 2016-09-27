package communication;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientCommunication implements Runnable {

	private Socket socket = null;
	private String ip = "192.168.1.8";
	private int port = 9999;
	private Thread connectionThread = null;
	private Connection connection = null;

	public ClientCommunication(String ip, int port) {
		this.ip = ip;
		this.port = port;
	}

	public ClientCommunication() {}

	@Override
	public void run() {
		while (true) {
			try {
				socket = new Socket(ip, port);
				connection = new Connection(socket);
				connectionThread = new Thread(connection);
				connectionThread.start();
				connectionThread.join();
				connection = null;

			} catch (UnknownHostException e) {
				System.err.println("ClientCommunication: don't know about host " + ip);
			} catch (IOException e) {
				System.err.println("ClientCommunication: can't read or write to remote " + ip);
			} catch (InterruptedException e) {
				System.err.println("ClientCommunication: stop thread now");
			}

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.err.println("ClientCommunication: thread can;t sleep");
			}
		}
	}
	
	public boolean push(Message msg) {
		return connection != null ? connection.push(msg) : false;
	}

	public Message pop() {
		return connection != null ? connection.pop() : null;
	}
}
