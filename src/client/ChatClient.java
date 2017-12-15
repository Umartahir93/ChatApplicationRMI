package client;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

import server.ChatServerInterface;

public class ChatClient extends UnicastRemoteObject implements ChatClinetInterface, Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String name = null;
	ChatServerInterface chatServer;
	ArrayList<String> savemessages = new ArrayList<String>();
	protected ChatClient(String name , ChatServerInterface chatServer)
			throws RemoteException {
	this.name = name;
	this.chatServer = chatServer;
	chatServer.registerchatclient(this, name);
	}

	
	@Override
	public void receiveMessage(String msg) throws RemoteException {
		System.out.println(msg);
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		String msg =  null;
		while(true)
		{
			System.out.println("Type message:  ");
			msg = scan.nextLine();
			try {
				if(Pattern.compile(Pattern.quote("Disconnect"),
						Pattern.CASE_INSENSITIVE).matcher(msg).find()){
							chatServer.removeclient(this);
							System.out.println("Disconnected");
							break;}
				chatServer.messagebroadcast(name+": "+msg, name);
				} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				}
		}
	}
	
	

	
	

}
