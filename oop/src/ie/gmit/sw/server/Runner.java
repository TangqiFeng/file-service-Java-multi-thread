package ie.gmit.sw.server;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;

public class Runner {
	
	static ArrayBlockingQueue<Request> q = new ArrayBlockingQueue<Request>(7);
	
	public static void main(String[] args) throws Exception {
		@SuppressWarnings("resource")
		ServerSocket m_ServerSocket = new ServerSocket(7777, 100 , InetAddress.getByName ("127.0.0.1") );
		int id = 0;
		while (true) {
			Socket clientSocket = m_ServerSocket.accept();
			ClientServiceThread cliThread = new ClientServiceThread(clientSocket, id++);
			cliThread.start();

		}
	}
}
