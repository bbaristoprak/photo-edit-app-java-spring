package pages;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.SystemColor;
import javax.swing.JTextField;

import fileOperation.ErrorLogger;
import users.User;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class ProfileSettings {
	private JFrame profileFrame = new JFrame();
	private JLabel profileLabel = new JLabel();
	private JTextField newPasswordField = new JTextField();
	private JButton saveChangesButton = new JButton("Save Changes");
	private JTextField newNameField;
	private JTextField newSurnameField;
	private JTextField newEmailField;
	private JTextField newAgeField;
	private JLabel ppDisplayLabel = new JLabel();
	private User thisUser;
	private JButton changePPButton = new JButton("Change Profile Photo");
	private JButton goBackButton = new JButton("Go Back");
	
	/**
	 * 
	 * @param user
	 * 
	 * It takes the user argument to be able to change the user's data.
	 */
	
	public ProfileSettings(User user) {
		this.thisUser= user;
		profileFrame.getContentPane().setLayout(null);	
		profileFrame.setSize(400, 700);
		
		profileLabel.setForeground(SystemColor.textHighlight);
		profileLabel.setFont(new Font("Tahoma", Font.PLAIN, 29));
		profileLabel.setBounds(162, 10, 140, 46);
		profileLabel.setText(user.getNickname());
		profileLabel.setVisible(true);
		profileFrame.getContentPane().add(profileLabel);
		profileFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		saveChangesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Path path = Paths.get("userData.txt");
					List<String> fileContent = new ArrayList<String>(Files.readAllLines(path, StandardCharsets.UTF_8)); 
					for (int i = 0; i < fileContent.size(); i++) {
	                    String dataLine = fileContent.get(i);
	                    String[] data = dataLine.split(" ");
	                    if (data[0].equals(thisUser.getNickname())) {
	                        fileContent.set(i, thisUser.getNickname() + " " + newPasswordField.getText() + " " + newNameField.getText() + " " + newSurnameField.getText() + " " + newEmailField.getText() + " " + newAgeField.getText() + " " + thisUser.getProfilePhoto().getAbsolutePath()+ " " + data[7]);
	                        break;
	                    }
	                }
	                Files.write(path, fileContent, StandardCharsets.UTF_8);
				} catch (IOException e1) {
					ErrorLogger.errorWrite("userData.txt file not found at the expected location at " + LocalDate.now() + " " + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")) + "\n");
				}
			}
			}
		);
		
		
		saveChangesButton.setBackground(SystemColor.activeCaption);
		saveChangesButton.setFont(new Font("Tahoma", Font.PLAIN, 24));
		saveChangesButton.setBounds(114, 598, 202, 87);
		saveChangesButton.setVisible(true);
		saveChangesButton.setFocusable(false);
		profileFrame.getContentPane().add(saveChangesButton);
		newPasswordField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		newPasswordField.setBounds(239, 309, 140, 31);
		profileFrame.getContentPane().add(newPasswordField);
		newPasswordField.setVisible(true);
		newPasswordField.setText(user.getPassword());
		newPasswordField.setColumns(10);
		
		newNameField = new JTextField();
		newNameField.setText(user.getName());
		newNameField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		newNameField.setBounds(239, 350, 140, 31);
		profileFrame.getContentPane().add(newNameField);
		newNameField.setColumns(10);
		
		newSurnameField = new JTextField();
		newSurnameField.setText(user.getSurname());
		newSurnameField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		newSurnameField.setColumns(10);
		newSurnameField.setBounds(239, 391, 140, 31);
		profileFrame.getContentPane().add(newSurnameField);
		
		newEmailField = new JTextField();
		newEmailField.setText(user.getEmail());
		newEmailField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		newEmailField.setColumns(10);
		newEmailField.setBounds(239, 432, 140, 31);
		profileFrame.getContentPane().add(newEmailField);
		
		newAgeField = new JTextField();
		newAgeField.setText(user.getAge());
		newAgeField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		newAgeField.setColumns(10);
		newAgeField.setBounds(239, 473, 140, 31);
		profileFrame.getContentPane().add(newAgeField);
		
		ppDisplayLabel.setBounds(114, 66, 213, 233);
		ppDisplayLabel.setIcon(new ImageIcon(new ImageIcon(user.getProfilePhoto().getAbsolutePath()).getImage().getScaledInstance(ppDisplayLabel.getWidth(), ppDisplayLabel.getHeight(), Image.SCALE_SMOOTH)));
		profileFrame.getContentPane().add(ppDisplayLabel);
		
		changePPButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		changePPButton.setBounds(10, 518, 184, 57);
		changePPButton.setFocusable(false);
		profileFrame.getContentPane().add(changePPButton);
		
		
		goBackButton.setBackground(SystemColor.activeCaption);
		goBackButton.setFont(new Font("Tahoma", Font.PLAIN, 19));
		goBackButton.setBounds(10, 10, 119, 31);
		profileFrame.getContentPane().add(goBackButton);
		
		JLabel newPassLabel = new JLabel("Enter New Password:");
		newPassLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		newPassLabel.setBounds(26, 309, 155, 31);
		profileFrame.getContentPane().add(newPassLabel);
		
		JLabel newNameLabel = new JLabel("Enter New Name:");
		newNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		newNameLabel.setBounds(26, 350, 155, 31);
		profileFrame.getContentPane().add(newNameLabel);
		
		JLabel newSurnameLabel = new JLabel("Enter New Surname");
		newSurnameLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		newSurnameLabel.setBounds(26, 391, 155, 31);
		profileFrame.getContentPane().add(newSurnameLabel);
		
		JLabel newEmailLabel = new JLabel("Enter New Email:");
		newEmailLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		newEmailLabel.setBounds(26, 432, 155, 31);
		profileFrame.getContentPane().add(newEmailLabel);
		
		JLabel newAgeLabel = new JLabel("Enter New Age:");
		newAgeLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		newAgeLabel.setBounds(26, 473, 155, 31);
		profileFrame.getContentPane().add(newAgeLabel);
		profileFrame.setSize(450,750);
		profileFrame.setVisible(true);
		profileFrame.setResizable(false);
		profileFrame.setLocationRelativeTo(null);
        
        goBackButton.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent e) {
				profileFrame.dispose();
				Profile profile = new Profile(thisUser);
				
			}
		});
        
        changePPButton.addActionListener(new ActionListener() {	
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
					Image img;
					try {
						img = ImageIO.read(file);
						ppDisplayLabel.setIcon(new ImageIcon(new ImageIcon(file.getAbsolutePath()).getImage().getScaledInstance(ppDisplayLabel.getWidth(), ppDisplayLabel.getHeight(), Image.SCALE_SMOOTH)));
						thisUser.setProfilePhoto(file);
						Path path = Paths.get("userData.txt");
						List<String> fileContent = new ArrayList<String>(Files.readAllLines(path, StandardCharsets.UTF_8)); 
						for (int i = 0; i < fileContent.size(); i++) {
		                    String dataLine = fileContent.get(i);
		                    String[] data = dataLine.split(" ");
		                    if (data[0].equals(thisUser.getNickname())) {
		                        fileContent.set(i, thisUser.getNickname() + " " + newPasswordField.getText() + " " + newNameField.getText() + " " + newSurnameField.getText() + " " + newEmailField.getText() + " " + newAgeField.getText() + " " + file.getAbsolutePath() + " " + data[7]);
		                        break;
		                    }
		                }
		                Files.write(path, fileContent, StandardCharsets.UTF_8);
		                try {
							FileWriter logWriter = new FileWriter("logs.txt", true);
							logWriter.write(thisUser.getNickname() + " has changed settings at " + LocalDate.now() + " " + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")) + "\n");
				            logWriter.close();
							
						} catch (IOException e1) {
							ErrorLogger.errorWrite("logs.txt file not found at the expected location at " + LocalDate.now() + " " + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")) + "\n");
						}
					} catch (IOException e1) {
						ErrorLogger.errorWrite("Selected file can not be converted to an image at " + LocalDate.now() + " " + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")) + "\n");
					}
					
				}
				
			}
		});
	}
}
