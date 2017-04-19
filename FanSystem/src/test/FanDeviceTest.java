package test;

import static org.junit.Assert.*;

import org.junit.Test;

import implementation.*;
import interfaces.*;

public class FanDeviceTest {

	/*test dell'autoregolazione a temperatura 27 gradi, la velocità diventa high
	 * 
	 * con questo test vengono anche testati i sensori
	 */
	@Test
	public void testAutoIncreaseSpeed1() {
		ITemperature temperature = new Temperature(27);
		ITermometer termometer = new Termometer(temperature);
		IFanDevice fanDevice = new FanDevice(termometer);
		IFanSpeed fanSpeed = FanSpeed.HIGHSPEED;
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//i requisiti ci chiedono di rilevare i dati ambientali ogni 1,5 secondi
		assertEquals(fanSpeed, fanDevice.getSpeed());
	}
	
	/*test dell'autoregolazione a temperatura 30 gradi, il sistema si spegne
	 * 
	 */
	@Test
	public void testAutoIncreaseSpeed2() {
		ITemperature temperature = new Temperature(30);
		ITermometer termometer = new Termometer(temperature);
		IFanDevice fanDevice = new FanDevice(termometer);
		IFanSpeed fanSpeed = FanSpeed.OFF;
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//i requisiti ci chiedono di rilevare i dati ambientali ogni 1,5 secondi
		assertEquals(fanSpeed, fanDevice.getSpeed());
	}

}
