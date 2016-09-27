package execution.audio;

import java.io.File;
import java.io.IOException;

public class OmxPlayerAudioExecutor implements AudioExecutor {

	@Override
	public boolean play(File audioFile) {
		if (audioFile.exists()) {
			try {
				Process scriptProcess = new ProcessBuilder("omxplayer", audioFile.getAbsolutePath()).start();
				scriptProcess.waitFor();
				return true;
				
			} catch (InterruptedException | IOException e) {
				System.out.println("OmxPlayerAudioExecutor: failed to execute the script");
			}

		} else {
			System.out.println("OmxPlayerAudioExecutor: missing file " + audioFile.getAbsolutePath());
		}
		return false;
	}
}
