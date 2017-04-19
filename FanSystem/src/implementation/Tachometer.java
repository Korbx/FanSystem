package implementation;

import java.awt.event.ActionListener;
import java.util.Observable;

import java.util.Timer;
import java.util.TimerTask;

import interfaces.FanSpeed;
import interfaces.IData;
import interfaces.ITachometer;

public class Tachometer extends Sensor implements ITachometer {

	private FanSpeed fanSpeed;
	private Timer timer;

	public Tachometer(FanSpeed fanSpeed) {
		this.fanSpeed = fanSpeed;
		timer = new Timer();
		//start();
	}

	private void start() {
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				putHashTable(getName(), getRepresentation());
			}
		}, 0, 1500);

	}

	@Override
	public IData getData() {
		return fanSpeed;
	}

	public double getSpeed() {
		return fanSpeed.getSpeed();
	}

	public String getRepresentation() {
		return fanSpeed.dataRepresentation();
	}
}
