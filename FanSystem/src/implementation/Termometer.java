package implementation;

import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JTextField;

import interfaces.IData;
import interfaces.ITemperature;
import interfaces.ITermometer;

public class Termometer extends Sensor implements ITermometer {

	private Temperature temperature;
	private Timer timer;
	//private Timer timerTask;

	public Termometer(ITemperature temperature) {
		this.temperature = (Temperature) temperature;
		timer = new Timer();
		//textField = new JTextField("gafgfd");
		//display.addJTextField(textField);
		start();
		// putHashTable(getName(), getTemperature());
	}

	/* il termometro viene creato e ogni 1,5 secondi viene rilevata
	 * la temperatura, e viene chiamato il metodo del sensore (Sensor)
	 * che notifica gli osservatori degli update
	 * 
	 */
	public void start() {
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				//System.out.println("working");
				putHashTable(getName(), getRepresentation());
			}
		}, 0, 1500);

	}

	@Override
	public IData getData() {
		return temperature;
	}

	public double getTemperature() {
		return temperature.getTemperature();
	}

	public String getRepresentation() {
		return temperature.dataRepresentation();
	}
}
