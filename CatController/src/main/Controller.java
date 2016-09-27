package main;

public class Controller {	
	
	public static void main(String[] args) {
		ClientCommunication communication = new ClientCommunication(args);
		Thread communicationThread = new Thread(communication);
		communicationThread.start();
		Gpios gpios = new Gpios();
		
		boolean running = true;
		while(running) {
			// check input buffer
			String input = communication.popInput();
			if(input != null) {
				if(gpios.set(input)) {
					gpios.setYellowLed(true);
					communication.pushOutput("RECU");
				}
				else {
					communication.pushOutput("ERROR");
				}
				
			}
			
			// check gpios
			if(gpios.buttonWasPressed()) {
				communication.pushOutput("BTN");
				gpios.setYellowLed(false);
				System.out.println("BTN");
			}
			
		}
		
	}
}