package users;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class FreeUser implements User{
	private String nickname;
	private String password;
	private String name;
	private String surname;
	private String emailAdress;
	private String age;
	private File profilePhoto;
	private ArrayList<Image> images;
	
	public FreeUser(String nickname, String password, String name, String surname, String email, String age, File profilePhoto) {
		this.nickname = nickname;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.emailAdress = email;
		this.age = age;
		this.profilePhoto = profilePhoto;
	}

	public String getNickname() {
		return this.nickname;
	}

	public String getPassword() {
		return this.password;
	}

	public String getName() {
		return this.name;
	}

	public String getSurname() {
		return this.surname;
	}

	public String getEmail() {
		return this.emailAdress;
	}

	public String getAge() {
		return this.age;
	}
	
	public File getProfilePhoto() {
		return this.profilePhoto;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public void setEmail(String email) {
		this.emailAdress = email;
	}

	public void setAge(String age) {
		this.age = age;
	}
	
	public void setProfilePhoto(File img) {
		this.profilePhoto = img.getAbsoluteFile();
	}

	public String getUserType() {
		return "FreeUser";
	}
	
}
