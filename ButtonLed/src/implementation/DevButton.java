package implementation;

import java.util.Observable;

import interfaces.IDevButton;
import interfaces.IObserver;

/*
 * The reference to implementation is given by the the Observable nature of the object
 */
public class DevButton extends Observable implements IDevButton{ //IObserver and IObservable   
//protected IBasicEnvAwt env;
protected String state = "false";
protected boolean isPressed = false;

/*public DevButton( IBasicEnvAwt env ){ 
	this.env = env;
}*/
 
	@Override
	public int getInput() throws Exception {
 		if( state.equals(  "true" ) ) return 1;
		else return 0;
	}
	@Override
	public void register(IObserver observer) {
		//if(env!=null) env.println(" *** DevButton register " + observer);
		this.addObserver(observer);	 
	}
	@Override
	public boolean isPressed() throws Exception {
//		System.out.println(" *** DevButton isPressed " + state );
 		return state.equals(  "true" );
	}
	@Override
	public synchronized void update(Observable arg0, Object arg1) {
		//if(env!=null) env.println(" *** DevButton " + arg1 + " from " + arg0);
		state = ""+arg1;
		this.setChanged();	//!!!!
		this.notifyObservers(state);
 	}

}
