package implementation;

import java.net.*;

public class ViewersMain {
	
	public static void main(String[] args) throws Exception{
		ViewerDisplay display = new ViewerDisplay();
		Socket socket = new Socket(SysKb.serverHost, SysKb.portNum);
		TcpObjectClient client = new TcpObjectClient (socket);
		//Client client = new Client(socket);
		//Receiver receiver = new Receiver(new ServerSocket(SysKb.portNum));
		Viewers view = new Viewers(client, display);
		view.update();
	}

}
