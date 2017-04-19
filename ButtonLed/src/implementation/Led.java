package implementation;


import java.awt.Color;
import interfaces.ILed;


/*
 * A concrete LED does not provide the switch operation
 * It is matter of a controller
 */
public class Led implements ILed{
protected Color color;
protected boolean on;

	public Led( Color color ){
		this.color = color;
		on = false;
	}
	
	@Override
	public void turnOn() {
		on = true;	
		show("Led ON");
	}

	@Override
	public void turnOff() {
		on = false;	
		show("Led OFF");
	}

	public Color getColor() {
 		return color;
	}

	@Override
	public boolean isOn() {
 		return on;
	}

	public void doSwitch() {
		if( isOn() ) turnOff();
		else turnOn();		
	}

	protected void show(String msg){		
		String colorOut = "";
		if( color == Color.green ) colorOut = "green";
		else if( color == Color.red ) colorOut = "red";
		System.out.println( "%%% " + msg + " " + colorOut + " %%% ");
	}
}