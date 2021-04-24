package src;

public class WelcomeWindow {
	
	public WelcomeWindow() {
		
	}
	
	public void welcome() {
		System.out.println( " _____     _       _____ ___ \r\n" + 
							"|  _  |_ _| |_ ___|     | . |\r\n" + 
							"|     | | |  _| . | | | | . |\r\n" + 
							"|__|__|___|_| |___|_|_|_|___|\r\n");
		
		AutoM8.client.printClient();
		
		System.out.println("AutoM8 Client successfully initialized.\n");
		System.out.println("Access your client via autom8.cloud");
		System.out.println("LINK CODE: " + AutoM8.client.LinkCode + "\n");
		System.out.println("Force quit via 'CTRL + C'");
	}
	
}
