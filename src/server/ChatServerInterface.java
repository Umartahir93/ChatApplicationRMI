package server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import client.ChatClinetInterface;

public interface ChatServerInterface extends Remote {

	//what is java remote interface?
	void registerchatclient(ChatClinetInterface chatclient, String name) throws RemoteException;
	void messagebroadcast(String msg, String name) throws Exception;
	void removeclient(ChatClinetInterface chatclient) throws RemoteException;
	ArrayList<String> displaydata() throws Exception;
	ArrayList<String> displayusers() throws Exception;
}

