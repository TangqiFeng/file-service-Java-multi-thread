package ie.gmit.sw.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Runner {
	String ss = null; // ignore repeat connection
	Socket requestSocket;
	ObjectOutputStream out;
	ObjectInputStream in;
	Parseator p;
	Context ctx;
	String message = ""; // store received message
	UI cin = new UI();  // input from keyboard
	String s="";   //input String from keyboard
	
	public void run() throws Throwable{
		try {
			Boolean flag = true;
			while(flag){
				System.out.println("1. Connect to Server");
				System.out.println("2. Print File Listing");
				System.out.println("3. Download File");
				System.out.println("4. Quit");
				System.out.println();
				System.out.println("Type Option [1-4]>");
				s = cin.getString();
				while(true){
					if(s.equals("1")){
						if(ss == null){
						System.out.println("111111111");
						connection();
						//System.out.println(ctx);
						System.out.println();
					}else{
						System.out.println("<<ERROR>>");
						System.out.println("         You have already connected");
						System.out.println("                                    !!!");
						System.out.println();
					}
						break;
						
					}else if(s.equals("2")){
						if(ss == null){
							System.out.println("<<ERROR>>");
							System.out.println("You need connect first!   try again>");
							System.out.println();
						}else{
							getList();
						}
						break;
					}else if(s.equals("3")){
						if(ss == null){
							System.out.println("<<ERROR>>");
							System.out.println("You need connect first!   try again>"); 
							System.out.println();
						}
					}else if(s.equals("4")){
						System.out.println("bye-bye  @.@");
						flag = false;
						break;
					}else{
						System.out.println("<<ERROR>>");
						System.out.println("           Please input [1-4]>");
						System.out.println();
						break;
					}
				}
			}
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	
	void connection() throws Throwable{
		try {

			// get context object from conf.xml
			p = new Parseator(new Context());
			p.init();
			ctx = p.getCtx();
			String ipaddress = ctx.getServer_host();
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
			ss = "ok";  // used to ignore repeat connection
			
		} catch (UnknownHostException unknownHost) {
			System.err.println("You are trying to connect to an unknown host!");
		} catch (IOException ioException) {
			ioException.printStackTrace();
		} 
	}
	
	void sendMessage(String msg) {
		try {
			out.writeObject(msg);
			out.flush();
			System.out.println("client> " + msg);
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}
	
	void getList() throws ClassNotFoundException, IOException{
		sendMessage("list");
		message = (String) in.readObject();
		int sum = Integer.parseInt(message); //get the amount of files
		System.out.println("file list :");
		System.out.println();
		for (int i = 0; i < sum; i++){
			message = (String) in.readObject();
			System.out.println(message);
		}
		System.out.println();
	}
	
	public static void main(String args[]) throws Throwable {
		Runner r = new Runner();
		r.run();
	}

}
