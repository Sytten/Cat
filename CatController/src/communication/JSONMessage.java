package communication;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONObject;

public class JSONMessage extends Message {
	JSONObject obj = new JSONObject();
	Map<String, String> list = null;

	public JSONMessage(Boolean bool) {
		obj.put("type", "ACK");

		list = new HashMap<String, String>();
		list.put("ERROR", bool.toString().toUpperCase());
		obj.put("parametres", list);
	}

	public JSONMessage(String s) {
		list = new HashMap<String, String>();
		obj = new JSONObject(s);
		JSONObject jsonObject = (obj.getJSONObject("parametres"));
		Iterator<?> keyList = jsonObject.keys();

		while (keyList.hasNext()) {
			String key = (String) keyList.next();
			list.put(key, jsonObject.getString(key));
		}
	}

	public JSONMessage(String type, Map<String, String> params) {
		obj.put("type", type);
		obj.put("parametres", params);
		list = params;
	}

	public String getType() {
		return obj.getString("type");
	}

	public Map<String, String> getParameter() {
		return list;
	}

	public String getParameter(String param) {
		return list.get(param);
	}

	public String toString() {
		return obj.toString();
	}
	
}
