package emilylights.scene;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.IIOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonReader;

import emilylights.scene.options.SceneDescriptor;
import emilylights.scene.options.workarounds.ListOfScenesWorkaround;
import emilylights.scene.options.workarounds.SceneDescriptorWorkaround;
import emilylights.scene.testing.SceneDummy;

public class SceneHandler {

	private Scene currentAnimation = new SceneDummy();
	private List<SceneDescriptor> sceneDescriptors = new ArrayList<SceneDescriptor>();
	private static final File SCENES_FILE = new File("files\\scenes.json");

	public SceneHandler() {
		try {
			reloadJSON();
		} catch (JsonIOException | JsonSyntaxException | IOException e) {
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

	private void reloadJSON() throws JsonIOException, JsonSyntaxException, IOException {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		FileReader fileReader = new FileReader(SCENES_FILE);
		sceneDescriptors = ((ListOfScenesWorkaround)gson.fromJson(new JsonReader(fileReader), ListOfScenesWorkaround.class)).scenes;
		fileReader.close();
	}
	
	private void saveJSON() throws JsonIOException, IOException {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		FileWriter fileWriter = new FileWriter(SCENES_FILE);
		gson.toJson(new ListOfScenesWorkaround(this.sceneDescriptors), fileWriter);
		fileWriter.close();
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
		
		new File("files\\previmgs\\" + id + ".gif").delete();
		
		saveJSON();
	}
	
	public void addScene(String json) throws JsonIOException, IOException {
		SceneDescriptor sd = createForSaveAndPreview(json);
		sceneDescriptors.add(sd);
		saveJSON();
	}
	
	public void previewScene(String json) throws IIOException, IOException {
		SceneDescriptor sd = createForSaveAndPreview(json);
		setScene(createSceneFromDescriptor(sd));
	}
	
	private SceneDescriptor createForSaveAndPreview(String json) throws IIOException, IOException {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		SceneDescriptorWorkaround sdw = gson.fromJson(json, SceneDescriptorWorkaround.class);
		SceneDescriptor sd = sdw.toSceneDescriptor(getUnusedId());
		createSceneFromDescriptor(sd).toGif(sd.id);
		return sd;
	}
	
	private int getUnusedId() {
		int largest = 1;
		for(SceneDescriptor sd : sceneDescriptors) {
			if(sd.id > largest) {
				largest = sd.id;
			}
		}
		return largest+1;
	}

}
