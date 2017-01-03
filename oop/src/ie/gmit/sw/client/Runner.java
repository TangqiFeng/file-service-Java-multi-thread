package ie.gmit.sw.client;

import java.net.Socket;

public class Runner {
	
	Socket requestSocket;
	public static void main(String[] args) throws Throwable{
		UI cin = new UI();
		String s="";
		Connection conn = new Connection();
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
					if(conn.s == null){
					System.out.println("111111111");
					conn.run();
					Parseator p = new Parseator(new Context());
					p.init();
					Context ctx = p.getCtx();
					System.out.println(ctx);
					System.out.println();
				}else{
					System.out.println("<<ERROR>>");
					System.out.println("         You have already connected");
					System.out.println("                                    !!!");
					System.out.println();
				}
					break;
					
				}else if(s.equals("2")){
					if(conn.s == null){
						System.out.println("You need connect first!   try again>");
						s = cin.getString();
					}
					
				}else if(s.equals("3")){
					if(conn.s == null){
						System.out.println("You need connect first!   try again>"); 
						s = cin.getString();
					}
				}else if(s.equals("4")){
					System.out.println("bye-bye  @.@");
					flag = false;
					break;
				}else{
					System.out.println();
					System.out.println("ERROR   Please input [1-4]>");
					s = cin.getString();
				}
			}
		}
	}

}
