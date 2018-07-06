package emilylights.scene.options;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SceneOptions {
	
	public Color[] colors;
	public Map<String, Object> customOptions = new HashMap<String, Object>();
	
	
	@Override
	public String toString() {
		return "SceneOptions [colors=" + Arrays.toString(colors) + ", customOptions=" + customOptions + "]";
	}
	
}