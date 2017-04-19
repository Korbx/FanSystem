package interfaces;

import java.util.Observer;

public interface IObserver extends Observer {
	
	public abstract void update(java.util.Observable arg0, java.lang.Object arg1);
}