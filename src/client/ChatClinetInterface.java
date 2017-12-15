package client;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatClinetInterface extends Remote {
	void receiveMessage(String msg) throws RemoteException;
}
