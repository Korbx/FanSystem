package implementation;

import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Observable;

import javax.swing.Timer;

import interfaces.FanSpeed;
import interfaces.IFanDevice;
import interfaces.IFanSpeed;
import interfaces.ITcpInteraction;
import interfaces.ITemperature;
import interfaces.ITermometer;

public class FanDevice implements IFanDevice, Serializable {

	private ITemperature temperature;
	private ControlUnit controlUnit;
	private Termometer termometer;
	private String value;
	private double temp;
	private double speed;
	private HashMap <String, String> hashMap;
	private TcpObjectClient client;
	private FanSpeed fanSpeed;

	/* costruttore per il FanDevice distribuito
	 * 
	 */
	public FanDevice(ITcpInteraction client, ITermometer termometer) {
		this.client = (TcpObjectClient) client;
		this.termometer = (Termometer) termometer;
		this.termometer.attach(this);
		hashMap = new HashMap <String, String>();
	}
	
	/* costruttore per il FanDevice in locale, utile per i test
	 * 
	 */
	public FanDevice (ITermometer termometer){
		this.termometer = (Termometer) termometer;
		this.termometer.attach(this);
		hashMap = new HashMap <String, String>();
	}
	
	public ControlUnit getControlUnit(){
		return controlUnit;
	}
	
	public void setControlUnit(ControlUnit controlUnit){
		this.controlUnit = controlUnit;
	}

	@Override
	public IFanSpeed getSpeed() {
		// TODO Auto-generated method stub
		return fanSpeed;
	}

	@Override
	public ITemperature getTemperature() {
		// TODO Auto-generated method stub
		return temperature;
	}

	public void setTemperature(ITemperature temperature) {
		this.temperature = temperature;
	}
	
	@Override
	public void setFanSpeed(IFanSpeed fanSpeed) {
		this.fanSpeed = (FanSpeed) fanSpeed;
		
	}

	/* autoregolazione in base alla temperatura ricevuta da Termometer
	 * 
	 */
	public void setSpeed(double temperature) {
		System.out.println("Speed: " + speed);
		if (temperature >= 30.0) {
			setFanSpeed(FanSpeed.OFF);
			hashMap.put("FanSpeed", getSpeedString());
			updateSpeed();
		} else if (temperature >= 27.0) {
			setFanSpeed(FanSpeed.HIGHSPEED);
			hashMap.put("FanSpeed", getSpeedString());
			updateSpeed();
		}
		//send();
	}
	
	/* aggiorna la velocità delle pale una volta ricevuto il messaggio
	 * 
	 */
	public void updateSpeed(){
		if((hashMap.get("FanSpeed"))!= null){
			speed = Double.parseDouble(hashMap.get("FanSpeed"));
		}
	}
	
	/* indica la nuova velocità alla ControlUnit, quando viene superata una certa
	 * temperatura
	 */
	public void sendTemperature(String value){
		controlUnit.putHashMap("Temperature", value);
		controlUnit.send();
	}
	
	/* quando viene notificato dall'observable (Sensor), il FanDevice
	 * chiamata setSpeed tramite update, e fa un controllo della 
	 * velocità
	 * 
	 * (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable o, Object arg) {
		value = termometer.getHashTable(arg);
		temp = Double.parseDouble(value);
		hashMap.put("Temperature", value);
		setSpeed(temp);
		send();
		//sendTemperature(value);
	}
	
	/* rimuove i messaggi non inerenti al FanDevice
	 * 
	 */
	public void remove(){
		hashMap.entrySet().removeIf(entries->!entries.getKey().equals("Temperature"));
	}
	
	public String getSpeedString() {
		return fanSpeed.dataRepresentation();
	}
	
	/* manda e riceve messaggi
	 * 
	 */
	public void send() {
		try {
			client.sendObject(hashMap);
			System.out.println("send:" + hashMap.entrySet());
			hashMap = (HashMap<String, String>) client.receiveAnObject();
			System.out.println("ack:" + hashMap.entrySet());
			//removeViewersMessage();
			updateSpeed();
			remove();
			System.out.println("afterRemove:" + hashMap.entrySet());
			// client.closeChannel();
			// client.closeConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
