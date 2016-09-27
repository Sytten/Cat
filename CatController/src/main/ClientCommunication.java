package main;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.Queue;

public class ClientCommunication implements Runnable {

	private static final long WAIT = 1;
	private Queue<String> outputBuffer = new LinkedList<String>();
	

	private Queue<String> inputBuffer = new LinkedList<String>();

	private Socket socket = null;
	private PrintWriter os = null;
	private BufferedReader is = null;

	private String ipStr = "192.168.1.8";
	private final String END = "FIN";;
	private final String QUIT = "QUIT";

	private boolean running = true;

	

	public ClientCommunication(String[] args) {
		// ip definition
		if (args.length > 0) {
			if (args[0] != null)
				ipStr = args[0];
		}
	}

	public void pushOutput(String e) {
		synchronized (outputBuffer) {
			outputBuffer.add(e);
		}
	}

	public String popInput() {
		synchronized (inputBuffer) {
			if (!inputBuffer.isEmpty())
				return inputBuffer.remove();
			else
				return null;
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		// create new socket on port 9999
		try {
			socket = new Socket(ipStr, 9999);
			os = new PrintWriter(socket.getOutputStream());
			is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (UnknownHostException e) {
			System.err.println("Don't know about host: " + ipStr);
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection to: " + ipStr);
		}

		if (socket != null && os != null && is != null) {
			try {
				String currentInput;
				String data = "";

				Thread thread = new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						// output routine
						while (running) {
							synchronized (outputBuffer) {
								while (!outputBuffer.isEmpty()) {
									try {
										String in = outputBuffer.remove();
										os.println(in);
										os.flush();
										System.out.println("PUSHED DATA : " + in);
										
									} catch (Exception e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
								try {
									Thread.sleep(WAIT);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						}
					}

				});
				thread.start();

				while (running) {
					// input routine
					if ((currentInput = is.readLine()) != null) {
						// store the response in data
						data += currentInput;
						// check if current equal end command
						if (currentInput.indexOf(END) != -1) {
							// isolate command
							String command = data.substring(0, data.length() - 3);

							// add command to the buffer
							synchronized (inputBuffer) {
								System.out.println("added: " + command);
								inputBuffer.add(command);
							}

							// empty the data
							data = "";

						} else if (currentInput.indexOf(QUIT) != -1) {
							break;
						}

					}
					try {
						Thread.sleep(WAIT);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				os.close();
				is.close();
				socket.close();
			} catch (UnknownHostException e) {
				System.err.println("Trying to connect to unknown host: " + e);
			} catch (IOException e) {
				System.err.println("IOException:  " + e);
			}
		}
	}
	
	public Queue<String> getOutputBuffer() {
		return outputBuffer;
	}

	public Queue<String> getInputBuffer() {
		return inputBuffer;
	}
	
}
