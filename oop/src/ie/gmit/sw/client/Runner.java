package ie.gmit.sw.client;

public class Runner {
	
	public static void main(String[] args) {
		UI cin = new UI();
		String s="";
		int i;
		System.out.println("1. Connect to Server");
		System.out.println("2. Print File Listing");
		System.out.println("3. Download File");
		System.out.println("4. Quit");
		System.out.println();
		System.out.println("Type Option [1-4]>");
		s = cin.getString();
		while(true){
			if(s.equals("1")){
				System.out.println("111111111"); break;
			}else if(s.equals("2")){
				System.out.println("222222222"); break;
			}else if(s.equals("3")){
				System.out.println("3333333333"); break;
			}else if(s.equals("4")){
				System.out.println("4444444444"); break;
			}else{
				System.out.println();
				System.out.println("ERROR   Please input [1-4]>");
				s = cin.getString();
			}
		}
	}

}
