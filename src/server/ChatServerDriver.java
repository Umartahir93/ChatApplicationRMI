package server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ChatServerDriver {

	static void deletefromtable() throws ClassNotFoundException, SQLException
	{
		String DB_URL  = new String("jdbc:mysql://localhost:3306/client_admin"); 
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(DB_URL,"root","");
		String query = "DELETE FROM username";
		PreparedStatement preparedStmt = conn.prepareStatement(query);
	    preparedStmt.executeUpdate();
	    conn.close();
	}
	
	
	public static void main(String args []) throws RemoteException, MalformedURLException, ClassNotFoundException, SQLException
	{
		deletefromtable();
		Registry reg = LocateRegistry.createRegistry(1099);
		Remote r = new ChatServer();
		reg.rebind("chatting", r);
		System.out.println("Server is working for client");
		Registry adminreg = LocateRegistry.createRegistry(1011);
		Remote admin_r = new ChatServer();
		adminreg.rebind("Admin", admin_r);
		System.out.println("Server is working for admin client");
	}
}
