package implementation;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;

import interfaces.ITcpInteraction;

public class Client implements ITcpInteraction{

	private Socket socket;
	private DataOutputStream outputChannel;
	private BufferedReader inputChannel;
	protected boolean debug = false;
	
	public Client(Socket socket) {
 		this.socket = socket;
 		try {
			OutputStream outStream 	= socket.getOutputStream();
			InputStream inStream  	= socket.getInputStream();
			outputChannel 			= new DataOutputStream(outStream);
			inputChannel    		= new BufferedReader( new InputStreamReader( inStream ) );	
			/*if( System.getProperty("tcpLowTrace") != null ) 
				debug = System.getProperty("tcpLowTrace").equals("set") ;*/
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void sendALine( String msg ) throws Exception{
		System.out.println( "sendALine ... " + msg + " localPort=" + socket.getLocalPort() + " port=" + socket.getPort());
		outputChannel.writeBytes( msg+"\n" );	
		System.out.println( "has written ... " +msg    );
		outputChannel.flush();
	}
	
	@Override
	public String receiveALine() throws Exception{
		//System.out.println( "receiveALine trying ... " + Thread.currentThread().getName()  + " timeOut="+timeOut);
 		try {
			//socket.setSoTimeout(timeOut);
			String line = inputChannel.readLine();  //blocking =>
			System.out.println( "has read ... " +line  );
			return line;		
		} catch (SocketException e) {
			System.out.println( "timeOut" +  socket.getLocalPort());
			throw e;
		}
	}
	
	public Socket getSocket(){
		return socket;
	}
	
	@Override
	public void closeConnection() throws Exception{
		System.out.println(" CLOSING ");
		socket.close();
	}
	
	public void closeChannel(){
		try {
			inputChannel.close();
			outputChannel.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean isTwoWay() {
	 	return true;
	}

}
