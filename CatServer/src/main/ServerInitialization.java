package main;

import java.util.concurrent.atomic.AtomicBoolean;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import communication.ServerCommunication;

public class ServerInitialization implements ServletContextListener {

	Thread serverThread = null;

	@Override
	public void contextDestroyed(ServletContextEvent contextEvent) {
		serverThread.interrupt();

		contextEvent.getServletContext().removeAttribute("ServerCommunication");
	}

	@Override
	public void contextInitialized(ServletContextEvent contextEvent) {
		ServerCommunication serverCommunication = createServerCommunication();
		serverThread = new Thread(serverCommunication);
		serverThread.start();

		contextEvent.getServletContext().setAttribute("ServerCommunication", serverCommunication);
		contextEvent.getServletContext().setAttribute("Running", new AtomicBoolean());
	}

	public ServerCommunication createServerCommunication() {
		return new ServerCommunication();
	}

}
