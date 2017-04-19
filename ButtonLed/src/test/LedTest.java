package test;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Test;

import implementation.Led;
import interfaces.ILed;

public class LedTest {

	/* test turnOn()
	 * 
	 */
	@Test
	public void testOn() {
		Color color = new Color (100, 100, 100);
		ILed led = new Led(color);
		led.turnOn();
		assertEquals(true, led.isOn());
	}
	
	/* test turnOff()
	 * 
	 */
	@Test
	public void testOff() {
		Color color = new Color (100, 100, 100);
		ILed led = new Led(color);
		led.turnOn();
		led.turnOff();
		assertEquals(false, led.isOn());
	}

}
