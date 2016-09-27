package communication;

import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;

public class Sender implements Runnable {

	private Queue<Message> outputBuffer = new LinkedList<Message>();
	private PrintWriter out = null;

	public Sender(PrintWriter out) {
		this.out = out;
	}

	@Override
	public void run() {
		while (true) {
			synchronized (outputBuffer) {
				while (!outputBuffer.isEmpty()) {
					try {
						Message msg = outputBuffer.remove();
						System.out.println("Sender: new message " + msg.toString());
						out.println("BEGINNING");
						out.flush();
						out.println(msg.toString());
						out.flush();
						out.println("ENDING");
						out.flush();
					} catch (Exception e) {
						System.out.println("Sender: can't send data to remote");
						return;
					}
				}
				try {
					outputBuffer.wait();
				} catch (InterruptedException e) {
					System.out.println("Sender: can't wait thread");
				}
			}
		}
	}

	public boolean push(Message msg) {
		synchronized (outputBuffer) {
			outputBuffer.add(msg);
			outputBuffer.notify();
		}
		return true;
	}

}
