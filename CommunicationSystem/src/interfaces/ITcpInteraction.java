package interfaces;

public interface ITcpInteraction {
	
	public void sendALine(  String msg ) throws Exception;
	public String receiveALine(  ) throws Exception;
	public void closeConnection( ) throws Exception;
	public boolean isTwoWay();

}
