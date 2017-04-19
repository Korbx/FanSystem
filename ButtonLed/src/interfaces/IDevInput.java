package interfaces;

public interface IDevInput extends IObserver, IObservable{
	
	public int getInput() throws Exception; 
}
