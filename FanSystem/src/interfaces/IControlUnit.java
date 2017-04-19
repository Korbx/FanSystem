package interfaces;

public interface IControlUnit {
	
	IFanSpeed getFanSpeed();
	void setFanSpeed(IFanSpeed fanSpeed);	
	void start();
	void stop();
	void incSpeed();
	void decSpeed();

}
