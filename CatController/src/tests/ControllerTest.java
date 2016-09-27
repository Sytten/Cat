package tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import main.Controller;

public class ControllerTest {

	
	
	
	@Test
	public void allumeLed() {
		String args[] = {"localhost"};
		Controller.main(args);
	}

}
