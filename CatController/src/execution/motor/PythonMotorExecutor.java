
package execution.motor;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class PythonMotorExecutor implements MotorExecutor {

	private boolean result = false;
	private String script="distribution.py";

	public PythonMotorExecutor() {}
	
	public PythonMotorExecutor(String nom) {
		script=nom;
	}

	@Override
	public boolean execute(String type, String time) {
		try {
			File scriptFile = new File(script);

			if (scriptFile.exists()) {
				exPythonProcess(type, time);
				
			} else {
				System.out.println("PythonMotorExecutor: missing file " + scriptFile.getAbsolutePath());
			}	

		} catch (Exception e) {
			System.out.println("PythonMotorExecutor: failed to execute the script");
		}

		return result;
	}

	private void exPythonProcess(String type, String time) throws IOException, InterruptedException{
		Process pythonProcess = new ProcessBuilder("python", script, type, time).start();
		showPythonResult(pythonProcess); 
		pythonProcess.waitFor();
	}
	
	private void showPythonResult(Process pythonProcess) throws IOException{
		BufferedReader bfr = new BufferedReader(new InputStreamReader(pythonProcess.getInputStream()));
		String line = "";
		while ((line = bfr.readLine()) != null) {
			if(line.contains("Result:")) {
				result = Boolean.parseBoolean(line.split(":")[1]);
			} else {
				System.out.println(line);
			}
		}
		
	}
}
