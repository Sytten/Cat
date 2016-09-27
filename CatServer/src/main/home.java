package main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * Servlet implementation class home
 */

@WebServlet("/home")
public class home extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServerCommunication socketRun = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public home() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void init() {
		try {
			socketRun = new ServerCommunication();
			socketRun.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String selectedIndex = request.getParameter("gpio");
		if (selectedIndex == null) {
			// load webpage
			this.getServletContext().getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);
		} else {
			socketRun.push(selectedIndex);
			// TODO wait for answers
			
			boolean running = true;
			List<String> list = new ArrayList<String>();			
			while (running) {
				String input = socketRun.pop();
				if (input != null) {
					if (input.equals("ERROR")) {
						list.add("ERROR");
						running = false;
					} else if (input.equals("BTN")) {
						list.add("RELEASE");
						running = false;
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	public void destroy() {
		try {
			socketRun.doShutdown();
			socketRun.interrupt();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
