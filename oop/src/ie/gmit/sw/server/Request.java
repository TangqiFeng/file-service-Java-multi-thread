package ie.gmit.sw.server;

import java.io.Serializable;
import java.util.Date;


@SuppressWarnings("serial")
public class Request implements Serializable {
	
	private String command;
	private String host;
	private Date d;
	
	public Request() {
		super();
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public Date getD() {
		return d;
	}

	public void setD(Date d) {
		this.d = d;
	}

	@Override
	public String toString() {
		return "[INFO]" + command + " requested by " + host + " at " + d + "\n";
	}

	
}
