package emilylights.scene;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import emilylights.scene.options.SceneDescriptor;
import emilylights.scene.options.ShittyWorkaround;
import emilylights.scene.testing.SceneDummy;

public class SceneHandler {

	private Scene currentAnimation = new SceneDummy();
	private List<SceneDescriptor> sceneDescriptors = new ArrayList<SceneDescriptor>();
	private static final File SCENES_FILE = new File("files\\scenes.json");

	public SceneHandler() {
		try {
			reloadJSON();
		} catch (JsonIOException | JsonSyntaxException | FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //Just to make sure, I dont think I need this but it doesnt hurt
	}
	
	public void setScene(int id) throws IOException {
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

	private void reloadJSON() throws JsonIOException, JsonSyntaxException, FileNotFoundException {
		Gson gson = new GsonBuilder().create();
		sceneDescriptors = ((ShittyWorkaround)gson.fromJson(new JsonReader(new FileReader(SCENES_FILE)), ShittyWorkaround.class)).scenes;
	}
	
	private void saveJSON() throws JsonIOException, IOException {
		Gson gson = new GsonBuilder().create();
		gson.toJson(new ShittyWorkaround(this.sceneDescriptors), new FileWriter(SCENES_FILE));
	}
	
	public void deleteScene(int id) throws JsonIOException, IOException {
		reloadJSON();
		int indexToRemove = -1;
		for(SceneDescriptor sd : sceneDescriptors) {
			if(sd.id == id) {
				indexToRemove = sceneDescriptors.indexOf(sd);
			}
		}
		if(indexToRemove != -1) {
			sceneDescriptors.remove(indexToRemove);
		}
		saveJSON();
	}

}
