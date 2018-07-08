package emilylights;

import java.awt.EventQueue;
import java.io.IOException;
import java.util.Arrays;

import emilylights.audio.Audio;
import emilylights.audio.AudioPropertiers;
import emilylights.audio.AudioUtils;
import emilylights.http.WebServer;
import emilylights.opc.OPCClient;
import emilylights.scene.Scene;
import emilylights.scene.SceneHandler;
import emilylights.tester.AnimationTester;

public class Main {

	private static final String IP = "192.168.1.122";
	private static int PORT = 7890;
	
	public static SceneHandler animationHandler = new SceneHandler();

	private static final boolean ENABLE_WEB_SERVER = true;
	private static final boolean ENABLE_LIGHT_WALL = false;
	private static final boolean ENABLE_TESTER = true;
	
	public static AudioPropertiers audioPropertiers = new AudioPropertiers();
	public static Audio audio = new Audio();;
	
	public static void main(String[] args) throws InterruptedException, IOException {

		OPCClient opc = new OPCClient(IP, PORT);
		AnimationTester ex = new AnimationTester();
		WebServer webServer = new WebServer(animationHandler);
		//animationHandler.setAnimation(0);
		//animationHandler.setScene(new SceneRainbow());
		
		String[] audioSources = AudioUtils.getAudioSources(audio);
		System.out.println(Arrays.toString(audioSources));
		
		audioPropertiers.fft_binns = (int)(1.5f * Math.max(11, 9));
        audioPropertiers.fft = new float[audioPropertiers.fft_binns];
		
		audio.SetAudioFormat();
		audio.Set_and_Start_Mixer(1); //1 = primary microphone
		
		audioPropertiers.gain = 35;

		log("WEB_SERVER: " + ENABLE_WEB_SERVER);
		log("LIGHT_WALL: " + ENABLE_LIGHT_WALL);
		log("TESTER: " + ENABLE_TESTER);
		log("");

		if(ENABLE_TESTER) {
			EventQueue.invokeLater(() -> {
				ex.setVisible(true);
			});
		}

		if(ENABLE_WEB_SERVER) {
			webServer.start();
		}
		
		animationHandler.reloadJSON();

		log("Running animation..");
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

	private static void log(String s) {
		System.out.println(s);
	}

}
