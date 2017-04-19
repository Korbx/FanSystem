package implementation;


import java.io.*;
import java.net.*;

import interfaces.ITcpInteraction;

public class TcpObjectClient implements Serializable, ITcpInteraction{
	
	private Socket socket;
	private ObjectOutputStream outputChannel;
	private ObjectInputStream inputChannel;
	protected boolean debug = false;
	private OutputStream outStream;
	private InputStream inStream;
	
	public TcpObjectClient(Socket socket) {
 		this.socket = socket;
 		try {
			outStream 	= socket.getOutputStream();
			inStream  	= socket.getInputStream();
			outputChannel 			= new ObjectOutputStream(outStream);
			inputChannel    		= new ObjectInputStream( inStream );	
			/*if( System.getProperty("tcpLowTrace") != null ) 
				debug = System.getProperty("tcpLowTrace").equals("set") ;*/
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void sendALine(String msg) throws Exception {
		System.out.println( "sendALine ... " + msg + " localPort=" + socket.getLocalPort() + " port=" + socket.getPort());
		outputChannel.writeObject( msg+"\n" );	
		System.out.println( "has written ... " +msg    );
		outputChannel.flush();
		
	}
	
	public void sendObject( Object msg ) throws Exception{
		try {
			//System.out.println( "sendALine ... " + msg + " localPort=" + socket.getLocalPort() + " port=" + socket.getPort());
			outputChannel.writeObject( msg );
			//System.out.println( "has written ... " +msg    );
			outputChannel.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}	
		
	}
	
	@Override
	public String receiveALine() throws Exception{
		//System.out.println( "receiveALine trying ... " + Thread.currentThread().getName()  + " timeOut="+timeOut);
 		try {
			//socket.setSoTimeout(timeOut);
			String line = (String) inputChannel.readObject();  //blocking =>
			//System.out.println( "has read ... " +line  );
			return line;		
		} catch (SocketException e) {
			System.out.println( "timeOut" +  socket.getLocalPort());
			inputChannel.close();
			outputChannel.close();
			socket.close();
			System.out.println("socket closed");
			throw e;
		}
	}
	
	public Object receiveAnObject() throws Exception{
		//System.out.println( "receiveALine trying ... " + Thread.currentThread().getName()  + " timeOut="+timeOut);
 		try {
			//socket.setSoTimeout(timeOut);
			Object object = inputChannel.readObject();  //blocking =>
			//System.out.println( "has read ... " +object  );
			return object;		
		} catch (SocketException e) {
			System.out.println( "timeOut" +  socket.getLocalPort());
			inputChannel.close();
			outputChannel.close();
			socket.close();
			System.out.println("socket closed");
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
	
	public void newBuffer(){
		try {
			outputChannel 			= new ObjectOutputStream(outStream);
			inputChannel    		= new ObjectInputStream( inStream );
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
