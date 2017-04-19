package implementation;

import java.net.Socket;

public class FanDeviceMain {

	public static void main(String[] args) throws Exception{
		Socket socket = new Socket(SysKb.serverHost, SysKb.portNum);
		TcpObjectClient client = new TcpObjectClient (socket);
		Temperature temp = new Temperature(15.0);
		Termometer termo = new Termometer(temp);
		FanDevice fan = new FanDevice(client, termo);
		//termo.start();
	}
}
