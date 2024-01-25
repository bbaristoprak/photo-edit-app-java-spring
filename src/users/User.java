package users;

import java.io.File;

public interface User {
	public String getNickname();
	public String getPassword();
	public String getName();
	public String getSurname();
	public String getEmail();
	public String getAge();
	public void setPassword(String password);
	public void setName(String name);
	public void setSurname(String surname);
	public void setEmail(String email);
	public void setAge(String age);
	public void setProfilePhoto(File file);
	public File getProfilePhoto();
	public String getUserType();
}
