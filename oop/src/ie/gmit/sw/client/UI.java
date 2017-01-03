package ie.gmit.sw.client;

import java.util.Scanner;

public class UI {
	
	
	public String getString(){
		Scanner cin = new Scanner(System.in);
		return cin.nextLine();
	}
	
	public int getInt(){
		Scanner cin = new Scanner(System.in);
		return cin.nextInt();
	}
}
