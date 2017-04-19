package implementation;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.HashMap;

public class ServerSupport extends Thread {

	private Socket socket;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private TcpObjectClient client;
	private HashMap<String, String> value;

	public ServerSupport(Socket socket) {
		this.socket = socket;
		value = new HashMap<String, String>();
		//client = new TcpObjectClient(this.socket);
		// invoca automaticamente il metodo run()
		this.start();
	}

	public void run() {
		try {
			in = new ObjectInputStream(socket.getInputStream());
			out = new ObjectOutputStream(socket.getOutputStream());
			ClientStreamList.addOutputStream(getOutputStream());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		while (!socket.isClosed()) {
			// inizializza i buffer in entrata e uscita
			try {

				System.out.println("Sto servendo il client che ha indirizzo " + this.getId());

				// eventuali elaborazioni e scambi di
				// informazioni
				// con il client
				//HashMap<String, String> value = new HashMap<String, String>();
				//value = (HashMap<String, String>) in.readObject();
				readObject();

				System.out.println("value:" + value.entrySet() + this.getId());

				MessageHandler handler = new MessageHandler(value, this);
				System.out.println("afterHandler:" + this.getId());
				// value.putAll(SensorValueHashmap.getHashMap());
				// System.out.println("afterMap: " +
				// SensorValueHashmap.getHashMap().entrySet());

				/*
				 * for (int i = 0; i <
				 * ClientStreamList.getOutputStreamList().size(); i++) {
				 * ObjectOutputStream out = ClientStreamList.getOutputStream(i);
				 * out.writeObject(value); System.out.println("output" + i +
				 * ClientStreamList.getOutputStream(i)); }
				 */

				// out.writeObject(value);

				// chiusura dei buffer e del socket
				// in.close();
				// out.close();
				// client.close();
				// System.out.println("closing");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void readObject() throws Exception {
		try {
			value = (HashMap<String, String>) in.readObject();
		} catch (SocketException e) {
			System.out.println("timeOut" + socket.getLocalPort());
			in.close();
			out.close();
			socket.close();
			System.out.println("socket closed");
			throw e;
		}
	}

	public ObjectInputStream getInputStream() {
		return in;
	}

	public ObjectOutputStream getOutputStream() {
		return out;
	}
}