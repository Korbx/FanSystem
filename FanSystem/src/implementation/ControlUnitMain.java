package implementation;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ControlUnitMain {

	public static void main(String[] args) throws Exception{
		//Temperature temp = new Temperature(15.0);
		//Termometer termo = new Termometer(temp);
		//FanDevice fan = new FanDevice(termo);
		Socket socket = new Socket(SysKb.serverHost, SysKb.portNum);
		System.out.println("address: " + socket.getInetAddress());
		TcpObjectClient client = new TcpObjectClient (socket);
		ControlUnit control = new ControlUnit(client);
		ControlDisplay controlDisplay = new ControlDisplay(control);
		//termo.start();
	}

}
