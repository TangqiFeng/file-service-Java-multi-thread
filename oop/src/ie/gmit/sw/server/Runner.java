package ie.gmit.sw.server;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
/**
 * This class include main method (server)
 * @author kyle
 *
 */
public class Runner {
	//BlockingQueue  store Request.PoidonRequest object
	static ArrayBlockingQueue<Request> q = new ArrayBlockingQueue<Request>(7);
	/**
	 * @param args
	 * there have two parameters:  port and the path of files provided for download 
	 * in server-side
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		@SuppressWarnings("resource")
		String port = args[0];
		String path = args[1];
		ServerSocket m_ServerSocket = new ServerSocket(Integer.parseInt(port),10 );
		int id = 0;
		while (true) {
			Socket clientSocket = m_ServerSocket.accept();
			ClientServiceThread cliThread = new ClientServiceThread(clientSocket, id++);
			cliThread.setPath(path);
			cliThread.start();

		}
	}
}
