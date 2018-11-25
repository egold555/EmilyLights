package emilylights.scene;

import java.net.InetAddress;
import java.net.UnknownHostException;

import emilylights.scene.options.Color;

public class SceneBoot extends Scene {

	private BootCode bootCode;
	private boolean wallOn = true;
	
	enum BootCode {
		SUCCESS, //Green
		ERROR_NO_INTERNET, //Yellow
		ERROR_OTHER //Red
	}
	
	public SceneBoot() {
		bootCode = getBootCode();
	}
	
	private BootCode getBootCode() {
		
		try {
			String host = InetAddress.getLocalHost().getHostAddress();
			if(host == null || host == "127.0.0.1") {
				return BootCode.ERROR_NO_INTERNET;
			}
		} catch (UnknownHostException e) {
			return BootCode.ERROR_NO_INTERNET;
		}
		
		
		return BootCode.SUCCESS;
		
		
	}
	
	@Override
	public void draw() {
		
		if(!wallOn) {
			setAllPixels(Color.COLOR_BLACK);
		}
		
		if(bootCode == BootCode.SUCCESS) {
			setAllPixels(Color.COLOR_GREEN);
		}
		else if(bootCode == BootCode.ERROR_NO_INTERNET) {
			setAllPixels(Color.COLOR_YELLOW);
		}
		else if(bootCode == BootCode.ERROR_OTHER) {
			setAllPixels(Color.COLOR_RED);
		}
	}
	
}
