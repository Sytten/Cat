package servlets;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;

import communication.JSONMessage;
import communication.Message;
import communication.ServerCommunication;

@WebServlet("/media")
public class Media extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServerCommunication serverCommunication = null;
	private AtomicBoolean running = null;

	public Media() {
		super();
	}

	public void init() {
		serverCommunication = (ServerCommunication) this.getServletContext().getAttribute("ServerCommunication");
		running = (AtomicBoolean) this.getServletContext().getAttribute("Running");
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (!isMultipart) {
			this.getServletContext().getRequestDispatcher("/WEB-INF/media.jsp").forward(request, response);
		} else if (!running.get()) {
			running.set(true);
			try {
				sendAudio(request);
				String audioResp;
				audioResp = waitAudioResponse();
				sendResponse(response, audioResp); 
			} catch (FileUploadException | InterruptedException e) {
				sendResponse(response, "Audio timeout");
			}
		} else {
			sendResponse(response, "Audio already running");
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	private void sendAudio(HttpServletRequest request) throws FileUploadException, IOException {
		ServletFileUpload upload = new ServletFileUpload();
		FileItemIterator iter = upload.getItemIterator(request);
		
		while (iter.hasNext()) {	
			FileItemStream item = iter.next();
			String fieldName = item.getFieldName();
			InputStream stream = item.openStream();
			if (item.isFormField() && fieldName.equals("data")) {
				sendData(stream);
			}
		}
	}
	
	private void sendData(InputStream stream) throws IOException{
		String tmpStr = Streams.asString(stream);
		HashMap<String, String> param = new HashMap<String, String>();
		param.put("DATA", tmpStr);
		param.put("AUDIO", "TRUE");
		serverCommunication.push(new JSONMessage("COMMAND", param));
	}
	
	private String waitAudioResponse() throws InterruptedException {
		int time = 6000; 
		String audioResponse = "";
		while (running.get() && time > 0) {
			if (manageMessage(audioResponse)) {
				return audioResponse.equals("TRUE") ? "ERROR" : "RELEASE" ;			
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
	
	private String getACKParameter(Message message, String param) {
		return message.getType().equals("ACK") ? message.getParameter(param) : null;
	}
	
	private void sendResponse(HttpServletResponse response, String message) throws IOException {
		response.setContentType("CatServer/media");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(message);
	}
}
