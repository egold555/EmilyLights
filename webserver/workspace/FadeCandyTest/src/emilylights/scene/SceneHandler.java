package emilylights.scene;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import emilylights.scene.options.SceneDescriptor;
import emilylights.scene.options.ShittyWorkaround;
import emilylights.scene.testing.SceneDummy;

public class SceneHandler {

	private Scene currentAnimation = new SceneDummy();
	private List<SceneDescriptor> sceneDescriptors = new ArrayList<SceneDescriptor>();

	public void setAnimation(int id) throws IOException {
		reloadJSON();
		for(SceneDescriptor sd : sceneDescriptors) {
			if(sd.id == id) {
				setScene(createSceneFromDescriptor(sd));
			}
		}
	}
	
	public void setScene(Scene scene) {
		this.currentAnimation = scene;
	}

	public Scene getAnimation() {
		return currentAnimation;
	}

	private Scene createSceneFromDescriptor(SceneDescriptor sd){

		int type = sd.type;
		Scene scene;

		if(type == 0) {
			scene = new SceneDots();
		}
		else if(type == 1) {
			scene = new SceneRainbow();
		}
		else if(type == 2) {
			scene = new SceneCircles();
		}
		else if(type == 3) {
			scene = new SceneRaindrops();
		}
		else {
			scene = new SceneDummy();
		}

		if(sd.options != null) {
			scene.setOptions(sd.options);
		}

		return scene;
	}

	public void reloadJSON() throws JsonIOException, JsonSyntaxException, FileNotFoundException {
		Gson gson = new GsonBuilder().create();
		sceneDescriptors =  ((ShittyWorkaround)gson.fromJson(new JsonReader(new FileReader(new File("files\\scenes.json"))), ShittyWorkaround.class)).scenes;
	}

}
