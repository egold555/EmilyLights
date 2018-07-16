package emilylights.scene.options.workarounds;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import emilylights.scene.options.Color;
import emilylights.scene.options.SceneDescriptor;
import emilylights.scene.options.SceneOptions;

public class SceneDescriptorWorkaround {

	public String name;
	public int type;
	public Map<String, Object> options;
	public List<HexWorkaround> colors;
	
	
	
	@Override
	public String toString() {
		return "SceneDescriptorWorkaround [name=" + name + ", type=" + type + ", options=" + options + ", colors="
				+ colors + "]";
	}



	public SceneDescriptor toSceneDescriptor(int id) {
		
		SceneDescriptor sd = new SceneDescriptor();
		sd.name = name;
		sd.id = id;
		sd.type = type;
		sd.img = "";
		SceneOptions so  = new SceneOptions();
		
		List<Color> soColors = new ArrayList<Color>();
		for(HexWorkaround hw : colors) {
			soColors.add(hw.toColor());
		}
		
		so.colors = soColors.toArray(new Color[0]);
		so.customOptions = options;
		
		sd.options = so;
		
		return sd;
	}
	
}
