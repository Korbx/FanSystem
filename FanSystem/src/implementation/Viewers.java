package implementation;

import java.io.IOException;
import java.io.Serializable;
import java.net.*;
import java.util.*;

import javax.swing.*;

import interfaces.ISensor;
import interfaces.ITcpInteraction;
import interfaces.IViewers;

public class Viewers implements IViewers, Serializable {
	
	/* il Viewers deve avere la propria socket con un "while" che riceve di continuo i messaggi
	 * 
	 */

	private Termometer termometer;
	private Tachometer tachometer;
	private Sensor sensor;
	private JLabel fanSpeedLabel, temperatureLabel;
	protected JTextField fanSpeedText, temperatureText;
	private ViewerDisplay display;
	private HashMap<String, String> hashMap, ack;
	private boolean active = true;
	private String fanSpeed;
	private TcpObjectClient client;

	public Viewers(ITcpInteraction client, ViewerDisplay display){
		//this.sensor = sensor;
		//this.receiver = receiver;
		//this.sensor.attach(this);
		this.client = (TcpObjectClient) client;
		this.display = display;
		configure();
		
	}
	
	/* inizializza la GUI
	 * 
	 */
	public void configure(){
		//InetAddress addr = InetAddress.getByName(SysKb.wifiHost);
		//Socket socket = new Socket(addr, SysKb.portNum);
		//client = new TcpObjectClient(socket);
		//client = new TcpObjectClient(new Socket(SysKb.serverHost, SysKb.portNum));
		hashMap = new HashMap<String, String>();
		hashMap.put("Viewers", "0");
		
		fanSpeedLabel = new JLabel("FanSpeed:");
		fanSpeedText = new JTextField();
		display.addJLabel(fanSpeedLabel, 0, 0);
		display.addJTextField(fanSpeedText, 1, 0);
		
		temperatureLabel = new JLabel("Temperature:");
		temperatureText = new JTextField();
		display.addJLabel(temperatureLabel, 0, 1);
		display.addJTextField(temperatureText, 1, 1);
	}

	
	/* riceve aggiornamenti del sistema dalle altre entità
	 * (non-Javadoc)
	 * @see interfaces.IViewers#update()
	 */
	@Override
	public void update() {
		try {
			client.sendObject(hashMap);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		while (active) {
			try {
				//client.sendObject(hashMap);
				hashMap = (HashMap<String, String>) client.receiveAnObject();
				System.out.println("received" + hashMap.entrySet());
				//this.hashMap.putAll(value);
				updateValue();
				hashMap.put("Viewers", "0");
				client.sendObject(hashMap);
				System.out.println("send" + hashMap.entrySet());
				//client.closeChannel();
				//client.closeConnection();
				//setTextField(speed);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/* aggiorna la GUI
	 * 
	 */
	public void updateValue(){
		if (hashMap.containsKey("FanSpeed")) {
			fanSpeedText.setText(hashMap.get("FanSpeed"));
		}
		if (hashMap.containsKey("Temperature")) {
			temperatureText.setText(hashMap.get("Temperature"));
		}
	}


	public JTextField getFanSpeedText(){
		return fanSpeedText;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

}
