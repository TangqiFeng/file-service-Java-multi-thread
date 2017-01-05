package ie.gmit.sw.server;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;


public class Logger {

	private ArrayBlockingQueue q;
	private FileWriter fw;

	public Logger(ArrayBlockingQueue q) throws IOException {
		this.q = q;
		fw = new FileWriter(new File("log.txt"),true);
	}
	
	void run() throws IOException, InterruptedException{
		Request r = (Request) q.take();
		if(r instanceof PoisonRequest){
			;
		}else{
			fw.write("\n" + r.toString() + "\n");
			fw.close();
		}
	}
	
}
