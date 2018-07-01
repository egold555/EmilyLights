package emilylights;

import java.io.IOException;
import java.util.Scanner;

import emilylights.animation.Animation;
import emilylights.animation.DotsAnimation;
import emilylights.http.WebServer;
import emilylights.opc.OPCClient;

public class Main {

	private static final String IP = "127.0.0.1";
	private static int PORT = 7890;
	
	static Scanner s= new Scanner(System.in);
	
	public static void main(String[] args) throws InterruptedException, IOException {
		
		OPCClient opc = new OPCClient(IP, PORT);
		WebServer webServer = new WebServer();

		Animation animation = new DotsAnimation();
		
		webServer.start();
		
		System.out.println("Running animation..");
		
		while (System.in.available() == 0) {
			opc.animate(animation);
			Thread.sleep(33);
		}
		
		System.out.println("Exited.");
		opc.clear(animation);
		opc.close();
		//webServer.stop();
	}
	
	

}
