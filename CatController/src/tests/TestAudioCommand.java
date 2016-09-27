package tests;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import execution.audio.AudioCommand;
import execution.audio.AudioExecutor;
import execution.audio.OmxPlayerAudioExecutor;

public class TestAudioCommand {

	@Mock
	OmxPlayerAudioExecutor player;

	@Rule
	public MockitoRule mockitoRule = MockitoJUnit.rule();

	@InjectMocks
	AudioCommand audio;
	AudioExecutor executor;

	@Test
	public void TestAudio() {

		AudioExecutor audioExecutor = null;

		Path currentRelativePath = Paths.get("");
		String path = currentRelativePath.toAbsolutePath().toString();
		File audioFile = new File(path + File.separator + "ExternalFiles" + File.separator + "sonTest.mp3");

		AudioCommand command = new AudioCommand(audioFile, audioExecutor);

		Mockito.when(player.play(Mockito.any(File.class))).thenReturn(true);

		audio = new AudioCommand(audioFile, executor);

		assertTrue(player.play(audioFile));
		assertNotNull(command);

	}

}
