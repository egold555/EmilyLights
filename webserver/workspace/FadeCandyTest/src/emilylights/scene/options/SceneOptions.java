package emilylights.animation.options;

import java.util.Arrays;
import java.util.HashMap;

public class AnimationOptions {

	public int type;
	public Color[] colors;
	public HashMap<String, String> options = new HashMap<String, String>();
	@Override
	public String toString() {
		return "AnimationOptions [type=" + type + ", colors=" + Arrays.toString(colors) + ", options=" + options + "]";
	}
	
	
	
}