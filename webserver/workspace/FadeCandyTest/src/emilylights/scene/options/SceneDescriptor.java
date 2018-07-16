package emilylights.scene.options;

public class SceneDescriptor {
	
	public String name;
	public int type;
	public int id;
	public SceneOptions options;
	
	
	@Override
	public String toString() {
		return "SceneDescriptor [name=" + name + ", type=" + type + ", id=" + id + ", options="
				+ options + "]";
	}	
	
	
	
}
