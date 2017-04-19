package implementation;

import java.util.Timer;
import java.util.TimerTask;
import interfaces.ITemperature;

public class Temperature implements ITemperature{
	
	private double temperature;
	private Timer timer;
	
	public Temperature(double temperature){
		this.temperature = temperature;
		timer = new Timer();
		increase();
	}

	public double getTemperature() {
		return this.temperature;
	}
	
	public void setTemperature( double temperature){
		this.temperature = temperature;
	}

	@Override
	public String dataRepresentation() {
		return String.valueOf(getTemperature());
	}
	
	public void increase() {
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				//System.out.println("working");
				temperature = temperature +1;
			}
		}, 0, 3000);

	}

}
