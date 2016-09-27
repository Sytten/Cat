/**
 * Authors: Emile Fugulin
 * 			Simon Vallieres
 * Date: 27 juin 2016
 * Concrete class using python to control the motors (Adaptor Pattern)
 */

package execution;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class PythonMotorExecutor implements MotorExecutor {

	private boolean result = false;
	private String script="distribution.py";

	public PythonMotorExecutor() {
	}
	public PythonMotorExecutor(String nom) {
		script=nom;
	}

	@Override
	public boolean execute(String argument) {
		try {

			String scriptPath = script;
			File scriptFile = new File(scriptPath);

			if (scriptFile.exists()) {
				Process scriptProcess = new ProcessBuilder("python", scriptPath, argument).start();

				BufferedReader bfr = new BufferedReader(new InputStreamReader(scriptProcess.getInputStream()));
				String line = "";
				while ((line = bfr.readLine()) != null) {
					if(line.contains("Result:")) {
						result = Boolean.parseBoolean(line.split(":")[1]);
					} else {
						System.out.println(line);
					}
				}
				scriptProcess.waitFor();
				
			} else {
				System.out.println("PythonMotorExecutor: missing file " + scriptFile.getAbsolutePath());
			}	

		} catch (Exception e) {
			System.out.println("PythonMotorExecutor: failed to execute the script");
		}

		return result;
	}

}
