package main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import communication.*;

/**
 * Servlet implementation class home
 */

@WebServlet("/home")
public class home extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServerCommunication socketRun = null;
	private Thread serverThread = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public home() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void init() {
		socketRun = createServerCommunication();
		serverThread = new Thread(socketRun);
		serverThread.start();
	}
	
	/**
	 * idée : but(ne plus avoir à repartir le serveur à chaque dexconnexion du client.)
	 * Nous pourrions avoir une url que quand elle est appelé elle crée un nouveau socket server en attente de connexion dans un threadpool.
	 * ainsi le client pourrait commencer en créant un socket server pour se connecter.
	 * puis il se connecte à la page web. 
	 * -pg
	 */

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String actionParam = request.getParameter("cat");
		if (actionParam == null) {
			// load webpage
			this.getServletContext().getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);
		} else {
			HashMap<String, String> param = new HashMap<String, String>();
			param.put("MOTOR", actionParam);
			socketRun.push(new JSONMessage("COMMAND",param));
			
			boolean running = true;
			List<String> list = new ArrayList<String>();			
			while (running) {
				Message message = socketRun.pop();
				if(message != null) {
					if (message.getType().equals("ACK")) {
						String input = message.getParameter("ERROR");
						if (input.equals("TRUE")) {
							list.add("ERROR");
							running = false;
						} else if (input.equals("FALSE")) {
							list.add("RELEASE");
							running = false;
						}
					}
				}
				
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			String json = new Gson().toJson(list);

			response.setContentType("CatServer/home");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	public void destroy() {
		serverThread.interrupt();
	}
	
	public ServerCommunication createServerCommunication() {
		return new ServerCommunication();
	}

}
