import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class User implements Runnable {
	public Thread uThread = new Thread(this);
	public PrintWriter output;
	public BufferedReader input;
	public PrintWriter inputKey;
	
	public User(PrintWriter output, BufferedReader input, PrintWriter inputKey){
		this.output = output;
		this.input = input;
		this.inputKey = inputKey;
	}
	
	
	public void run(){
		while(true){
			String chat;
			try {
				chat = input.readLine();
				//server.broadcastKey();
				server.broadcast(chat);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
