package users;

import java.io.File;

public class Professional extends Hobbyist{

	public Professional(String nickname, String password, String name, String surname, String email, String age, File profilePhoto) {
		super(nickname, password, name, surname, email, age, profilePhoto);
		
	}

	public String getUserType() {
		return "Professional";
	}
}
