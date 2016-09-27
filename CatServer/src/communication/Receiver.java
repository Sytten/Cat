package communication;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Receiver implements Runnable {

	private Queue<Message> inputBuffer = new LinkedList<Message>();
	private BufferedReader in = null;

	private boolean begin = false;
	private String currentInput;
	private String data;

	public Receiver(BufferedReader in) {
		this.in = in;
	}

	@Override
	public void run() {
		try {
			while (true) {
				while ((currentInput = in.readLine()) != null) {
					if (currentInput.indexOf("BEGIN") != -1) {
						data = "";
						begin = true;
					} else if (currentInput.indexOf("END") != -1) {
						synchronized (inputBuffer) {
							if (begin && !data.isEmpty()) {
								inputBuffer.add(new JSONMessage(data));
								System.out.println("Receiver: new message " + data);
							} else {
								System.out.println("Receiver: malformed input, please send enough parameters");
							}
							begin = false;
						}
					} else {
						if (begin && data.isEmpty()) {
							data = currentInput;
						} else {
							System.out.println("Receiver: malformed input, please start with BEGIN");
							data = "";
						}
					}
				}

				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					System.out.println("Receiver: can't sleep the thread");
				}
			}
		} catch (IOException e1) {
			System.out.println("Receiver: can't read from remote");
			return;
		} finally {
			System.out.println("Receiver: stopping thread now");
		}
	}

	public Message pop() {
		synchronized (inputBuffer) {
			if (!inputBuffer.isEmpty()) {
				return inputBuffer.remove();
			}
		}
		return null;
	}

}
