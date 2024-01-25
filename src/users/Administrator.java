package users;

import java.io.File;

public class Administrator extends Professional{

	public Administrator(String nickname, String password, String name, String surname, String email, String age,
			File profilePhoto) {
		super(nickname, password, name, surname, email, age, profilePhoto);
	}
	
	public String getUserType() {
		return "Administrator";
	}
}
