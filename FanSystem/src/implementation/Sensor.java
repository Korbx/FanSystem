package implementation;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

import interfaces.IData;
import interfaces.ISensor;

public class Sensor extends Observable implements ISensor {

	private List<Observer> observers;
	private int state;
	private Viewers viewers;
	private Hashtable<String, String> hashTable;
	private String value;
	protected JTextField textField;
	protected ViewerDisplay display;

	public Sensor() {
		observers = new ArrayList<Observer>();
		hashTable = new Hashtable<String, String>();
		//this.display = display;
		//textField = new JTextField("gafgfd");
		//display.addJTextField(textField);
	}

	@Override
	public IData getData() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * public int getState() { return state; }
	 * 
	 * public void setState(int state) { this.state = state;
	 * notifyAllObservers(); }
	 */

	public void attach(Observer observer) {
		observers.add(observer);
	}
	
	/* notifica tutti gli osservatori attaccati alla lista
	 * quindi chiama il metodo update() nell'osservatore
	 */
	public void notifyAllObservers() {
		for (Observer observer : observers) {
			observer.update(this, getName());
		}
	}

	public String getName() {
		// TODO Auto-generated method stub
		return getClass().getSimpleName();
	}

	
	/* ogni 1,5 secondi il valore del sensore viene aggiornato
	 * e gli osservatori (Viewers e FanDevice) vengono notificati
	 * 
	 */
	public void putHashTable(String key, String value) {
		hashTable.put(key, value);
		notifyAllObservers(); 
	}
	
	/* metodo finale di aggiornamento dei Viewers e FanDevice, 
	 * ritorna il valore del sensore e lo stampa su schermo
	 * 
	 */
	public String getHashTable(Object key) {
		value = hashTable.get(key);
		//viewers.getTextField().setText(hashTable.get(key));
		System.out.println(""+hashTable.get(key));
		return value;
	}
	
	public String getValue(){
		return value;
	}

}
