package interfaces;

public enum FanSpeed implements IFanSpeed {
	OFF (0.0),
	LOWSPEED (30.0),
	MEDIUMSPEED (60.0), 
	HIGHSPEED (90.0);
	
	private double speed;
	
	FanSpeed (double speed) {
		this.speed = speed;
	}

	public double getSpeed() {
		return this.speed;
	}
	
	public void setSpeed(double speed){
		this.speed = speed;
	}

	@Override
	public String dataRepresentation() {
		return String.valueOf(getSpeed());
	}
	
}