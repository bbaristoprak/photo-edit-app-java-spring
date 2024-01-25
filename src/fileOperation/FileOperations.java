package fileOperation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class FileOperations {
	/**
	 * 
	 * @param filePath
	 * @param searchString
	 * 
	 * This method takes the filePath as an argument to see which file it's operating and
	 * Takes the searchString argument to find which string to delete.
	 * With the given parameters the method finds the string in the desired file and deletes it.
	 */
	public static void removeLinesFromFile(String filePath, String searchString) {
        try {
            File inputFile = new File(filePath);
            File tempFile = new File("tempPhotoFile.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String line;

            while ((line = reader.readLine()) != null) {
                if (!line.contains(searchString)) {
                    writer.write(line);
                    writer.newLine();
                }
            }

            writer.close();
            reader.close();

            if (inputFile.delete()) {
                tempFile.renameTo(inputFile);
            } else {
            	ErrorLogger.errorWrite("Can not delete the file " + LocalDate.now() + " " + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")) + "\n");
            }
        }
        catch (IOException e) {
        	ErrorLogger.errorWrite("tempPhotoFile.txt file not found at the expected location at " + LocalDate.now() + " " + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")) + "\n");
		}
            
	}
}
