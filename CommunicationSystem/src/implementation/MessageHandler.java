package implementation;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.*;

public class MessageHandler {

	private HashMap<String, String> hashMap;
	private Thread thread;
	private boolean bool;
	private static Object monitor = new Object();

	public MessageHandler(HashMap<String, String> hashMap, Thread thread) {
		this.hashMap = hashMap;
		this.thread = thread;
		init();
	}

	public void init() {
		if ((hashMap.containsKey("Viewers"))) {
			/*synchronized (monitor) {
				try {
					System.out.println("waiting" + thread.getId());
					while(!ViewersThreadList.getReady()){
					monitor.wait();
					System.out.println("go" + thread.getId());
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}*/
			return;
		} else {
			// SensorValueHashmap.putValue(hashMap);
			for (int i = 0; i < ClientStreamList.getOutputStreamList().size(); i++) {
				try {
					ObjectOutputStream out = ClientStreamList.getOutputStream(i);
					out.writeObject(hashMap);
					System.out.println("out:" + hashMap.entrySet() + thread.getId());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("output" + i + ClientStreamList.getOutputStream(i));
			}
			/*ViewersThreadList.setTrue();
			synchronized (monitor) {
				//monitor.notifyAll();
				System.out.println("notify" + thread.getId());
			}
			ViewersThreadList.setFalse();*/
		}
	}

}
