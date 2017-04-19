package test;

import static org.junit.Assert.*;

import org.junit.Test;

import implementation.DevButton;
import interfaces.IDevButton;

public class ButtonTest {
	
	
	/* stato iniziale del bottone
	 * 
	 */
	@Test
	public void testIsPressed() throws Exception {
		IDevButton button = new DevButton();
		assertEquals(false, button.isPressed());
	}

}
