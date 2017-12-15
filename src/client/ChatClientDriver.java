package client;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import server.ChatServerInterface;

public class ChatClientDriver {
	
	public static void main(String args[]) throws MalformedURLException, RemoteException, NotBoundException
	{
		
		Registry myreg = LocateRegistry.getRegistry("127.0.0.1" , 1099);
		ChatServerInterface chatServer = (ChatServerInterface)myreg.lookup("chatting");
		new Thread(new ChatClient(args[0] , chatServer)).start();
	
	
	}
	
}
