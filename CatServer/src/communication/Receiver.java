package communication;

import java.io.BufferedReader;
import java.io.IOException;
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
		while (true) {
			try {
				receiveData();
				Thread.sleep(1);
			} catch (InterruptedException e) {
				System.out.println("Receiver: can't sleep the thread");
			} catch (IOException e1) {
				System.out.println("Receiver: can't read from remote");
				return;
			}
		}
	}

	public void receiveData() throws IOException {
		while ((currentInput = in.readLine()) != null) {
			if (currentInput.indexOf("BEGINNING") != -1) {
				data = "";
				begin = true;
			} else if (currentInput.indexOf("ENDING") != -1) {
				writeToBuffer();
			} else if (begin && data.isEmpty()) {
				data = currentInput;
			} else {
				System.out.println("Receiver: malformed input, please start with BEGIN");
				data = "";
			}
		}
	}

	public void writeToBuffer() {
		synchronized (inputBuffer) {
			if (begin && !data.isEmpty()) {
				inputBuffer.add(new JSONMessage(data));
				System.out.println("Receiver: new message " + data);
			} else {
				System.out.println("Receiver: malformed input, please send enough parameters");
			}
			begin = false;
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
