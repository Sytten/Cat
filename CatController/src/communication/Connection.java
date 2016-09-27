package communication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class Connection implements Runnable {

	private Socket socket;
	
	private Sender sender = null;
	private Receiver receiver = null;
	private Thread senderThread = null;
	private Thread receiverThread = null;

	public Connection(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			sender = new Sender(new PrintWriter(socket.getOutputStream()));
			senderThread = new Thread(sender);
			senderThread.start();

			receiver = new Receiver(new BufferedReader(new InputStreamReader(socket.getInputStream())));
			receiverThread = new Thread(receiver);
			receiverThread.start();
			
			receiverThread.join();
			
		} catch (IOException e) {
			System.out.println("Connection: can't read or write to remote");
		} catch (InterruptedException e) {
			System.out.println("Connection: stopping thread now");
		}
		finally {
			receiverThread.interrupt();
			senderThread.interrupt();
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("Connection: closing connection to " + socket.getInetAddress().toString());
		}
	}
	
	public Message pop() {
		if(receiver != null) {
			return receiver.pop();
		}
		return null;
	}
	
	public boolean push(Message msg) {
		if(receiver != null) {
			return sender.push(msg);
		}
		return false;
	}

}
