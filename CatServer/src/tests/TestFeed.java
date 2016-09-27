package tests;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;
import org.mockito.Mockito;

import communication.JSONMessage;
import communication.Message;
import communication.ServerCommunication;
import servlets.Feed;

public class TestFeed {

	@Test
	public void testDoGetWithoutParam() throws ServletException, IOException, InterruptedException {

		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		RequestDispatcher rd = mock(RequestDispatcher.class);
		final ServletContext servletContext = mock(ServletContext.class);

		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);

		when(response.getWriter()).thenReturn(pw);

		when(servletContext.getRequestDispatcher("/WEB-INF/feed.jsp")).thenReturn(rd);

		Feed servlet = new Feed() {
			private static final long serialVersionUID = 1L;

			public ServletContext getServletContext() {
				return servletContext; 
			}
		};
		servlet.init();
		servlet.doPost(request, response);
		Mockito.verify(rd).forward(request, response);

	}

	@Test
	public void testMotorFeed() throws ServletException, IOException, InterruptedException {

		HttpServletRequest request = mock(HttpServletRequest.class);
		when(request.getParameter("cat")).thenReturn("FEED");
		when(request.getParameter("qty")).thenReturn("2");

		HttpServletResponse response = mock(HttpServletResponse.class);
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		when(response.getWriter()).thenReturn(pw);

		ServerCommunication serverCom = mock(ServerCommunication.class);
		when(serverCom.pop()).thenReturn(null).thenReturn(new JSONMessage(true)).thenReturn(new JSONMessage(false));
		when(serverCom.push(any(Message.class))).thenReturn(true);
		Mockito.doNothing().when(serverCom).run();
		
		AtomicBoolean run = new AtomicBoolean();

		final ServletContext servletContext = mock(ServletContext.class);
		when(servletContext.getAttribute("ServerCommunication")).thenReturn(serverCom);
		when(servletContext.getAttribute("Running")).thenReturn(run);

		Feed servlet = new Feed() {
			private static final long serialVersionUID = 1L;

			public ServletContext getServletContext() {
				return servletContext; 
			}
		};
		servlet.init();
		servlet.doPost(request, response);

	}

}