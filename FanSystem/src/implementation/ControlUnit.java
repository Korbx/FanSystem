package implementation;

import java.io.IOException;
import java.io.Serializable;
import java.net.*;
import java.util.*;
import javax.swing.*;

import interfaces.FanSpeed;
import interfaces.IControlUnit;
import interfaces.IFanSpeed;
import interfaces.ITcpInteraction;


public class ControlUnit extends Observable implements IControlUnit, Serializable {

	private FanSpeed fanSpeed;
	private FanDevice fanDevice;
	private double currentSpeed;
	private TcpObjectClient client;
	private HashMap<String, String> hashMap, message;
	private boolean started = false;
	private boolean speedSelected = false;


	/* costruttore per la ControlUnit distribuita
	 * 
	 */
	public ControlUnit(ITcpInteraction client) {
		// this.fanDevice = fanDevice;
		// this.fanDevice.setControlUnit(this);
		this.client = (TcpObjectClient) client;
		hashMap = new HashMap<String, String>();
		message = new HashMap<String, String>();
	}
	
	
	/* costruttore per la ControlUnit in locale, utile per i test
	 * 
	 */
	public ControlUnit(){
		hashMap = new HashMap<String, String>();
		message = new HashMap<String, String>();
	}

	/* mette started a true se setSpeed() è stato chiamato, permettendo di inviare 
	 * comandi
	 * 
	 */
	@Override
	public void start() {
		if (started) {
			JOptionPane.showMessageDialog(null, "Already started!");
			System.out.println("Already started!");
		} else if (!speedSelected) {
			JOptionPane.showMessageDialog(null, "Select Speed!");
			System.out.println("Select Speed!");
		} else if (speedSelected) {
			started = true;
			JOptionPane.showMessageDialog(null, "Started!");
			System.out.println("Started!");
			send();
		}
	}
	
	
	/* spegne il sistema, la velocità viene messa a 0 e setSpeed() e start() devono
	 * essere richiamati per riattivarlo
	 */
	@Override
	public void stop() {
		if (started) {
			setFanSpeed(FanSpeed.OFF);
			System.out.println("speed:" + getSpeed());
			putHashMap("FanSpeed", getSpeedString());
			send();
			started = false;
			speedSelected = false;
		}
	}
	
	/* aumenta la velocità se il sistema è acceso
	 * (non-Javadoc)
	 * @see interfaces.IControlUnit#incSpeed()
	 */
	@Override
	public void incSpeed() {
		if (started) {
			switch (fanSpeed) {
			case LOWSPEED:
				fanSpeed = FanSpeed.valueOf("MEDIUMSPEED");
				break;

			case MEDIUMSPEED:
				fanSpeed = FanSpeed.valueOf("HIGHSPEED");
				break;

			case HIGHSPEED:
				break;

			case OFF:
				break;
			}
			System.out.println("speed:" + getSpeed());
			putHashMap("FanSpeed", getSpeedString());
			send();
		}
	}

	/* diminuisce la velocità se il sistema è acceso
	 * (non-Javadoc)
	 * @see interfaces.IControlUnit#decSpeed()
	 */
	@Override
	public void decSpeed() {
		if (started) {
			switch (fanSpeed) {
			case LOWSPEED:
				break;

			case MEDIUMSPEED:
				fanSpeed = FanSpeed.valueOf("LOWSPEED");
				break;

			case HIGHSPEED:
				fanSpeed = FanSpeed.valueOf("MEDIUMSPEED");
				break;

			case OFF:
				break;
			}
			System.out.println("speed:" + getSpeed());
			putHashMap("FanSpeed", getSpeedString());
			send();
		}
	}

	@Override
	public IFanSpeed getFanSpeed() {
		return fanSpeed;
	}

	/* seleziona la velocità, deve essere invocato prima di start()
	 * (non-Javadoc)
	 * @see interfaces.IControlUnit#setFanSpeed(interfaces.IFanSpeed)
	 */
	@Override
	public void setFanSpeed(IFanSpeed fanSpeed) {
		this.fanSpeed = (FanSpeed) fanSpeed;
		speedSelected = true;
		putHashMap("FanSpeed", getSpeedString());
		System.out.println("speed:" + getSpeed());
		if (started) {
			send();
		}
	}

	public String getName() {
		// TODO Auto-generated method stub
		return getClass().getSimpleName();
	}

	/* inserisce i comandi della velocità in una Hashmap, indicando tipo di valore e 
	 * valore
	 */
	public void putHashMap(String key, String value) {
		hashMap.put(key, value);
	}

	public HashMap<String, String> getHashMap() {
		return this.hashMap;
	}

	public void setHashMap(HashMap<String, String> hashMap) {
		this.hashMap = hashMap;
	}

	public double getSpeed() {
		return fanSpeed.getSpeed();
	}

	public String getSpeedString() {
		return fanSpeed.dataRepresentation();
	}

	public void setCurrentSpeed() {
		currentSpeed = fanSpeed.getSpeed();
	}
	
	/*spegne il sistema quando il FanDevice supera i 30 gradi, mandando il messaggio "off"
	 * 
	 */
	private void updateSpeed() {
		if((hashMap.get("FanSpeed"))== "0.0"){
			stop();
		}
		
	}
	
	/* rimuove i messaggi non inerenti alla ControlUnit
	 * 
	 */
	public void remove() {
		hashMap.entrySet().removeIf(entries -> !entries.getKey().equals("FanSpeed"));
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
			// removeViewersMessage();
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
