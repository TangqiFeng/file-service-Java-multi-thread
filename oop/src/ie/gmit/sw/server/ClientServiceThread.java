package ie.gmit.sw.server;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Calendar;
import java.util.Date;
/**
 * this class includes main functions(as methods) in server-side
 * provide solutions if get requests from clients.(one thread response one client's requests)
 * and record info append to a .txt file if a client download a file
 * @author kyle
 *
 */
class ClientServiceThread extends Thread {
	String Path;

	Socket clientSocket;
	String message;
	int clientID;
	ObjectOutputStream out;
	ObjectInputStream in;

	ClientServiceThread(Socket s, int i) {
		clientSocket = s;
		clientID = i;
	}
/**
 * the message send to client
 * @param msg
 */
	void sendMessage(String msg) {
		try {
			out.writeObject(msg);
			out.flush();
			System.out.println("server > " + msg);
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}

	public void run() {
		try {
			out = new ObjectOutputStream(clientSocket.getOutputStream());
			out.flush();
			in = new ObjectInputStream(clientSocket.getInputStream());
			System.out.println("Accepted Client : ID - " + clientID
					+ " : Address - "
					+ clientSocket.getInetAddress().getHostName());
			sendMessage("Connection successful");

			try {
				askOperation();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Client : ID - " + clientID + "   exit !...");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}finally {
			// 4: Closing connection
			try {
				in.close();
				out.close();
				clientSocket.close();
			} catch (IOException ioException) {
				// ioException.printStackTrace();
			}
		}
	}

	void askOperation() throws IOException, ClassNotFoundException, InterruptedException {
		while (true) {
			message = (String) in.readObject();
			if (message.equals("list")) {
				getList();
			}
			if (message.equals("download")) {
				downloadFile();
			}
		}
	}

	void downloadFile() throws IOException, ClassNotFoundException, InterruptedException {
		String signal = "no"; // check whether the file client need is exist
		String fileName;
		while (true) {
			fileName = (String) in.readObject();
			String[] str = new File(Path
					+ File.separator).list();
			for (int i = 0; i < str.length; i++) {
				if (str[i].equals(fileName)) {
					signal = "yes";
					break;
				}
			}
			if (signal.equals("yes")) {
				sendMessage("ok");
				break;
			} else {
				sendMessage("error");
			}
		}
		fileName = (String) in.readObject();
		sendFile(fileName);
	}

	void sendFile(String filename) throws IOException, InterruptedException, ClassNotFoundException {
		addLog();
		writeLog();
		int length = 0;
		byte[] sendByte = null;
		DataOutputStream dout = null;
		FileInputStream fin = null;
		dout = new DataOutputStream(out);
		File file = new File(Path+filename);
		fin = new FileInputStream(file);
		sendByte = new byte[1024];
		dout.writeUTF(file.getName());
		while ((length = fin.read(sendByte, 0, sendByte.length)) > 0) {
			dout.write(sendByte, 0, length);
			dout.flush();
		}
		fin.close();
		dout.close();
	}
	
	void addLog() throws IOException, InterruptedException, ClassNotFoundException{
		Request r = new Request();
		r.setCommand("Listing");
		message = (String) in.readObject();
		r.setHost(message);
		Calendar ca = Calendar.getInstance();//get current time
		Date date = ca.getTime();
		r.setD(date);
		Runner.q.add(r);
		// if want to interrupt the 
	}
	
	void writeLog() throws IOException, InterruptedException{
		Logger log = new Logger(Runner.q);
		log.run(); // add log
		
	}

	void getList() {
		String[] str = new File(Path
				+ File.separator).list();
		sendMessage(str.length + "");
		for (int i = 0; i < str.length; i++) {
			// System.out.println(str[i]);
			sendMessage(str[i]);
		}
	}

	public String getPath() {
		return Path;
	}

	public void setPath(String path) {
		Path = path;
	}

	
}
