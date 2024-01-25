package pages;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import users.User;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.SwingConstants;

import fileOperation.ErrorLogger;

public class HomePage implements ActionListener {
	private JFrame frame = new JFrame();
	private JLabel titleLabel = new JLabel();
	private JButton profileButton = new JButton("My Profile");
	private User currentUser;
	private JButton signOutButton = new JButton("Sign Out");
	private JButton editButton = new JButton("Edit Photograph!");
	private JPanel photoDisplayPanel;
    private JScrollPane scrollPane;
    private final JButton searchButton = new JButton("Search Another User");
    private HashMap<String, User> logInfo = new HashMap<String, User>();

    /**
     * 
     * @param user
     * 
     * Takes the user as argument to greet the user by reaching the user's name.
     */
	
	public HomePage(User user) {
		this.logInfo = UsernamePassword.logInfo;
		this.currentUser = user;
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setForeground(SystemColor.textHighlight);
		
		titleLabel.setBounds(329, 0, 541, 112);
		titleLabel.setFont(new Font("Dialog", Font.PLAIN, 34));
		titleLabel.setText("Welcome to PhotoCloud, " + user.getName());
		titleLabel.setBackground(SystemColor.red);
		frame.setBackground(SystemColor.textHighlight);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1280, 720);
		frame.setResizable(false);
		frame.getContentPane().setLayout(new BorderLayout());
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		
		frame.getContentPane().add(titleLabel, BorderLayout.NORTH);
		profileButton.setBackground(new Color(100, 149, 237));
		
		profileButton.setFont(new Font("Tahoma", Font.PLAIN, 24));
		profileButton.setBounds(1094, 11, 162, 78);
		profileButton.addActionListener(this);
		profileButton.setFocusable(false);
		frame.getContentPane().add(profileButton, BorderLayout.EAST);
		signOutButton.setBackground(new Color(100, 149, 237));
		signOutButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		signOutButton.setBounds(10, 17, 140, 43);
		signOutButton.addActionListener(this);
		signOutButton.setFocusable(false);
		
		frame.getContentPane().add(signOutButton, BorderLayout.WEST);
		editButton.setBackground(new Color(0, 204, 255));
		editButton.setFont(new Font("Tahoma", Font.PLAIN, 36));
		editButton.setBounds(403, 573, 387, 71);
		editButton.addActionListener(this);
		editButton.setFocusable(false);
		
		frame.getContentPane().add(editButton, BorderLayout.SOUTH);
		
		photoDisplayPanel = new JPanel(new GridLayout(5, 3, 10, 10));
		photoDisplayPanel.setBackground(new Color(230, 230, 250));
        scrollPane = new JScrollPane(photoDisplayPanel);
        titleLabel.setLabelFor(scrollPane);

        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
        searchButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog(frame, "Enter username:");
                if (input != null && !input.isEmpty()) {
                    if (logInfo.containsKey(input)) {
						ProfileByAnotherUser profileByAnotherUser = new ProfileByAnotherUser(logInfo.get(input), currentUser);
					}
                }
        		
        		
        	}
        });
        searchButton.setBackground(new Color(100, 149, 237));
        searchButton.setFocusable(false);
        searchButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
        scrollPane.setColumnHeaderView(searchButton);
        
        ArrayList<File> files = new ArrayList<>();
        try  {
    		File dataFile = new File("publicPhotos.txt");
    		Scanner scanner = new Scanner(dataFile);
    	    while (scanner.hasNextLine()) {
    	        String dataLine = scanner.nextLine();
				String[] data = dataLine.split(" ");
				files.add(new File(data[1]));		
    	    }
    	    scanner.close();
    	} catch (FileNotFoundException e) {
    		ErrorLogger.errorWrite("publicPhotos.txt file not found at the expected location at " + LocalDate.now() + " " + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")) + "\n");
    	}

        for (final File file : files) {
			if (file.isFile()) {
				try {
					ImageIcon imageIcon = new ImageIcon(file.getAbsolutePath());
					Image image = imageIcon.getImage();
					Image scaledImage = image.getScaledInstance(330, 186, Image.SCALE_SMOOTH);
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
					ErrorLogger.errorWrite("File could not be read at the expected location at " + LocalDate.now() + " " + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")) + "\n");
				}
				
			}
		}
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == profileButton) {
			frame.dispose();
			Profile profile = new Profile(currentUser);
		}
		else if (e.getSource() == signOutButton) {
			frame.dispose();
			Login login = new Login();
		}
		else if (e.getSource() == editButton) {
			frame.dispose();
			EditPage editPage = new EditPage(currentUser);
		}
	}
}
