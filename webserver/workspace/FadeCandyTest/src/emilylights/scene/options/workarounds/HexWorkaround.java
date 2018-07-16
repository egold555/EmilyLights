package emilylights.scene.options.workarounds;

import emilylights.scene.options.Color;

public class HexWorkaround {

	public String hex;

	@Override
	public String toString() {
		return "HexWorkaround [hex=" + hex + "]";
	}
	
	public Color toColor() {
		return new Color(hex);
	}
	
}
