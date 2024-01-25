package pages;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Scanner;

import fileOperation.ErrorLogger;
import users.Administrator;
import users.FreeUser;
import users.Hobbyist;
import users.Professional;
import users.User;


public class UsernamePassword {
	static HashMap<String, User> logInfo = new HashMap<String, User>();
	
	/**
	 * This method reads the userData.txt file and parses it to Users according to their User Types.
	 */
	public UsernamePassword() {
		try  {
    		File dataFile = new File("userData.txt");
    		Scanner scanner = new Scanner(dataFile);
    	    while (scanner.hasNext()) {
    	        String dataLine = scanner.nextLine();
				String[] data = dataLine.split(" ");
				if (data[7].equals("FreeUser")) {
					logInfo.put(data[0], new FreeUser(data[0], data[1], data[2], data[3], data[4], data[5], new File(data[6])));
				}
				else if (data[7].equals("Hobbyist")) {
					logInfo.put(data[0], new Hobbyist(data[0], data[1], data[2], data[3], data[4], data[5], new File(data[6])));
				}
				else if (data[7].equals("Professional")) {
					logInfo.put(data[0], new Professional(data[0], data[1], data[2], data[3], data[4], data[5], new File(data[6])));
				}
				else if (data[7].equals("Administrator")) {
					logInfo.put(data[0], new Administrator(data[0], data[1], data[2], data[3], data[4], data[5], new File(data[6])));
				}
				
    	    }
    	    scanner.close();
    	} catch (FileNotFoundException e) {
    		ErrorLogger.errorWrite("userData.txt file not found at the expected location at " + LocalDate.now() + " " + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")) + "\n");
    	}
	}
	
	protected HashMap<String, User> getLogInfo() {
		return logInfo;
	}
}
