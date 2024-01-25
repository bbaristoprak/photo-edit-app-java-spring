package users;

import java.awt.Image;
import java.io.File;

public class Hobbyist extends FreeUser{

	public Hobbyist(String nickname, String password, String name, String surname, String email, String age, File profilePhoto) {
		super(nickname, password, name, surname, email, age, profilePhoto);
	}
	
	public String getUserType() {
		return "Hobbyist";
	}

}
