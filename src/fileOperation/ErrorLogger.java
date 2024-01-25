package fileOperation;

import java.io.FileWriter;
import java.io.IOException;

public class ErrorLogger {
	/**
	 * 
	 * @param error
	 * This method takes the error message as argument and writes to the errors.txt file.
	 */
	public static void errorWrite(String error) {
		try {
			FileWriter errorWriter = new FileWriter("errors.txt", true);
			errorWriter.write(error);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
