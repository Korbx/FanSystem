package test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import implementation.ControlUnit;
import interfaces.FanSpeed;
import interfaces.IControlUnit;
import interfaces.IFanSpeed;

public class ControlUnitTest {
	
	
	/* test del metodo start(), per verificare che non funzioni prima di aver selezionato la
	 * velocit�, attraverso side effect, essendo il valore di ritorno void
	 * 
	 * i test seguenti mostrano anche il funzionamento dei metodi setSpeed() e getSpeed()
	 */
	@Test
	public void testStartSpeedNotSelected(){
		IFanSpeed fanSpeed = FanSpeed.LOWSPEED;
		
		IControlUnit controlUnit = new ControlUnit();
		controlUnit.start();
		controlUnit.setFanSpeed(FanSpeed.LOWSPEED);
		controlUnit.incSpeed();
		// la velocit� non incrementa poich� si � usato start() prima setFanSpeed()
		assertEquals(fanSpeed, controlUnit.getFanSpeed());
	}
	
	/* test del metodo inc(), � possibile notare anche il funzionamento del metodo
	 * start() come side effect di questo test
	 * 
	 */
	@Test
	public void testIncSpeed() {
		IFanSpeed fanSpeed = FanSpeed.MEDIUMSPEED;
		
		IControlUnit controlUnit = new ControlUnit();
		controlUnit.setFanSpeed(FanSpeed.LOWSPEED);
		controlUnit.start();
		controlUnit.incSpeed();
		
		assertEquals(fanSpeed, controlUnit.getFanSpeed());
	}
	
	/*test del metodo decSpeed(), molto simile al test sopra
	 * 
	 */
	@Test
	public void testDecSpeed() {
		IFanSpeed fanSpeed = FanSpeed.LOWSPEED;
		
		IControlUnit controlUnit = new ControlUnit();
		controlUnit.setFanSpeed(FanSpeed.MEDIUMSPEED);
		controlUnit.start();
		controlUnit.decSpeed();
		
		assertEquals(fanSpeed, controlUnit.getFanSpeed());
	}
	
	/* test del metodo off(), il valore di ritorno aspettato � OFF
	 * 
	 */
	@Test
	public void testStop() {
		IFanSpeed fanSpeed = FanSpeed.OFF;
		
		IControlUnit controlUnit = new ControlUnit();
		controlUnit.setFanSpeed(FanSpeed.LOWSPEED);
		controlUnit.start();
		controlUnit.incSpeed();
		controlUnit.stop();
		controlUnit.incSpeed();
		//il secondo incSpeed() non sortisce alcun effetto perch� il sistema � spento
		assertEquals(fanSpeed, controlUnit.getFanSpeed());
	}


}
