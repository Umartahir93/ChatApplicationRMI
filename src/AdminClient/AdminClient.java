package AdminClient;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Scanner;

import server.ChatServerInterface;
public class AdminClient {
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Registry myreg = LocateRegistry.getRegistry("127.0.0.1" , 1011);
		ChatServerInterface chatServer = (ChatServerInterface)myreg.lookup("Admin");
		boolean exit =true;
		Scanner scan = new Scanner(System.in);
		while(exit)
		{
			System.out.println("To display messages last 10 messages press 1");
			System.out.println("To display users press 2");
			System.out.println("To exit press anyother key");
			
			int value = scan.nextInt();
			if(value == 1)
			{
				ArrayList<String> temp = new ArrayList<String>(); 
				System.out.println("The last 10 messages are");
				temp = chatServer.displaydata();
				for(int i  = 0  ; i < temp.size() ; i++)
					System.out.println(temp.get(i));
			}
				
			else if(value == 2)
				{
				ArrayList<String> temp = new ArrayList<String>();
				temp = chatServer.displayusers();
				System.out.println("The Users are");
				for(int i = 0 ; i <temp.size() ; i++)
				{
					System.out.println(temp.get(i));
				}
				}
			else
				exit = false;
		}
		
	}
}

