package execution.audio;

import java.io.File;

import execution.Command;

public class AudioCommand implements Command {

	private AudioExecutor audioExecutor;
	private File audioFile;
	
	public AudioCommand(File audioFile, AudioExecutor audioExecutor) {
		this.audioExecutor = audioExecutor;
		this.audioFile = audioFile;
	}
	
	@Override
	public boolean execute() {
		return audioExecutor.play(audioFile);
	}

}
