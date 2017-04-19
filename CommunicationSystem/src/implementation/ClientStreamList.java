package implementation;

import java.io.*;
import java.net.*;
import java.util.*;

public class ClientStreamList {
	
	private static ArrayList<ObjectOutputStream> outputStreamList = new ArrayList<ObjectOutputStream>();
	
	public static void addOutputStream(ObjectOutputStream stream){
		outputStreamList.add(stream);
	}
	
	public static ObjectOutputStream getOutputStream(int index){
		ObjectOutputStream stream = outputStreamList.get(index);
		return stream;
	}
	
	public static ArrayList<ObjectOutputStream> getOutputStreamList(){
		return outputStreamList;
	}

}
