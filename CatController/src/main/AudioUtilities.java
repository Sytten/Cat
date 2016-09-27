package main;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Base64;

public class AudioUtilities {


	public static File convertAudioStringToFile(String audioData) throws IOException {
		// format of audio strip : "[metadata],[base64data]"
		String strippedAudioData = audioData.split(",")[1];
		byte[] decodedAudioData = Base64.getDecoder().decode(strippedAudioData);
		File targetFile = new File(System.getProperty("user.home") + File.separator + "VoiceData.ogg");
		OutputStream outStream = new FileOutputStream(targetFile);
		outStream.write(decodedAudioData);

		outStream.close();

		return targetFile;
	}
}
