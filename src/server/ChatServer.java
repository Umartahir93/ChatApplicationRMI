package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Pattern;

import client.ChatClinetInterface;

public class ChatServer extends UnicastRemoteObject implements ChatServerInterface{
	private static final long serialVersionUID = 1L;
	public ArrayList<ChatClinetInterface> chatClients;
	public static ArrayList<String> display_users = 
			new ArrayList<String>();
	int check = 0;

	protected ChatServer() throws RemoteException {
		chatClients = new ArrayList<ChatClinetInterface>();
}
	@Override
	public void registerchatclient(ChatClinetInterface chatclient, String name) throws RemoteException {
		// TODO Auto-generated method stub
		this.chatClients.add(chatclient);
		display_users.add(name);
		}
	
	
	
	@Override
	public synchronized void messagebroadcast(String msg, String name)
			throws Exception {
		int i  = 0;
		while(i < chatClients.size())
		{
			chatClients.get(i).receiveMessage(msg);
			if(i == 0)
			{
			
			String DB_URL  = new String("jdbc:mysql://localhost:3306/client_admin"); 
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(DB_URL,"root","");
			String query = "INSERT INTO username (Username, Message) VALUES (?, ?)";
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.setString(1, name);
		    preparedStmt.setString(2, msg);
		    preparedStmt.executeUpdate();
		    conn.close();
			
			}
			i++;
		}
	}
	@Override
	public void removeclient(ChatClinetInterface chatclient) throws RemoteException {
		// TODO Auto-generated method stub
		this.chatClients.remove(chatclient);
	}
	@Override
	public ArrayList<String> displaydata() throws Exception {
		// TODO Auto-generated method stub
		ArrayList <String> messages =  new ArrayList<String>();
		String DB_URL  = new String("jdbc:mysql://localhost:3306/client_admin"); 
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(DB_URL,"root","");
		Statement statement = conn.createStatement();
		ResultSet st = statement.executeQuery("Select * from username");
		while(st.next())
		{
			messages.add(st.getString(2)); 
		}
		conn.close();
		int i = 0;
		ArrayList <String> final_msg =  new ArrayList<String>();
		Collections.reverse(messages);
		while(i < 10 && i != messages.size()-1)
		{
			final_msg.add(messages.get(i));
			i++;
		}
		System.out.println(final_msg);
		Collections.reverse(final_msg);
		return final_msg;
	}
	@Override
	public ArrayList<String> displayusers() throws Exception {
		return display_users;
	}	
}
