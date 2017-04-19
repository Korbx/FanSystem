package implementation;

import java.io.Serializable;
import java.util.HashMap;

public class SensorValueHashmap implements Serializable {

	private static HashMap<String, String> sensorValue = new HashMap<String, String>();

	public static void putValue(HashMap<String, String> value) {
		sensorValue.putAll(value);
	}

	public static HashMap<String, String> getHashMap() {
		return sensorValue;
	}
}
