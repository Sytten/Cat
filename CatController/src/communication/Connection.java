package communication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Connection implements Runnable {

	private Socket socket;
	private Sender sender = null;
	private Thread senderThread = null;
	private Receiver receiver = null;
	private Thread   receiverThread = null;

	public Connection(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			startSender();
			startReceiver();

		} catch (Exception e) {
			System.out.println("Connection: stopping thread now");
		} finally {
			receiverThread.interrupt();
			senderThread.interrupt();
			try {
				socket.close();
			} catch (IOException e) {}
			System.out.println("Connection: closing connection to " + socket.getInetAddress().toString());
		}
	}

	private void startSender() throws IOException{
		sender = new Sender(new PrintWriter(socket.getOutputStream()));
		senderThread = new Thread(sender);
		senderThread.start();
	}
	
	private void startReceiver() throws InterruptedException, IOException{
		receiver = new Receiver(new BufferedReader(new InputStreamReader(socket.getInputStream())));
		receiverThread = new Thread(receiver);
		receiverThread.start();
		receiverThread.join();
	}
	
	public Message pop() {
		return (receiver != null) ? receiver.pop() : null;
	}

	public boolean push(Message msg) {
		return (receiver != null) ? sender.push(msg) : false;
	}
	
}
