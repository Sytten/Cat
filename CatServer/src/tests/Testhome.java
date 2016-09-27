package tests;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Assert;
import org.junit.Test;
import org.xml.sax.SAXException;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.meterware.httpunit.GetMethodWebRequest;
import com.meterware.httpunit.WebRequest;
import com.meterware.httpunit.WebResponse;
import com.meterware.servletunit.ServletRunner;
import com.meterware.servletunit.ServletUnitClient;

import communication.JSONMessage;
import communication.Message;
import communication.ServerCommunication;
import main.home;

/**
 * Unit test for hello world servlet using EasyMock and ServletUnit.
 */
public class Testhome {
	/**
	 * Example for a test using EasyMock on a SAP HANA Cloud application.
	 */
	@Test
	public void testHelloWorldUsingServletUnit() throws IOException, SAXException {
		// Prepare servlet emulation environment
		/*
		 * ServletRunner sr = new ServletRunner();
		 * sr.registerServlet("hello-world", home.class.getName());
		 * ServletUnitClient sc = sr.newClient();
		 * 
		 * // Call servlet WebRequest request = new
		 * GetMethodWebRequest("http://test.meterware.com/hello-world");
		 * request.setParameter("cat", "FEED"); WebResponse response =
		 * sc.getResponse(request);
		 * 
		 * // Check for string "Hello" in the returned response
		 * Assert.assertTrue(response.getText().indexOf("Hello World") != -1);
		 */
	}

	@Test
	public void testDoGetWithoutParam() {

		try {
			HttpServletRequest request = mock(HttpServletRequest.class);
			HttpServletResponse response = mock(HttpServletResponse.class);
			RequestDispatcher rd = mock(RequestDispatcher.class);
			final ServletContext servletContext = mock(ServletContext.class);

			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);

			when(response.getWriter()).thenReturn(pw);

			when(servletContext.getRequestDispatcher("/WEB-INF/home.jsp")).thenReturn(rd);

			home servlet = new home() {
				public ServletContext getServletContext() {
					return servletContext; // return the mock
				}
			};

			servlet.doPost(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testDoGetWithParam() {

		try {
			
			HttpServletRequest request = mock(HttpServletRequest.class);
			when(request.getParameter("cat")).thenReturn("FOOD");
			
			HttpServletResponse response = mock(HttpServletResponse.class);
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			when(response.getWriter()).thenReturn(pw);

			ServerCommunication serverCom = mock(ServerCommunication.class);
			when(serverCom.pop()).thenReturn(null).thenReturn(new JSONMessage(true)).thenReturn(new JSONMessage(false));
			when(serverCom.push(any(Message.class))).thenReturn(true);
			Mockito.doNothing().when(serverCom).run();

			home servlet = spy(new home());
			when(servlet.createServerCommunication()).thenReturn(serverCom);

			servlet.init();
			servlet.doPost(request, response);
			servlet.doPost(request, response);

		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}