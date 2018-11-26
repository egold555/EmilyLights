package emilylights;

import java.awt.EventQueue;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.Enumeration;

import emilylights.http.WebServer;
import emilylights.opc.OPCClient;
import emilylights.scene.Scene;
import emilylights.scene.SceneBoot;
import emilylights.scene.SceneHandler;
import emilylights.tester.AnimationTester;

public class Main {

	private static final boolean DEPLOYED_TO_PI = true; //CHANGE ME
	
	private static final String IP = DEPLOYED_TO_PI ? "127.0.0.1" : "192.168.1.122";
	private static int PORT = 7890;
	
	public static SceneHandler animationHandler = new SceneHandler();

	private static final boolean ENABLE_WEB_SERVER = true;
	private static final boolean ENABLE_LIGHT_WALL = true;
	public static final boolean ENABLE_TESTER = !DEPLOYED_TO_PI;
	
	//public static AudioPropertiers audioPropertiers = new AudioPropertiers();
	//public static Audio audio = new Audio();;
	
	public static void main(String[] args) throws InterruptedException, IOException {

		String webserverIp = waitForLocalIPAddress();
		System.out.println("Local IP Address: " + webserverIp);
		Thread.sleep(5000);
		
		OPCClient opc = new OPCClient(IP, PORT);
		AnimationTester animTester = null;
		if(ENABLE_TESTER) {
			animTester = new AnimationTester();
		}
		final AnimationTester ex = animTester;
		
		WebServer webServer = new WebServer(animationHandler);
		//animationHandler.setAnimation(0);
//		Mixer mixer = AudioSystem.getMixer(Shared.getMixerInfo(false, true).get(1));
		//animationHandler.setScene(new SceneMusicGraph(mixer));
		animationHandler.setScene(new SceneBoot());
		
//		String[] audioSources = AudioUtils.getAudioSources(audio);
//		System.out.println(Arrays.toString(audioSources));
		
//		audioPropertiers.fft_binns = (int)(1.5f * Math.max(11, 9));
//        audioPropertiers.fft = new float[audioPropertiers.fft_binns];
//		
//		audio.SetAudioFormat();
//		audio.Set_and_Start_Mixer(1); //1 = primary microphone
//		
//		audioPropertiers.gain = 35; //35
		
//		for(Mixer.Info info : Shared.getMixerInfo(false, true)){
//			System.out.println(info.getName());
//		}

		//log("WEB_SERVER: " + ENABLE_WEB_SERVER);
		//log("LIGHT_WALL: " + ENABLE_LIGHT_WALL);
		//log("TESTER: " + ENABLE_TESTER);
		//log("");

		if(ENABLE_TESTER) {
			EventQueue.invokeLater(() -> {
				ex.setVisible(true);
			});
		}

		if(ENABLE_WEB_SERVER) {
			webServer.start(webserverIp);
		}
		
		//animationHandler.reloadJSON();

		log("Running.");
		log("Press ENTER to exit.");
		while (System.in.available() == 0) {
			Scene animation = animationHandler.getAnimation();
			animation.reset();
			animation.draw();
			
			if(ENABLE_LIGHT_WALL) {
				opc.animate(animation);
			}
			if(ENABLE_TESTER) {
				ex.panel.updateFromAnimation(animation);
			}
			
			Thread.sleep(33);
		}

		opc.clear(animationHandler.getAnimation());
		opc.close();

		System.exit(0);

	}
	
	// Wait until we have a local IP address, then return it.
	public static String waitForLocalIPAddress() {
		String ip;
		while (true) {
			ip = getLocalIPAddress();
			if (ip != null) {
				return ip;
			}
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
		}
	}
	
	public static String getLocalIPAddress() {
		try {
	        Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
	        for (NetworkInterface netint : Collections.list(nets)) {
	            Enumeration<InetAddress> inetAddresses = netint.getInetAddresses();
		        for (InetAddress inetAddress : Collections.list(inetAddresses)) {
		            if (!inetAddress.isLoopbackAddress() && !inetAddress.isLinkLocalAddress() && inetAddress instanceof Inet4Address) {
		            	return inetAddress.toString().substring(1);
		            }
		        }
			}
		}
		catch (Exception e) {
			return null;
		}
		
		return null;
    }


	private static void log(String s) {
		System.out.println(s);
	}

}
