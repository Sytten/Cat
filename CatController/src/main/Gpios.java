package main;

// .jar should be in folder: WebContent/WEB-INF/lib/
import com.pi4j.io.gpio.*;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import com.pi4j.wiringpi.GpioUtil;

public class Gpios {

	private GpioController gpio;

	// Default constructor
	private final GpioPinDigitalOutput led0;// Led 0
	private final GpioPinDigitalOutput led1;// led 1
	private final GpioPinDigitalOutput led2; // led 2
	private final GpioPinDigitalOutput led3; // Led 3
	private final GpioPinDigitalOutput ledJaune; // Led jaune
	private final GpioPinDigitalOutput vcc; // vcc
	private final GpioPinDigitalInput myButton; // button
	
	private boolean buttonPressed = false;

	public Gpios() {
		System.out.println("<--Pi4J--> Non-Privileged GPIO Example ... started.");

		// we can use this utility method to pre-check to determine if
		// privileged access is required on the running system
		if (GpioUtil.isPrivilegedAccessRequired()) {
			System.err.println("*****************************************************************");
			System.err.println("Privileged access is required on this system to access GPIO pins!");
			System.err.println("*****************************************************************");
		}

		// ----------------------
		// ATTENTION
		// ----------------------
		// YOU CANNOT USE ANY HARDWARE PWM OR CLOCK FUNCTIONS WHILE ACCESSING
		// NON-PRIVILEGED GPIO.
		// THIS METHOD MUST BE INVOKED BEFORE CREATING A GPIO CONTROLLER
		// INSTANCE.
		GpioUtil.enableNonPrivilegedAccess();

		// create gpio controller
		final GpioController gpio = GpioFactory.getInstance();

		this.gpio = gpio;
		// Gpio and Shutdown all leds
		led0 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_25, "MyLED0", PinState.LOW); // led0
		led1 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_24, "MyLED1", PinState.LOW); // led1
		led2 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_23, "MyLED2", PinState.LOW); // led2
		led3 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_29, "MyLED3", PinState.LOW); // Led3
		ledJaune = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_28, "LEDJAUNE", PinState.LOW); // ledjaune
		vcc = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_27, "VCC", PinState.HIGH); // VCC

		// create button
		myButton = gpio.provisionDigitalInputPin(RaspiPin.GPIO_07, PinPullResistance.PULL_DOWN);

		// create and register gpio pin listener
		myButton.addListener(new GpioPinListenerDigital() {
			@Override
			public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
				// display pin state on console
				if(event.getState() == PinState.HIGH && ledJaune.getState() == PinState.HIGH)
					buttonPressed = true;
			}

		});

		led0.setShutdownOptions(true, PinState.LOW);
		led1.setShutdownOptions(true, PinState.LOW);
		led2.setShutdownOptions(true, PinState.LOW);
		led3.setShutdownOptions(true, PinState.LOW);
		ledJaune.setShutdownOptions(true, PinState.LOW);
		vcc.setShutdownOptions(true, PinState.LOW);

	}

	public boolean setYellowLed(boolean state) {
		try {
			if (state)
				ledJaune.high();
			else
				ledJaune.low();
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public boolean set(String num) {
		System.out.println("Set Led to HEX:" + num);

		if (num.equals("-1")) {
			led0.low();
			led1.low();
			led2.low();
			led3.low();
		}

		else if (num.equals("0")) {
			led0.low();
			led1.low();
			led2.low();
			led3.low();
		}

		else if (num.equals("1")) {

			led0.high();
			led1.low();
			led2.low();
			led3.low();

		}

		else if (num.equals("2")) {
			led0.low();
			led1.high();
			led2.low();
			led3.low();

		}

		else if (num.equals("3")) {
			led0.high();
			led1.high();
			led2.low();
			led3.low();

		}

		else if (num.equals("4")) {
			led0.low();
			led1.low();
			led2.high();
			led3.low();
		}

		else if (num.equals("5")) {
			led0.high();
			led1.low();
			led2.high();
			led3.low();
		}

		else if (num.equals("6")) {
			led0.low();
			led1.high();
			led2.high();
			led3.low();
		}

		else if (num.equals("7")) {
			led0.high();
			led1.high();
			led2.high();
			led3.low();
		}

		else if (num.equals("8")) {
			led0.low();
			led1.low();
			led2.low();
			led3.high();
		}

		else if (num.equals("9")) {
			led0.high();
			led1.low();
			led2.low();
			led3.high();
		}

		else if (num.equals("10")) {
			led0.low();
			led1.high();
			led2.low();
			led3.high();
		}

		else if (num.equals("11")) {
			led0.high();
			led1.high();
			led2.low();
			led3.high();
		}

		else if (num.equals("12")) {
			led0.low();
			led1.low();
			led2.high();
			led3.high();
		}

		else if (num.equals("13")) {
			led0.high();
			led1.low();
			led2.high();
			led3.high();
		}

		else if (num.equals("14")) {
			led0.low();
			led1.high();
			led2.high();
			led3.high();
		}

		else if (num.equals("15")) {
			led0.high();
			led1.high();
			led2.high();
			led3.high();
		} else {
			return false;
		}
		return true;
	}

	public void shutdown() {
		gpio.shutdown();

	}

	public boolean buttonWasPressed() {
		if(buttonPressed) {
			buttonPressed = false;
			return true;
		}
		return false;
	}

}