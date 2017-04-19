package implementation;

import java.lang.*;
import java.io.*;
import java.net.*;
import java.util.HashMap;

public class Server extends Thread implements Serializable {

	private Socket client;
	private ServerSocket server;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	// private HashMap<String,String> value;
	private String line;

	public Server(ServerSocket server) {
		this.server = server;
		// value = new HashMap<String,String>();
		init();
	}

	private void init() {
		try {
			// ciclo infinito, in attesa di connessioni
			while (true) {
				// chiamata bloccante, in attesa di una nuova connessione
				System.out.println("connecting");
				client = server.accept();
				System.out.println("starting");
				ServerSupport support = new ServerSupport(client);
					

				// la nuova richiesta viene gestita da un thread indipendente,
				// si ripete il ciclo
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ObjectInputStream getInputStream() {
		return in;
	}

	public ObjectOutputStream getOutputStream() {
		return out;
	}
}
