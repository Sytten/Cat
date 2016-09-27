/**
 * Authors: Donavan Martin 
 * 			Emile Fugulin
 * Date: 16 juin 2016
 * Units Tests of JSONMessage Class 
 */
package tests;

import java.util.HashMap;
import java.util.Map;
import org.junit.Assert;
import org.junit.Test;

import communication.JSONMessage;

public class TestJSONMessage {

    @Test
	public void testConstructor() {	
    	// Type
    	String type = "FEED";
    	 
     	// Params
    	Map<String, String> params = new HashMap<String, String>();
    	params.put("time", "10");
    	params.put("motorSpeed", "20");
        
        // Message
    	JSONMessage json = new JSONMessage(type, params);;

        // Type equals
        Assert.assertTrue("Type not equals ",type.equals(json.getType()));
    		
        // params equals
    	Assert.assertTrue("MotorSpeed param not equals",json.getParameter("motorSpeed").equals("20"));
    	Assert.assertTrue("Time not equals",json.getParameter("time").equals("10"));
    	
    	// HashMap equals
    	Assert.assertTrue("HashMap not equals",json.getParameter().equals(params));
	}
    
    @Test
	public void testStringConstructor() {    	
    	// This string/json represent
    	String Json = "{"+'"'+"parametres"+'"'+":{"+'"'+"motorSpeed"+'"'+":"+'"'+"20"+'"'+","+'"'+"time"+'"'+":"+'"'+"10"+'"'+"},"+'"'+"type"+'"'+":"+'"'+"FEED"+'"'+"}";
    			
        // Constructor 1 String 
    	JSONMessage json = new JSONMessage(Json);;
    	
        // Equals
    	Assert.assertTrue("StringConstructor not equals",json.toString().equals(Json));
	}
    
    @Test
	public void testBooleanConstructor() {
        // Message
    	JSONMessage json = new JSONMessage(false);

        // Check equals params
        Assert.assertTrue("BooleanConstructor not equals",json.getParameter("ERROR").equals("FALSE"));
	}
}
