/**
 * Authors: Donavan Martin 
 * 			Emile Fugulin
 * Date: 16 juin 2016
 * JSONMessage Class 
 */
package communication;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONObject;

public class JSONMessage extends Message {
	JSONObject obj = new JSONObject();
	Map<String, String> list = null;

	public JSONMessage(Boolean bool){	
		obj.put("type", "ACK");
		
		list = new HashMap<String, String>();
		list.put("ERROR", bool.toString().toUpperCase());
        obj.put("parametres", list);

	    //System.out.print(obj);  
	}  
	
	public JSONMessage(String s){
		list = new HashMap<String, String>();
		obj = new JSONObject(s);
		JSONObject jsonObject = (obj.getJSONObject("parametres"));
		Iterator<?> x = jsonObject.keys();

		while (x.hasNext()){
		    String key = (String) x.next();
		    list.put(key,jsonObject.getString(key));
		}
	    //System.out.print(obj);  
	}  
	
	public JSONMessage(String type, Map<String, String> params) {
	    obj.put("type", type);
        obj.put("parametres", params);
        list = params;
	    //System.out.print(obj);  
	}
	
	public String getType(){
		return obj.getString("type");
	}
	
	public Map<String, String> getParameter(){	
		return list;
	}
	
	public String getParameter(String param){
		return list.get(param);
	}
	
	public String toString(){
		return obj.toString();
	}

}
