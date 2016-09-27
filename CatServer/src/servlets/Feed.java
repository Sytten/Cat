package servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import communication.JSONMessage;
import communication.Message;
import communication.ServerCommunication;

@WebServlet("/feed")
public class Feed extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AtomicBoolean running = null;
	private ServerCommunication serverCommunication = null;

	public Feed() {
		super();
	}

	public void init() {
		serverCommunication = (ServerCommunication) this.getServletContext().getAttribute("ServerCommunication");
		running = (AtomicBoolean) this.getServletContext().getAttribute("Running");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String actionParam = request.getParameter("cat");
		String qtyParam    = request.getParameter("qty");

		if (actionParam == null || qtyParam == null) {
			this.getServletContext().getRequestDispatcher("/WEB-INF/feed.jsp").forward(request, response);
		} else if (!running.get()) {
			running.set(true);
			sendCommand(actionParam, qtyParam); 
			String motorResp;
			try {
				motorResp = waitMotorResponse();
				sendResponse(response, motorResp);
			} catch (InterruptedException e) {
				sendResponse(response, "Motor timeout");
			}
		} else {
			sendResponse(response, "Motor already running");
		}
	}

	private String waitMotorResponse() throws InterruptedException {
		int time = 6000;
		String motorResponse = "";
		while (running.get() && time > 0) {
			if (manageMessage(motorResponse)) {
				return motorResponse.equals("TRUE") ? "ERROR" : "RELEASE" ;			
			} else {
				Thread.sleep(10);
				time--;
			}
		}
		running.set(false);
		return "TIMEOUT";
	}

	private boolean manageMessage(String motorResponse){
		Message message = serverCommunication.pop();
		if (message != null) {
			motorResponse = getACKParameter(message, "ERROR");
			if(!motorResponse.isEmpty()){
				running.set(false);
				return true;			
			}
		}
		return false;
	}
	
	private void sendCommand(String actionParam, String qtyParam) {
		HashMap<String, String> param = new HashMap<String, String>();
		param.put("MOTOR", actionParam);
		param.put("QTY", qtyParam);
		serverCommunication.push(new JSONMessage("COMMAND", param));
	}

	private void sendResponse(HttpServletResponse response, String message) throws IOException {
		response.setContentType("CatServer/feed");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(message);
	}
	
	private String getACKParameter(Message message, String param) {
		return message.getType().equals("ACK") ? message.getParameter(param) : null;
	}
}
