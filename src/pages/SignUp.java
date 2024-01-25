package pages;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.awt.Color;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;

import fileOperation.ErrorLogger;
import users.FreeUser;
import users.Hobbyist;
import users.Professional;

import javax.swing.JPasswordField;
import javax.swing.JTextArea;

public class SignUp extends JFrame implements ActionListener{
	private JFrame signupFrame = new JFrame();
	private JTextField usernameTextF;
	private JPasswordField passwordTextF;
	private JTextField nameTextF;
	private JTextField surnameTextF;
	private JTextField emailTextF;
	private JTextField ageTextF;
	private JTextArea warningText;
	private JButton ppUploadButton;
	private JButton freeUserButton;
	private JButton hobbyistButton;
	private JButton professionalButton;
	private JTextArea FreeUserText;
	private JTextArea HobbyistText;
	private JTextArea ProfessionalText;
	private JLabel userTypeLabel;
	private JButton goBackButton = new JButton("Go Back");
	private JLabel signupLabel = new JLabel("Signup To PhotoCloud");
	private JLabel photoDisplayLabel = new JLabel();
	private File file = new File("defaultPP.jpg");
	
	public SignUp(){
		signupFrame.setSize(800,600);
		signupFrame.getContentPane().setLayout(null);
		signupFrame.setVisible(true);
		signupFrame.setResizable(false);
		signupFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		signupFrame.setLocationRelativeTo(null);
		JLabel signupLabel = new JLabel("Signup To PhotoCloud");
		signupLabel.setForeground(new Color(233, 150, 122));
		signupLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		signupLabel.setBounds(87, 0, 245, 70);
		signupFrame.getContentPane().add(signupLabel);
		
		JLabel usernameLabel = new JLabel("Username: ");
		usernameLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		usernameLabel.setBounds(10, 68, 86, 25);
		signupFrame.getContentPane().add(usernameLabel);
		
		JLabel passwordLabel = new JLabel("Password: ");
		passwordLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		passwordLabel.setBounds(10, 103, 86, 25);
		signupFrame.getContentPane().add(passwordLabel);
		
		JLabel nameLabel = new JLabel("Name:");
		nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		nameLabel.setBounds(10, 138, 86, 25);
		signupFrame.getContentPane().add(nameLabel);
		
		JLabel surnameLabel = new JLabel("Surname:");
		surnameLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		surnameLabel.setBounds(10, 173, 86, 31);
		signupFrame.getContentPane().add(surnameLabel);
		
		JLabel mailLabel = new JLabel("E-mail Address: ");
		mailLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		mailLabel.setBounds(10, 214, 94, 31);
		signupFrame.getContentPane().add(mailLabel);
		
		JLabel ageLabel = new JLabel("Age:");
		ageLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		ageLabel.setBounds(10, 255, 86, 31);
		signupFrame.getContentPane().add(ageLabel);
		
		JLabel profilePhotoLabel = new JLabel("Profile Photo (Optional):");
		profilePhotoLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		profilePhotoLabel.setBounds(10, 296, 145, 31);
		signupFrame.getContentPane().add(profilePhotoLabel);
		
		usernameTextF = new JTextField();
		usernameTextF.setBounds(114, 69, 131, 25);
		signupFrame.getContentPane().add(usernameTextF);
		usernameTextF.setColumns(10);
		
		passwordTextF = new JPasswordField();
		passwordTextF.setBounds(114, 104, 131, 25);
		signupFrame.getContentPane().add(passwordTextF);
		
		nameTextF = new JTextField();
		nameTextF.setColumns(10);
		nameTextF.setBounds(114, 139, 131, 25);
		signupFrame.getContentPane().add(nameTextF);
		
		surnameTextF = new JTextField();
		surnameTextF.setColumns(10);
		surnameTextF.setBounds(114, 177, 131, 25);
		signupFrame.getContentPane().add(surnameTextF);
		
		emailTextF = new JTextField();
		emailTextF.setColumns(10);
		emailTextF.setBounds(114, 214, 131, 25);
		signupFrame.getContentPane().add(emailTextF);
		
		ageTextF = new JTextField();
		ageTextF.setColumns(10);
		ageTextF.setBounds(114, 260, 131, 25);
		signupFrame.getContentPane().add(ageTextF);
		
		ppUploadButton = new JButton("Click Here to Upload Profile Photo");
		ppUploadButton.setBackground(new Color(240, 255, 240));
		ppUploadButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		ppUploadButton.setBounds(10, 322, 206, 31);
		ppUploadButton.addActionListener(this);
		signupFrame.getContentPane().add(ppUploadButton);
		
		warningText = new JTextArea();
		warningText.setEditable(false);
		warningText.setFont(new Font("Monospaced", Font.PLAIN, 12));
		warningText.setText("Please make sure \nthat you are\nOLDER than 15 \nand your name,\npassword must be \nLONGER than \n4 chars.\nEnter integers \nfor age");
		warningText.setBounds(254, 70, 122, 159);
		signupFrame.getContentPane().add(warningText);
		
		freeUserButton = new JButton("Free User");
		freeUserButton.setBackground(new Color(188, 143, 143));
		freeUserButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		freeUserButton.setBounds(386, 71, 146, 86);
		freeUserButton.addActionListener(this);
		freeUserButton.setFocusable(false);
		signupFrame.getContentPane().add(freeUserButton);
		
		
		hobbyistButton = new JButton("Hobbyist");
		hobbyistButton.setBackground(new Color(188, 143, 143));
		hobbyistButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		hobbyistButton.setBounds(386, 167, 146, 86);
		hobbyistButton.addActionListener(this);
		hobbyistButton.setFocusable(false);
		signupFrame.getContentPane().add(hobbyistButton);
		
		professionalButton = new JButton("Professional");
		professionalButton.setBackground(new Color(188, 143, 143));
		professionalButton.setFont(new Font("Tahoma", Font.BOLD, 17));
		professionalButton.setBounds(386, 262, 146, 86);
		professionalButton.addActionListener(this);
		professionalButton.setFocusable(false);
		signupFrame.getContentPane().add(professionalButton);
		
		FreeUserText = new JTextArea();
		FreeUserText.setEditable(false);
		FreeUserText.setText("Free user can use \nBlurring and Sharpening \neffects to images.");
		FreeUserText.setFont(new Font("Monospaced", Font.BOLD, 13));
		FreeUserText.setBounds(542, 74, 230, 86);
		signupFrame.getContentPane().add(FreeUserText);
		
		HobbyistText = new JTextArea();
		HobbyistText.setEditable(false);
		HobbyistText.setText("Hobbyist can change the \nbrightness and contrast \nadditionally to \nFree User Tier");
		HobbyistText.setFont(new Font("Monospaced", Font.BOLD, 13));
		HobbyistText.setBounds(541, 167, 231, 86);
		signupFrame.getContentPane().add(HobbyistText);
		
		ProfessionalText = new JTextArea();
		ProfessionalText.setEditable(false);
		ProfessionalText.setText("Professional User can apply \nGrayscale and Edge Detection \nfilters additionally to \nHobbyist Tier");
		ProfessionalText.setFont(new Font("Monospaced", Font.BOLD, 13));
		ProfessionalText.setBounds(542, 262, 231, 86);
		signupFrame.getContentPane().add(ProfessionalText);
		
		userTypeLabel = new JLabel("Please Select a User Type For Your Need");
		userTypeLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		userTypeLabel.setBounds(394, 0, 366, 70);
		signupFrame.getContentPane().add(userTypeLabel);
		
		JTextArea accountText = new JTextArea("Your account will \nbe created after \nyou select your \nUser Type");
		accountText.setEditable(false);
		accountText.setFont(new Font("Monospaced", Font.PLAIN, 12));
		accountText.setBounds(255, 239, 122, 70);
		signupFrame.getContentPane().add(accountText);
		goBackButton.setBackground(new Color(169, 169, 169));
		
		goBackButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		goBackButton.setBounds(254, 322, 122, 49);
		goBackButton.addActionListener(this);
		signupFrame.getContentPane().add(goBackButton);
		warningText.setVisible(false);
		ppUploadButton.setFocusable(false);
		
		photoDisplayLabel.setBounds(20, 363, 196, 144);
		signupFrame.getContentPane().add(photoDisplayLabel);
		
	}
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == freeUserButton) {
			if (usernameTextF.getText().length() >= 4 && passwordTextF.getPassword().length >= 4 && nameTextF.getText().length() >= 2 && surnameTextF.getText().length() >= 2 && ageTextF.getText().matches("\\d+") && Integer.parseInt(ageTextF.getText()) >= 15 && emailTextF.getText().length() >= 8 && emailTextF.getText().contains("@")) {			
				try {
					FileWriter userDataWriter = new FileWriter("userData.txt", true);
		            FileWriter logWriter = new FileWriter("logs.txt", true);
		            String passwordString = new String(passwordTextF.getPassword());
		            userDataWriter.write(usernameTextF.getText() + " " + passwordString + " " + nameTextF.getText() + " " + surnameTextF.getText() + " " + emailTextF.getText() + " " + ageTextF.getText()+ " " + file.getAbsolutePath() +" FreeUser" + "\n");
		            userDataWriter.close();
		            logWriter.write("New FreeUser user data with nickname "+ usernameTextF.getText() +" has been added at " + LocalDate.now() + " " + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")) + "\n");	            
		            logWriter.close();
		            signupFrame.dispose();
		            UsernamePassword usernamePassword = new UsernamePassword();
		            FreeUser user = new FreeUser(usernameTextF.getText(), passwordString, nameTextF.getText(), surnameTextF.getText(), emailTextF.getText(), ageTextF.getText(), file);
		            Login login = new Login();
		          
		        } catch (IOException exception) {
		        	ErrorLogger.errorWrite("userData.txt file not found at the expected location at " + LocalDate.now() + " " + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")) + "\n");
		        }
			}
			else {
				warningText.setVisible(true);
			}
		}
		else if (e.getSource() == hobbyistButton) {
			if (usernameTextF.getText().length() >= 3 && passwordTextF.getPassword().length >= 4 && nameTextF.getText().length() >= 2 && surnameTextF.getText().length() >= 2 && ageTextF.getText().matches("\\d+") && Integer.parseInt(ageTextF.getText()) >= 15 && emailTextF.getText().length() >= 8 && emailTextF.getText().contains("@")) {			
				try {
					FileWriter userDataWriter = new FileWriter("userData.txt", true);
		            FileWriter logWriter = new FileWriter("logs.txt", true);
		            String passwordString = new String(passwordTextF.getPassword());
		            userDataWriter.write(usernameTextF.getText() + " " + passwordString + " " + nameTextF.getText() + " " + surnameTextF.getText() + " " + emailTextF.getText() + " " + ageTextF.getText()+ " " + file.getAbsolutePath()  + " Hobbyist" + "\n");
		            userDataWriter.close();
		            logWriter.write("New Hobbyist user data with nickname "+ usernameTextF.getText() +" has been added at " + LocalDate.now() + " " + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")) + "\n");
		            System.out.println("Data appended to userData.txt file");	            
		            logWriter.close();
		            signupFrame.dispose();
		            UsernamePassword usernamePassword = new UsernamePassword();
		            Hobbyist user = new Hobbyist(usernameTextF.getText(), passwordString, nameTextF.getText(), surnameTextF.getText(), emailTextF.getText(), ageTextF.getText(), file);
		            Login login = new Login();
		          
		        } catch (IOException exception) {
		        	ErrorLogger.errorWrite("userData.txt file not found at the expected location at " + LocalDate.now() + " " + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")) + "\n");
		        }
			}
			else {
				warningText.setVisible(true);
			}
		}
		
		else if (e.getSource() == professionalButton) {
			if (usernameTextF.getText().length() >= 4 && passwordTextF.getPassword().length >= 4 && nameTextF.getText().length() >= 2 && surnameTextF.getText().length() >= 2 && ageTextF.getText().matches("\\d+") && Integer.parseInt(ageTextF.getText()) >= 15 && emailTextF.getText().length() >= 8 && emailTextF.getText().contains("@")) {			
				try {
					FileWriter userDataWriter = new FileWriter("userData.txt", true);
		            FileWriter logWriter = new FileWriter("logs.txt", true);
		            String passwordString = new String(passwordTextF.getPassword());
		            userDataWriter.write(usernameTextF.getText() + " " + passwordString + " " + nameTextF.getText() + " " + surnameTextF.getText() + " " + emailTextF.getText() + " " + ageTextF.getText()+ " " + file.getAbsolutePath()  +" Professional" + "\n");
		            userDataWriter.close();
		            logWriter.write("New Professional User data with  nickname "+ usernameTextF.getText() +" has been added at " + LocalDate.now() + " " + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")) + "\n");
		            logWriter.close();
		            signupFrame.dispose();
		            UsernamePassword usernamePassword = new UsernamePassword();
		            Professional user = new Professional(usernameTextF.getText(), passwordString, nameTextF.getText(), surnameTextF.getText(), emailTextF.getText(), ageTextF.getText(), file);
		            Login login = new Login();
		          
		        } catch (IOException exception) {
		        	ErrorLogger.errorWrite("userData.txt file not found at the expected location at " + LocalDate.now() + " " + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")) + "\n");
		        }
			}
			else {
				warningText.setVisible(true);
			}
		}
		else if (e.getSource() == goBackButton) {
			signupFrame.dispose();
			UsernamePassword usernamePassword = new UsernamePassword();
			Login login = new Login();
		}
		
		else if (e.getSource() == ppUploadButton ){
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setCurrentDirectory(new File("C:\\Users\\topra\\OneDrive\\Masaüstü"));
			if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				file = fileChooser.getSelectedFile();
				Image img;
				try {
					img = ImageIO.read(file);
					photoDisplayLabel.setIcon(new ImageIcon(new ImageIcon(file.getAbsolutePath()).getImage().getScaledInstance(photoDisplayLabel.getWidth(), photoDisplayLabel.getHeight(), Image.SCALE_SMOOTH)));
				} catch (IOException e1) {
					ErrorLogger.errorWrite("Selected file can not be converted to an image at " + LocalDate.now() + " " + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")) + "\n");
				}
				
			}
			
		}
	}
}
