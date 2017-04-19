package interfaces;

import java.util.Observer;

public interface IFanDevice extends Observer{
	
	IFanSpeed getSpeed();
	ITemperature getTemperature();
	void setFanSpeed(IFanSpeed fanSpeed);
}
