package emilylights;

import java.awt.EventQueue;
import java.io.IOException;

import emilylights.animation.Animation;
import emilylights.animation.AnimationHandler;
import emilylights.animation.CirclesAnimation;
import emilylights.http.WebServer;
import emilylights.opc.OPCClient;
import emilylights.tester.AnimationTester;

public class Main {

	private static final String IP = "192.168.1.122";
	private static int PORT = 7890;

	private static final boolean ENABLE_WEB_SERVER = false;
	private static final boolean ENABLE_LIGHT_WALL = true;
	private static final boolean ENABLE_TESTER = true;
	
	public static AnimationHandler animationHandler = new AnimationHandler();
	
	private static Animation CURRENT_ANIMATION = new CirclesAnimation();

	public static void main(String[] args) throws InterruptedException, IOException {
		
		OPCClient opc = new OPCClient(IP, PORT);
		WebServer webServer = new WebServer(animationHandler);
		animationHandler.setAnimation(CURRENT_ANIMATION);

		if(ENABLE_TESTER) {
			log("Tester started.");
			EventQueue.invokeLater(() -> {
				AnimationTester ex = new AnimationTester(animationHandler.getAnimation());
				ex.setVisible(true);
			});
		}

		if(ENABLE_WEB_SERVER) {
			webServer.start();
			log("Web-Server started.");
		}

		if(ENABLE_LIGHT_WALL) {
			log("Light-Wall started.");
		}

		log("Running animation..");
		log("Press ENTER to exit.");
		while (System.in.available() == 0) {
			if(ENABLE_LIGHT_WALL) {
				opc.animate(animationHandler.getAnimation());
			}
			Thread.sleep(33);
		}

		opc.clear(animationHandler.getAnimation());
		opc.close();

		System.exit(0);

	}

	private static void log(String s) {
		System.out.println(s);
	}

}
