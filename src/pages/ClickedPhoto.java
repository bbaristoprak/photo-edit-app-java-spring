package pages;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import fileOperation.ErrorLogger;
import fileOperation.FileOperations;
import users.User;

public class ClickedPhoto {
    private JFrame frame = new JFrame();
    private JTextArea commentTextArea = new JTextArea();
    private JTextField commentTextField;
    private User currentUser;
    private HashMap<String, User> logInfo = new HashMap<String, User>();
    private String uploaderName;
    private String thisFilePath;

    /**
     * 
     * @param imageIcon
     * @param user
     * @param filePath
     * 
     * ClickedPhoto takes these 3 parameters to display the desired photo, the photo owner
     * and the file path to compare if the photo is uploaded by the current useri
     * if yes, it also displays the delete button
     * 
     */
    public ClickedPhoto(ImageIcon imageIcon, User user, String filePath) {
    	this.logInfo = UsernamePassword.logInfo;
    	this.currentUser = user;
    	this.thisFilePath = filePath;
        frame.getContentPane().setBackground(new Color(169, 169, 169));
        frame.setSize(1280, 720);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setLayout(null);

        JScrollPane commentScrollPane = new JScrollPane();
        commentScrollPane.setBounds(992, 64, 264, 470);
        frame.getContentPane().add(commentScrollPane);
        commentScrollPane.setPreferredSize(new Dimension(600, 200));
        commentTextArea.setFont(new Font("Arial", Font.PLAIN, 16));
        
        commentScrollPane.setViewportView(commentTextArea);

        JLabel photoDisplayLabel = new JLabel();
        Image image = imageIcon.getImage();
        photoDisplayLabel.setBounds(22, 64, 960, 546);
        frame.getContentPane().add(photoDisplayLabel);
        Image scaledImage = image.getScaledInstance(photoDisplayLabel.getWidth(), photoDisplayLabel.getHeight(),
                Image.SCALE_SMOOTH);
        photoDisplayLabel.setIcon(new ImageIcon(scaledImage));

        JButton submitButton = new JButton("Submit Your Comment");
        submitButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
        submitButton.setBounds(992, 620, 264, 44);
        submitButton.setFocusable(false);
        frame.getContentPane().add(submitButton);

        commentTextField = new JTextField();
        commentTextField.setFont(new Font("Arial", Font.PLAIN, 16));
        commentTextField.setBounds(992, 545, 264, 65);
        frame.getContentPane().add(commentTextField);
        commentTextField.setColumns(10);
        
        try  {
    		File dataFile = new File("commentData.txt");
    		Scanner scanner = new Scanner(dataFile);
    	    while (scanner.hasNext()) {
    	        String dataLine = scanner.nextLine();
				String[] data = dataLine.split(" ");
				if (data[2].equals(filePath)) {
					commentTextArea.append(" " + data[0] + ": " + data[1].replace("é", " ") + "\n");
				}
			
				
    	    }
    	    scanner.close();
    	} catch (FileNotFoundException e) {
    		ErrorLogger.errorWrite("commentData.txt file not found at the expected location at " + LocalDate.now() + " " + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")) + "\n");
    	}
	
        JButton deleteButton = new JButton("Delete Photo");
        
        deleteButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
        deleteButton.setBounds(814, 10, 168, 44);
        deleteButton.setFocusable(false);
        deleteButton.setVisible(false);
        
        frame.getContentPane().add(deleteButton);
        
        if (currentUser.getUserType().equals("Administrator")) {
			deleteButton.setVisible(true);
		}
        ArrayList<File> files = new ArrayList<>();
        try  {
    		File dataFile = new File("privatePhotos.txt");
    		Scanner scanner = new Scanner(dataFile);
    	    while (scanner.hasNextLine()) {
    	        String dataLine = scanner.nextLine();
				String[] data = dataLine.split(" ");
				if (data[0].equals(user.getNickname())) {
					files.add(new File(data[1]));
				}
				if (data[1].equals(filePath)) {
					uploaderName = data[0];
				}
				
						
    	    }
    	    
    	    File publicDataFile = new File("publicPhotos.txt");
    		Scanner publicScanner = new Scanner(publicDataFile);
    	    while (publicScanner.hasNextLine()) {
    	        String publicDataLine = publicScanner.nextLine();
				String[] publicData = publicDataLine.split(" ");
				if (publicData[0].equals(user.getNickname())) {
					files.add(new File(publicData[1]));
				}
				if (publicData[1].equals(filePath)) {
					uploaderName = publicData[0];
				}
						
    	    }
    	    scanner.close();
    	    publicScanner.close();
    	    
    	} catch (FileNotFoundException e) {
    	    ErrorLogger.errorWrite("publicPhotos.txt/privatePhotos.txt files not found at the expected location at " + LocalDate.now() + " " + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")) + "\n");
    	}
        
        if (currentUser.getNickname().equals(uploaderName)) {
			deleteButton.setVisible(true);
		}
        
        JLabel ownerLabel = new JLabel("Uploaded By " + uploaderName);
        ownerLabel.setFont(new Font("Tahoma", Font.PLAIN, 19));
        ownerLabel.setBounds(22, 20, 297, 34);
        ownerLabel.addMouseListener(new MouseAdapter() {
        	public void mouseClicked(MouseEvent event) {
        		frame.dispose();
        		ProfileByAnotherUser profileByAnotherUser = new ProfileByAnotherUser(logInfo.get(uploaderName), currentUser);
        	}
		});
        frame.getContentPane().add(ownerLabel);
        
        deleteButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if (uploaderName != null) {
        			try {
						FileWriter logWriter = new FileWriter("logs.txt", true);
						logWriter.write(currentUser.getNickname() + " has deleted a photo at " + LocalDate.now() + " " + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")) + "\n");
			            logWriter.close();
						
					} catch (IOException e1) {
						ErrorLogger.errorWrite("logs.txt file not found at the expected location at " + LocalDate.now() + " " + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")) + "\n");
					}
					FileOperations.removeLinesFromFile("publicPhotos.txt", uploaderName + " " + thisFilePath);
					FileOperations.removeLinesFromFile("privatePhotos.txt", uploaderName + " " + thisFilePath);
				}
        	}
        });
        
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String comment = commentTextField.getText();
                commentTextArea.append(" " + currentUser.getNickname() + ": " + comment + "\n");
                try {
					FileWriter commentWriter = new FileWriter("commentData.txt", true);
					commentWriter.write(currentUser.getNickname() + " " + comment.replace(" ", "é") + " " + thisFilePath +"\n");
					commentWriter.close();
					try {
						FileWriter logWriter = new FileWriter("logs.txt", true);
						logWriter.write(currentUser.getNickname() + " has made a comment at " + LocalDate.now() + " " + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")) + "\n");
			            logWriter.close();
						
					} catch (IOException e1) {
						ErrorLogger.errorWrite("logs.txt file not found at the expected location at " + LocalDate.now() + " " + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")) + "\n");
					}
				} catch (IOException e1) {
					ErrorLogger.errorWrite("commentData.txt file not found at the expected location at " + LocalDate.now() + " " + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")) + "\n");
				}
                commentTextField.setText("");
            }
        });
    }
}
