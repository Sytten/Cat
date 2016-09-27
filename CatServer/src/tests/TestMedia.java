package tests;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockMultipartHttpServletRequest;

import communication.JSONMessage;
import communication.Message;
import communication.ServerCommunication;
import servlets.Media;

public class TestMedia {

	@Test
	public void testDoGetWithoutParam() throws ServletException, IOException, InterruptedException {

		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		RequestDispatcher rd = mock(RequestDispatcher.class);
		final ServletContext servletContext = mock(ServletContext.class);

		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);

		when(response.getWriter()).thenReturn(pw);

		when(servletContext.getRequestDispatcher("/WEB-INF/media.jsp")).thenReturn(rd);

		Media servlet = new Media() {
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
	public void testDoPost() throws IOException, ServletException {

		HttpServletResponse response = mock(HttpServletResponse.class);
		RequestDispatcher rd = mock(RequestDispatcher.class);

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
		when(servletContext.getRequestDispatcher("/WEB-INF/media.jsp")).thenReturn(rd);

		Media servlet = new Media() {
			private static final long serialVersionUID = 1L;

			public ServletContext getServletContext() {
				return servletContext; 
			}
		};

		
		Path currentRelativePath = Paths.get("");
		String spath = currentRelativePath.toAbsolutePath().toString();
		Path path = Paths.get(spath + File.separator + "ExternalFiles" + File.separator + "AudioBase64.txt");
		
		String Sdata = readFile(path.toString(), Charset.defaultCharset());

		MockMultipartHttpServletRequest mockRequest = new MockMultipartHttpServletRequest();
		String boundary = "----q1w2e3r4t5y6u7i8o9";
		mockRequest.setContentType("multipart/form-data; boundary=" + boundary);
		System.out.println(new String(createFileContent(Sdata, boundary), StandardCharsets.UTF_8));
		mockRequest.setContent(createFileContent(Sdata, boundary));

		mockRequest.setMethod("POST");

		servlet.init();
		servlet.doPost(mockRequest, response);
	}

	public byte[] createFileContent(String sdata, String boundary) {

		String start = "--" + boundary + "\r\nContent-Disposition: form-data; name=\"data\"" + "\r\n\r\n";
		String data  = "data:" + sdata + "\r\n";
		String end   = "--" + boundary + "--";

		String r = start + data + end;
		return r.getBytes();		
	}

	static String readFile(String path, Charset encoding) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}

}