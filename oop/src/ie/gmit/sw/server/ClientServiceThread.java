package ie.gmit.sw.server;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


class ClientServiceThread extends Thread {

	Socket clientSocket;
	String message;
	int clientID;
	ObjectOutputStream out;
	ObjectInputStream in;

	ClientServiceThread(Socket s, int i) {
		clientSocket = s;
		clientID = i;
	}

	void sendMessage(String msg) {
		try {
			out.writeObject(msg);
			out.flush();
			System.out.println("server > " + msg);
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}
	
	public void run(){
		try {
			out = new ObjectOutputStream(clientSocket.getOutputStream());
			out.flush();
			in = new ObjectInputStream(clientSocket.getInputStream());
			System.out.println("Accepted Client : ID - " + clientID + " : Address - "
					+ clientSocket.getInetAddress().getHostName());
			sendMessage("Connection successful");
			
			askOperation();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void askOperation() throws IOException, ClassNotFoundException{
		message = (String) in.readObject();
		if(message.equals("list")){
			String[] str = new File("src//ie//gmit//sw//server//files//" + File.separator).list();
			sendMessage(str.length+"");
			for (int i = 0; i < str.length; i++) {
				System.out.println(str[i]);
				sendMessage(str[i]);
			}
			
		}
	}
	
}