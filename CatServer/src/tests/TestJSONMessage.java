package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import communication.JSONMessage;

public class TestJSONMessage {

    // Test result
    @Test
	public void testType() {	
    	// Type constante
    	String type = "FEED";
    	 
     	// Params constantes
    	Map<String, String> JSONparams = new HashMap<String, String>();
    	JSONparams.put("time", "10");
    	JSONparams.put("motorSpeed", "20");
        
        // Message
    	JSONMessage message = new JSONMessage(type, JSONparams);;

        type = message.getType();

        // Test le type
        Assert.assertTrue("type est fonctionnel",type.equals(message.getType()));
    		
        // Test les paramètres
    	HashMap<String, String> TESTmessage = (HashMap) message.getParameter();
    	System.out.print("\n");
    	
    	Assert.assertTrue("type est fonctionnel",message.getParameter("motorSpeed").equals("20"));
    	Assert.assertTrue("type est fonctionnel",message.getParameter("time").equals("10"));
    	
	}
    
 // Test result
    @Test
	public void testStringConstructor() {
    	String type = "FEED";
    	
    	// Type constante
    	String Json = "{"+'"'+"parametres"+'"'+":{"+'"'+"motorSpeed"+'"'+":"+'"'+"20"+'"'+","+'"'+"time"+'"'+":"+'"'+"10"+'"'+"},"+'"'+"type"+'"'+":"+'"'+"FEED"+'"'+"}";
    	System.out.println("String constructor arg:" + Json);
    			
        // Message
    	JSONMessage message = new JSONMessage(Json);;

        // Test le type
        Assert.assertTrue("type est fonctionnel",type.equals(message.getType()));
    		
        // Test les paramètres
    	HashMap<String, String> TESTmessage = (HashMap) message.getParameter();
        System.out.print("\n");
     
        
    	Assert.assertTrue("type est fonctionnel",message.toString().equals(Json));

	}
    
    @Test
	public void testBooleanConstructor() {
        // Message
    	JSONMessage message = new JSONMessage(false);;

        // Test le type
    		
        // Test les paramètres
        HashMap<String, String> TESTmessage = (HashMap) message.getParameter();
        System.out.print("\n");
	}
    
}
