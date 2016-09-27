package tests;

import java.util.HashMap;
import java.util.Map;
import org.junit.Assert;
import org.junit.Test;

import communication.JSONMessage;

public class TestJSONMessage {

    @Test
	public void testConstructor() {	
    	String type = "FEED";
    	 
    	Map<String, String> params = new HashMap<String, String>();
    	params.put("time", "10");
    	params.put("motorSpeed", "20");
        
    	JSONMessage json = new JSONMessage(type, params);;

        Assert.assertTrue("Type not equals ",type.equals(json.getType()));
    		
    	Assert.assertTrue("MotorSpeed param not equals",json.getParameter("motorSpeed").equals("20"));
    	Assert.assertTrue("Time not equals",json.getParameter("time").equals("10"));
    	
    	Assert.assertTrue("HashMap not equals",json.getParameter().equals(params));
	}
    
    @Test
	public void testStringConstructor() {    	
    	String Json = "{"+'"'+"parametres"+'"'+":{"+'"'+"motorSpeed"+'"'+":"+'"'+"20"+'"'+","+'"'+"time"+'"'+":"+'"'+"10"+'"'+"},"+'"'+"type"+'"'+":"+'"'+"FEED"+'"'+"}";
    			
    	JSONMessage json = new JSONMessage(Json);;
    	
    	Assert.assertTrue("StringConstructor not equals",json.toString().equals(Json));
	}
    
    @Test
	public void testBooleanConstructor() {
    	JSONMessage json = new JSONMessage(false);

        Assert.assertTrue("BooleanConstructor not equals",json.getParameter("ERROR").equals("FALSE"));
	}
}
