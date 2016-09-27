package tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import main.Gpios;

public class GpiosTests {

	private Gpios gpios;
	
	@Before
	public void initContact() {		
		gpios = new Gpios();
	}
	
	@After
	public void clearContact() {
		gpios = null;
	}
	
	
	@Test
	public void tesGPIO() {
		assertTrue(gpios.setYellowLed(false));
		assertTrue(gpios.setYellowLed(true));
	
		assertTrue(gpios.set("-1"));
		assertTrue(gpios.set("0"));
		assertTrue(gpios.set("1"));
		assertTrue(gpios.set("2"));
		assertTrue(gpios.set("3"));
		assertTrue(gpios.set("4"));
		assertTrue(gpios.set("5"));
		assertTrue(gpios.set("6"));
		assertTrue(gpios.set("7"));
		assertTrue(gpios.set("8"));
		assertTrue(gpios.set("9"));
		assertTrue(gpios.set("10"));
		assertTrue(gpios.set("11"));
		assertTrue(gpios.set("12"));
		assertTrue(gpios.set("13"));
		assertTrue(gpios.set("14"));
		assertTrue(gpios.set("15"));
		assertFalse(gpios.set("100"));
		
		assertFalse(gpios.buttonWasPressed());
		
		
	}

}
