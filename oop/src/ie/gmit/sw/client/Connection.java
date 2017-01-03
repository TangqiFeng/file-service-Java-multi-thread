package ie.gmit.sw.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Connection {
	String s = null;
	Socket requestSocket;
	ObjectOutputStream out;
	ObjectInputStream in;
	String message = "";
	String ipaddress = "127.0.0.1";
	
	public Connection() {
		super();
	}
	
	void run() throws Exception {
		try {
			// 1. creating a socket to connect to the server
			requestSocket = new Socket(ipaddress, 7777);
			System.out.println("Connected to " + ipaddress + " in port 2004");
			System.out.println();
			// 2. get Input and Output streams
			out = new ObjectOutputStream(requestSocket.getOutputStream());
			out.flush();
			in = new ObjectInputStream(requestSocket.getInputStream());
			// 3. Communicating with the server
			message = (String) in.readObject();
			System.out.println("<<<<< "+message+" >>>>>");
			s = "ok";
			
		} catch (UnknownHostException unknownHost) {
			System.err.println("You are trying to connect to an unknown host!");
		} catch (IOException ioException) {
			ioException.printStackTrace();
		} finally {
			// 4: Closing connection
			try {
				in.close();
				out.close();
				requestSocket.close();
			} catch (IOException ioException) {
				ioException.printStackTrace();
			}
		}
	}
	
	void test() throws ClassNotFoundException, IOException{
		out = new ObjectOutputStream(requestSocket.getOutputStream());
		out.flush();
		in = new ObjectInputStream(requestSocket.getInputStream());
		sendMessage("haha");
		message = (String) in.readObject();
		System.out.println(message);
	}
	
	
	void sendMessage(String msg) {
		try {
			out.writeObject(msg);
			out.flush();
			//System.out.println("client> " + msg);
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}
}
