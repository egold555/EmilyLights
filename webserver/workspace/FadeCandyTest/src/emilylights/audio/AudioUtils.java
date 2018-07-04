package emilylights.audio;

import javax.sound.sampled.Mixer;

public class AudioUtils {

	public static String[] getAudioSources(Audio audio) {
        final Mixer.Info[] mixerInfo = audio.GetMixerInfo();
        String[] result;
        if (mixerInfo.length <= 0) {
            result = new String[0];
        }
        else {
            result = new String[mixerInfo.length];
            for (int i = 0; i < mixerInfo.length; ++i) {
                result[i] = mixerInfo[i].getName();
            }
        }
        return result;
    }
	
}
