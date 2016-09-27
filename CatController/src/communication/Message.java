package communication;

import java.util.Map;

public abstract class Message {
	
	public abstract String getType();

	public abstract Map<String, String> getParameter();
	
	public abstract String getParameter(String param);

	public abstract String toString();

}
