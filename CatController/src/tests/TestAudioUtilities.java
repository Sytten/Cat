package tests;

import static org.junit.Assert.assertNotEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;

import main.AudioUtilities;

public class TestAudioUtilities {

	@Test
	public void TestConversion() {

		String audioData = "";
		Path currentRelativePath = Paths.get("");
		String path = currentRelativePath.toAbsolutePath().toString();
		File audioFile = new File(path + File.separator + "ExternalFiles" + File.separator + "AudioBase64.txt");

		try (BufferedReader br = new BufferedReader(new FileReader(audioFile))) {
			String line;
			while ((line = br.readLine()) != null) {
				audioData += line;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		assertNotEquals(audioData, "");
		File musicFile = null;
		try {
			musicFile = AudioUtilities.convertAudioStringToFile(audioData);
		} catch (IOException e) {
			e.printStackTrace();
		}

		assertNotEquals(musicFile, null);

		try {

			File file = new File(System.getProperty("user.home") + File.separator + "VoiceData.ogg");

			if (file.delete()) {
				System.out.println(file.getName() + " is deleted!");
			} else {
				System.out.println("Delete operation is failed.");
			}

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

}
