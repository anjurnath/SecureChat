
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class server{
	
	static Console console;
	ServerSocket serverSocket;
	Socket socket;
	static PrintWriter output;
	static BufferedReader input;
	static PrintWriter inputKey;
	static ArrayList<User> users = new ArrayList<User>(); // clients are added into this list
	public server(){
		try{
			
			serverSocket = new ServerSocket(28753); // server port number:28753
			generateKey.generateSymmKey(); // Symmetric key generation
			System.out.println("Generated key: " + generateKey.symmKeyVal);
			new Thread(new Runnable(){
				@Override
				public void run() {
					while(true){
						try{
							socket = serverSocket.accept();// Server accepts connections from clients
							output = new PrintWriter(socket.getOutputStream(),true);// to send data to clients
							input = new BufferedReader(new InputStreamReader(socket.getInputStream()));// to receive data from clients
							inputKey = new PrintWriter(socket.getOutputStream(),true);
							broadcastKey();//Server broadcasts messages to all the clients connected to that particular server
							User user = new User(output,input,inputKey);
							user.uThread.start();
							users.add(user);
								
						}catch(IOException e){
							e.printStackTrace();
						}
					}
					
				}}).start();
					
		}catch(IOException e){
			e.printStackTrace();
		}
		
		console = new Console();
	}
	public static void main(String[] args){
		new server();	
		console.writeInServerConsole("Server Started!!!");	
	}	
	public static void broadcastKey(){
		for(int i=0; i < users.size(); i++){
			User user = users.get(i);	
			if(user == null || user.output ==null){
				users.remove(user);
			}
			user.inputKey.println(generateKey.symmKeyVal);
			System.out.println("Generated key: " + generateKey.symmKeyVal);
		}
	}	
	public static void broadcast(String text){
		for(int i=0; i < users.size(); i++){
			User user = users.get(i);	
			if(user == null || user.output ==null){
				users.remove(user);
			}
			user.output.println(text);
		}
	}
}