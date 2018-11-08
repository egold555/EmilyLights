package emilylights.scene.testing;

import java.util.Arrays;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.TargetDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

import be.tarsos.dsp.AudioDispatcher;
import be.tarsos.dsp.AudioEvent;
import be.tarsos.dsp.Oscilloscope;
import be.tarsos.dsp.Oscilloscope.OscilloscopeEventHandler;
import be.tarsos.dsp.io.jvm.JVMAudioInputStream;
import emilylights.scene.Scene;

public class SceneMusicGraph extends Scene implements OscilloscopeEventHandler {

	public SceneMusicGraph(Mixer mix) {
		try {
			setNewMixer(mix);
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	AudioDispatcher dispatcher;
	Mixer currentMixer;
	float[] data;
	
	@Override
	public void draw() {

		for(int bar = 0; bar < 11; bar++) {

//						int[] audioLevel = Main.audio.processAudio(Main.audioPropertiers);
//						System.out.println(Arrays.toString(audioLevel));
//			
//						int test = (audioLevel[0] + audioLevel[1]) / 2;
//			
//						test -= 4; //cause of fan
//			
//						test/= 10;
//			
//						test = 9 - test;
//						
//						//System.out.println("Audio: " + test);
//						
//						this.setLineCol(bar, 8, test, new Color((float)bar/11, 1, 1));
			
			//0-1 4096 channels
			
			//System.out.println("Data: " + data.length);
			System.out.println(Arrays.toString(data));
		}
	}

	@Override
	public void handleEvent(float[] data, AudioEvent event) {
		this.data = data;
	}

	private void setNewMixer(Mixer mixer) throws LineUnavailableException,
	UnsupportedAudioFileException {

		if(dispatcher!= null){
			dispatcher.stop();
		}
		currentMixer = mixer;

		float sampleRate = 44100;
		int bufferSize = 2048;
		int overlap = 0;

		final AudioFormat format = new AudioFormat(sampleRate, 16, 1, true,
				true);
		final DataLine.Info dataLineInfo = new DataLine.Info(
				TargetDataLine.class, format);
		TargetDataLine line;
		line = (TargetDataLine) mixer.getLine(dataLineInfo);
		final int numberOfSamples = bufferSize;
		line.open(format, numberOfSamples);
		line.start();
		final AudioInputStream stream = new AudioInputStream(line);

		JVMAudioInputStream audioStream = new JVMAudioInputStream(stream);
		// create a new dispatcher
		dispatcher = new AudioDispatcher(audioStream, bufferSize,
				overlap);

		// add a processor, handle percussion event.
		//dispatcher.addAudioProcessor(new DelayEffect(400,0.3,sampleRate));
		dispatcher.addAudioProcessor(new Oscilloscope(this));
		//dispatcher.addAudioProcessor(new AudioPlayer(format));

		// run the dispatcher (on a new thread).
		new Thread(dispatcher,"Audio dispatching").start();
	}

}
