package main;

import communication.ClientCommunication;
import communication.JSONMessage;
import communication.Message;
import execution.Command;

public class Controller {
	
	boolean running = true;
	
	public void run(String[] args) {
		ClientCommunication communication = createClientCommunication(args);
		Thread communicationThread = new Thread(communication);
		communicationThread.start();

		while (isRunning()) {
			manageMessage(communication);
			
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				System.out.println("Controller: Thread can't sleep");
			}
		}
	}
	
	public CommandCreator createCommandCreator() {
		return new CommandCreator();
	}
	
	public ClientCommunication createClientCommunication(String[] args) {
		if (args != null && args.length > 0) {
			return new ClientCommunication(args[0], Integer.parseInt(args[1]));
		} else {
			return new ClientCommunication();
		}
	}
	
	public boolean isRunning() {
		return running;
	}
	
	private void manageMessage(ClientCommunication communication ){
		Message message = communication.pop();
		CommandCreator commandCreator = createCommandCreator();
		
		if (message != null) {
			Command command = commandCreator.createCommand(message);
			if (command != null) {
				boolean signal = command.execute();
				communication.push(new JSONMessage(!signal));
			}
		}
	}
}