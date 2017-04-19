package implementation;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;


public class TcpConnection {
	
	private static HashMap<String,ServerSocket> activePorts = 
			new HashMap<String,ServerSocket>();
	
	public TcpConnection() {

	}

	
	/**
	 * To be used by a receiver to start a connection.
	 */
	public ServerSocket connectAsReceiver(int portNum) throws Exception{
		System.out.println( "connectAsReceiver on port " + portNum );
		if( activePorts.get(""+portNum) != null ){ //port already active
			System.out.println(" +++ INPUT PORT " + portNum +" ALREADY ACTIVE");
			return activePorts.get(""+portNum);
		}
		ServerSocket serverSocket = new ServerSocket( portNum );	
		activePorts.put(""+portNum, serverSocket);
		return serverSocket;
	}
	
	public Socket acceptAConnection(ServerSocket serverSocket) throws Exception {
		int timeOut = 20000;
		if( System.getProperty("inputTimeOut") != null )
			timeOut = Integer.parseInt(
					System.getProperty("inputTimeOut"));
		serverSocket.setSoTimeout(timeOut); //wait for timeOut sec
		Socket socket = serverSocket.accept();
		System.out.println( " *** has accepted a connection ... " + socket.getRemoteSocketAddress());
		return  socket;
	}
	
	public Socket connectAsClient(String hostName, int port) throws Exception{
//		println( "connecting as client to " + hostName + " " + port);
		try{
			Socket socket = new Socket( hostName, port );	//bloccante
//			println( "connected a client to " + hostName );
			return  socket;
		}catch(Exception e){
//			println( "ERROR " + e.getMessage() );
			throw e;
		}
	}
		
 	
	public  void closeConnection(ServerSocket serverSocket) throws Exception{
		serverSocket.close();
	}

}
