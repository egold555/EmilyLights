package emilylights;

import java.io.IOException;
import java.util.Scanner;

import emilylights.animation.AnimatedImageAnimation;
import emilylights.animation.AnimationHandler;
import emilylights.animation.DotsAnimation;
import emilylights.animation.DummyAnimation;
import emilylights.animation.PongAnimation;
import emilylights.animation.RainbowAnimation;
import emilylights.animation.StaticImageAnimation;
import emilylights.http.WebServer;
import emilylights.opc.OPCClient;

public class Main {

	private static final String IP = "192.168.1.122";
	private static int PORT = 7890;
	
	static Scanner s= new Scanner(System.in);
	
	public static void main(String[] args) throws InterruptedException, IOException {
		OPCClient opc = new OPCClient(IP, PORT);
		

		AnimationHandler animationHandler = new AnimationHandler();
		WebServer webServer = new WebServer(animationHandler);
		
		webServer.start();
		
		System.out.println("Running animation..");
		
		animationHandler.setAnimation(new RainbowAnimation());
		
		while (System.in.available() == 0) {
			opc.animate(animationHandler.getAnimation());
			Thread.sleep(33);
		}
		
		System.out.println("Exited.");
		opc.clear(animationHandler.getAnimation());
		opc.close();
		//webServer.stop();
		
		System.exit(0);
		
	}
	
	

}
