package implementation;

import java.io.IOException;
import java.net.*;

public class ServerMain {
	
	public static void main(String[] args){
		try {
			//InetAddress addr = InetAddress.getByName(SysKb.wifiHost);
			//System.out.println("ip: " + addr.getHostAddress());
			//System.out.println("ragg: " + addr.isReachable(10000));
			//Server3 server = new Server3(new ServerSocket(SysKb.portNum, 0 , addr));
			Server server = new Server(new ServerSocket(SysKb.portNum));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
