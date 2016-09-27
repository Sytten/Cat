package tests;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;
import org.mockito.Mockito;

import servlets.Home;

public class TestHome {

	@Test
	public void testDoGetWithoutParam() throws ServletException, IOException, InterruptedException {

		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		RequestDispatcher rd = mock(RequestDispatcher.class);
		final ServletContext servletContext = mock(ServletContext.class);

		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);

		when(response.getWriter()).thenReturn(pw);

		when(servletContext.getRequestDispatcher("/WEB-INF/home.jsp")).thenReturn(rd);

		Home servlet = new Home() {
			private static final long serialVersionUID = 1L;

			public ServletContext getServletContext() {
				return servletContext; 
			}
		};
		servlet.init();
		servlet.doPost(request, response);
		Mockito.verify(rd).forward(request, response);

	}
}