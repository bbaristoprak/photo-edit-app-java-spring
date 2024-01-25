package pages;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import fileOperation.ErrorLogger;
import users.User;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.GridLayout;

public class Profile {
	private JFrame frame = new JFrame();
	private User currentUser;
	private JLabel ppDisplayLabel = new JLabel();
	/**
	 * 
	 * @param user
	 * 
	 * Takes the user as argument to display user's photographs.
	 */
	public Profile(User user) {
		this.currentUser = user;
		frame.setSize(400, 700);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		
		ppDisplayLabel.setBounds(106, 71, 174, 174);
		ppDisplayLabel.setIcon(new ImageIcon(new ImageIcon(user.getProfilePhoto().getAbsolutePath()).getImage().getScaledInstance(ppDisplayLabel.getWidth(), ppDisplayLabel.getHeight(), Image.SCALE_SMOOTH)));
		frame.getContentPane().add(ppDisplayLabel);
		
		JLabel profileLabel = new JLabel(currentUser.getNickname());
		profileLabel.setFont(new Font("Tahoma", Font.PLAIN, 39));
		profileLabel.setBounds(106, 10, 270, 47);
		frame.getContentPane().add(profileLabel);
		
		JPanel photoDisplayPanel = new JPanel();
		photoDisplayPanel.setBounds(10, 255, 366, 398);
		frame.getContentPane().add(photoDisplayPanel);
		photoDisplayPanel.setLayout(new GridLayout(0, 1, 10, 10));
		
		JScrollPane scrollPane = new JScrollPane(photoDisplayPanel);
		scrollPane.setBounds(10, 255, 366, 398);
		frame.getContentPane().add(scrollPane);
		

		
		
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
						
    	    }
    	    
    	    File publicDataFile = new File("publicPhotos.txt");
    		Scanner publicScanner = new Scanner(publicDataFile);
    	    while (publicScanner.hasNextLine()) {
    	        String publicDataLine = publicScanner.nextLine();
				String[] publicData = publicDataLine.split(" ");
				if (publicData[0].equals(user.getNickname())) {
					files.add(new File(publicData[1]));
				}
						
    	    }
    	    scanner.close();
    	    publicScanner.close();
    	    
    	} catch (FileNotFoundException e) {
    		ErrorLogger.errorWrite("privatePhotos.txt/publicPhotos.txt file not found at the expected location at " + LocalDate.now() + " " + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")) + "\n");
    	}

        for (final File file : files) {
			if (file.isFile()) {
				try {
					ImageIcon imageIcon = new ImageIcon(file.getAbsolutePath());
					Image image = imageIcon.getImage();
					Image scaledImage = image.getScaledInstance(350, 190 , Image.SCALE_SMOOTH);
					imageIcon = new ImageIcon(scaledImage);
					final ImageIcon displayedPhoto = imageIcon;
					JLabel photoLabel = new JLabel();
					photoLabel.setIcon(imageIcon);
					photoLabel.addMouseListener(new MouseAdapter() {
		        	public void mouseClicked(MouseEvent event) {
		        		ClickedPhoto clickedPhoto = new ClickedPhoto(displayedPhoto, currentUser, file.getAbsolutePath());
		        	}
				});
		        photoDisplayPanel.add(photoLabel);
				} catch (Exception e) {
					ErrorLogger.errorWrite("File can not be converted to an image at " + LocalDate.now() + " " + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")) + "\n");
				}
				
			}
		}
	
        
		
		
		JButton settingsButton = new JButton("Settings");
		settingsButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		settingsButton.setFocusable(false);
		settingsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				ProfileSettings profileSettings = new ProfileSettings(currentUser);
			}
		});
		settingsButton.setBounds(290, 67, 86, 47);
		frame.getContentPane().add(settingsButton);
		
		JButton goBackButton = new JButton("Go Back");
		goBackButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		goBackButton.setFocusable(false);
		goBackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				HomePage homePage = new HomePage(currentUser);
			}
		});
		goBackButton.setBounds(10, 67, 86, 47);
		frame.getContentPane().add(goBackButton);
		
	}
	
	
}

