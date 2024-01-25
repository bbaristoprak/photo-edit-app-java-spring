package pages;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import fileOperation.ErrorLogger;
import users.User;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.Font;
import java.awt.GridLayout;

public class ProfileByAnotherUser {
	private JFrame frame = new JFrame();
	private User currentUser;
	private User viewerUser;
	private JLabel ppDisplayLabel = new JLabel();
	/**
	 * 
	 * @param user
	 * @param viewerUser
	 * 
	 * It takes the user argument to show the user's profile.
	 * And it takes the viewerUser argument to keep the trace of which user is displaying that profile.
	 */
	
	public ProfileByAnotherUser(User user, User viewerUser) {
		this.currentUser = user;
		this.viewerUser = viewerUser;
		frame.setSize(400, 700);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
    	    File publicDataFile = new File("publicPhotos.txt");
    		Scanner publicScanner = new Scanner(publicDataFile);
    	    while (publicScanner.hasNextLine()) {
    	        String publicDataLine = publicScanner.nextLine();
				String[] publicData = publicDataLine.split(" ");
				if (publicData[0].equals(user.getNickname())) {
					files.add(new File(publicData[1]));
				}
						
    	    }
    	    publicScanner.close();
    	    
    	} catch (FileNotFoundException e) {
    		ErrorLogger.errorWrite("publicPhotos.txt file not found at the expected location at " + LocalDate.now() + " " + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")) + "\n");
    	}

        for (final File file : files) {
			if (file.isFile()) {
				try {
					ImageIcon imageIcon = new ImageIcon(file.getAbsolutePath());
					Image image = imageIcon.getImage();
					Image scaledImage = image.getScaledInstance(340, 190 , Image.SCALE_SMOOTH);
					imageIcon = new ImageIcon(scaledImage);
					final ImageIcon displayedPhoto = imageIcon;
					JLabel photoLabel = new JLabel();
					photoLabel.setIcon(imageIcon);
					photoLabel.addMouseListener(new MouseAdapter() {
		        	public void mouseClicked(MouseEvent event) {
		        		frame.dispose();
		        		ClickedPhoto clickedPhoto = new ClickedPhoto(displayedPhoto, ProfileByAnotherUser.this.viewerUser, file.getAbsolutePath());
		        	}
				});
		        photoDisplayPanel.add(photoLabel);
				} catch (Exception e) {
					ErrorLogger.errorWrite("File can not be converted to an image at " + LocalDate.now() + " " + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")) + "\n");
				}
				
			}
		}
	}
}

