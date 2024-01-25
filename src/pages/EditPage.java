package pages;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import users.User;

import java.awt.Font;
import java.awt.Image;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import fileOperation.ErrorLogger;
import filters.Filters;

public class EditPage {
	private JFrame editFrame = new JFrame("Edit Page");
	private JLabel photoDisplayLabel = new JLabel();
	private User thisUser;
	private BufferedImage image;
	private File file;
	private File originalFile;
	private JSlider brightnessSlider = new JSlider();
	private JSlider contrastSlider = new JSlider();
	/**
	 * 
	 * @param user
	 * 
	 * this constructor takes the user as argument to check the user's access to filters by checking the user type.
	 * It displays the filters that the user have access to.
	 */
	
	public EditPage(User user) {
		this.thisUser = user;
		editFrame.setVisible(true);
		editFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		editFrame.setSize(1300, 730);
		editFrame.getContentPane().setLayout(null);
		editFrame.setLocationRelativeTo(null);
		
		JLabel editLabel = new JLabel("Edit Tool");
		editLabel.setFont(new Font("Times New Roman", Font.BOLD, 44));
		editLabel.setBounds(540, 10, 187, 79);
		editFrame.getContentPane().add(editLabel);
		
		photoDisplayLabel.setBounds(10, 83, 978, 572);
		editFrame.getContentPane().add(photoDisplayLabel);
		
		JButton goBackButton = new JButton("Go Back");
		goBackButton.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent e) {
				editFrame.dispose();
				HomePage homePage = new HomePage(thisUser);
				
			}
		});
		goBackButton.setFont(new Font("Tahoma", Font.PLAIN, 24));
		goBackButton.setBounds(10, 10, 144, 45);
		goBackButton.setFocusable(false);
		editFrame.getContentPane().add(goBackButton);
		
		JButton photoUploadButton = new JButton("Upload Photograph");
		photoUploadButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new File("C:\\Users\\topra\\eclipse-workspace\\Barış-Toprak-Programming-Project-GUI\\photos"));
				if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					file = fileChooser.getSelectedFile();
					originalFile = fileChooser.getSelectedFile();
					try {
						image = ImageIO.read(file);
						photoDisplayLabel.setIcon(new ImageIcon(new ImageIcon(file.getAbsolutePath()).getImage().getScaledInstance(photoDisplayLabel.getWidth(), photoDisplayLabel.getHeight(), Image.SCALE_SMOOTH)));
					} catch (IOException e2) {
						ErrorLogger.errorWrite("Selected file could not be read at " + LocalDate.now() + " " + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")) + "\n"); 
					}
				}
			}
		});
		photoUploadButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		photoUploadButton.setBounds(998, 28, 258, 45);
		photoUploadButton.setFocusable(false);
		editFrame.getContentPane().add(photoUploadButton);
		
		JLabel filtersLabel = new JLabel("Available Filters For You:");
		filtersLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		filtersLabel.setBounds(998, 83, 258, 59);
		editFrame.getContentPane().add(filtersLabel);
		
		JButton postPublicButton = new JButton("Post Public");
		postPublicButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (image != null) {
					
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH_mm_ss");
					String currentTime = LocalTime.now().format(formatter);
		            File output = new File("C:\\Users\\topra\\eclipse-workspace\\Barış-Toprak-Programming-Project-GUI\\editedPictures\\publicPhotos"+currentTime+".jpg");
		            try {
		                ImageIO.write(image, "jpg", output);
		                FileWriter logWriter = new FileWriter("logs.txt", true);
			            logWriter.write(thisUser.getNickname() + " posted a public photo at " + LocalDate.now() + " " + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")) + "\n");
			            logWriter.close();
		            } catch (IOException ex) { 
		            	ErrorLogger.errorWrite("logs.txt file not found at the expected location at " + LocalDate.now() + " " + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")) + "\n");
		            }

		            try (FileWriter writer = new FileWriter("publicPhotos.txt", true)) {
		                writer.write(thisUser.getNickname() + " " + output.getAbsolutePath() + "\n");
		            } catch (IOException ex) {
		            	ErrorLogger.errorWrite("publicPhotos.txt file not found at the expected location at " + LocalDate.now() + " " + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")) + "\n");
		            }
		        }
			}
		});
		postPublicButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		postPublicButton.setBounds(998, 563, 134, 92);
		postPublicButton.setFocusable(false);
		editFrame.getContentPane().add(postPublicButton);
		
		JButton postPrivateButton = new JButton("Post Private");
		postPrivateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (image != null) {
				
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH_mm_ss");
					String currentTime = LocalTime.now().format(formatter);
					File output = new File("C:\\Users\\topra\\eclipse-workspace\\Barış-Toprak-Programming-Project-GUI\\privatePhotos\\privatePhotos"+currentTime+".jpg");
					try {
						ImageIO.write(image, "jpg", output);
						FileWriter logWriter = new FileWriter("logs.txt", true);
			            logWriter.write(thisUser.getNickname() + " posted a private photo at " + LocalDate.now() + " " + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")) + "\n");
			            logWriter.close();
					} catch (IOException ex) { 
						ErrorLogger.errorWrite("logs.txt file not found at the expected location at " + LocalDate.now() + " " + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")) + "\n");
					}

					try (FileWriter writer = new FileWriter("privatePhotos.txt", true)) {
						writer.write(thisUser.getNickname() + " " + output.getAbsolutePath() + "\n");
					} catch (IOException ex) {
						ErrorLogger.errorWrite("privatePhotos.txt file not found at the expected location at " + LocalDate.now() + " " + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")) + "\n");
					}
				}
			}
		});
		postPrivateButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		postPrivateButton.setBounds(1132, 563, 134, 92);
		postPrivateButton.setFocusable(false);
		editFrame.getContentPane().add(postPrivateButton);
		
		JButton blurButton1 = new JButton("#1");
		blurButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (photoDisplayLabel.getIcon() != null) {
					try {
						image = Filters.blur(ImageIO.read(file));
						Image scaledImage = image.getScaledInstance(photoDisplayLabel.getWidth(), photoDisplayLabel.getHeight(), Image.SCALE_SMOOTH);
						photoDisplayLabel.setIcon(new ImageIcon(scaledImage));
						FileWriter logWriter = new FileWriter("logs.txt", true);
			            logWriter.write(thisUser.getNickname() + " applied blur at " + LocalDate.now() + " " + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")) + "\n");
			            logWriter.close();
					} catch (IOException e1) {
						ErrorLogger.errorWrite("logs.txt file not found at the expected location at " + LocalDate.now() + " " + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")) + "\n");
					}
				}
			}
		});
		blurButton1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		blurButton1.setBounds(1064, 143, 94, 54);
		blurButton1.setFocusable(false);
		editFrame.getContentPane().add(blurButton1);
		
		JButton sharpenButton = new JButton("Sharpen");
		sharpenButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (photoDisplayLabel.getIcon() != null) {
					try {
						image = Filters.sharpen(ImageIO.read(file));
						Image scaledImage = image.getScaledInstance(photoDisplayLabel.getWidth(), photoDisplayLabel.getHeight(), Image.SCALE_SMOOTH);
						photoDisplayLabel.setIcon(new ImageIcon(scaledImage));
						FileWriter logWriter = new FileWriter("logs.txt", true);
			            logWriter.write(thisUser.getNickname() + " applied sharpen at " + LocalDate.now() + " " + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")) + "\n");
			            logWriter.close();
					} catch (IOException e1) {
						ErrorLogger.errorWrite("logs.txt file not found at the expected location at " + LocalDate.now() + " " + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")) + "\n");
					}
				}
			}
		});
		sharpenButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		sharpenButton.setBounds(1110, 207, 134, 54);
		sharpenButton.setFocusable(false);
		editFrame.getContentPane().add(sharpenButton);
		
		JButton blurButton2 = new JButton("#2");
		blurButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (photoDisplayLabel.getIcon() != null) {
					try {
						image = Filters.heavyBlur(ImageIO.read(file));
						Image scaledImage = image.getScaledInstance(photoDisplayLabel.getWidth(), photoDisplayLabel.getHeight(), Image.SCALE_SMOOTH);
						photoDisplayLabel.setIcon(new ImageIcon(scaledImage));
						FileWriter logWriter = new FileWriter("logs.txt", true);
			            logWriter.write(thisUser.getNickname() + " applied heavy blur at " + LocalDate.now() + " " + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")) + "\n");
			            logWriter.close();
					} catch (IOException e1) {
						ErrorLogger.errorWrite("logs.txt file not found at the expected location at " + LocalDate.now() + " " + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")) + "\n");
					}
				}
			}
		});
		blurButton2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		blurButton2.setBounds(1164, 143, 94, 54);
		blurButton2.setFocusable(false);
		editFrame.getContentPane().add(blurButton2);
		
		JLabel blurLabel = new JLabel("Blur:");
		blurLabel.setFont(new Font("Tahoma", Font.PLAIN, 21));
		blurLabel.setBounds(998, 151, 56, 45);
		editFrame.getContentPane().add(blurLabel);
		
		JLabel sharpenLabel = new JLabel("Sharpen:");
		sharpenLabel.setFont(new Font("Tahoma", Font.PLAIN, 21));
		sharpenLabel.setBounds(998, 215, 88, 35);
		editFrame.getContentPane().add(sharpenLabel);
		
		JLabel brightnessLabel = new JLabel("Brightness:");
		brightnessLabel.setFont(new Font("Tahoma", Font.PLAIN, 21));
		brightnessLabel.setBounds(998, 272, 108, 35);
		brightnessLabel.setVisible(false);
		editFrame.getContentPane().add(brightnessLabel);
		
		JButton grayscaleButton1 = new JButton("#1");
		grayscaleButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (photoDisplayLabel.getIcon() != null) {
					try {
						image = Filters.heavyGrayScale(ImageIO.read(file));
						Image scaledImage = image.getScaledInstance(photoDisplayLabel.getWidth(), photoDisplayLabel.getHeight(), Image.SCALE_SMOOTH);
						photoDisplayLabel.setIcon(new ImageIcon(scaledImage));
						FileWriter logWriter = new FileWriter("logs.txt", true);
			            logWriter.write(thisUser.getNickname() + " applied heavy grayscale at " + LocalDate.now() + " " + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")) + "\n");
			            logWriter.close();
					} catch (IOException e1) {
						ErrorLogger.errorWrite("logs.txt file not found at the expected location at " + LocalDate.now() + " " + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")) + "\n");
					}
				}
			}
		});
		grayscaleButton1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		grayscaleButton1.setBounds(1104, 376, 71, 45);
		grayscaleButton1.setVisible(false);
		grayscaleButton1.setFocusable(false);
		editFrame.getContentPane().add(grayscaleButton1);
		
		JButton grayscaleButton2 = new JButton("#2");
		grayscaleButton2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		grayscaleButton2.setBounds(1185, 376, 71, 45);
		grayscaleButton2.setVisible(false);
		grayscaleButton2.setFocusable(false);
		editFrame.getContentPane().add(grayscaleButton2);
		grayscaleButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (photoDisplayLabel.getIcon() != null) {
					try {
						image = Filters.grayScale(ImageIO.read(file));
						Image scaledImage = image.getScaledInstance(photoDisplayLabel.getWidth(), photoDisplayLabel.getHeight(), Image.SCALE_SMOOTH);
						photoDisplayLabel.setIcon(new ImageIcon(scaledImage));
						FileWriter logWriter = new FileWriter("logs.txt", true);
			            logWriter.write(thisUser.getNickname() + " applied grayscale at " + LocalDate.now() + " " + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")) + "\n");
			            logWriter.close();
					} catch (IOException e1) {
						ErrorLogger.errorWrite("logs.txt file not found at the expected location at " + LocalDate.now() + " " + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")) + "\n");
					}
				}
			}
		});
		
		JLabel grayscaleLabel = new JLabel("GrayScale:");
		grayscaleLabel.setFont(new Font("Tahoma", Font.PLAIN, 21));
		grayscaleLabel.setBounds(998, 386, 108, 35);
		grayscaleLabel.setVisible(false);
		editFrame.getContentPane().add(grayscaleLabel);
		
		JLabel contrastLabel = new JLabel("Contrast:");
		contrastLabel.setFont(new Font("Tahoma", Font.PLAIN, 21));
		contrastLabel.setBounds(998, 328, 108, 35);
		contrastLabel.setVisible(false);
		editFrame.getContentPane().add(contrastLabel);
		brightnessSlider.setMinimum(-20);
		
		brightnessSlider.setBounds(1110, 282, 146, 22);
		brightnessSlider.setVisible(false);
		brightnessSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (photoDisplayLabel.getIcon() != null) {
					int brightnessPercentage = brightnessSlider.getValue();
				try {
					image = Filters.brighten(ImageIO.read(file), brightnessPercentage);
					Image scaledImage = image.getScaledInstance(photoDisplayLabel.getWidth(), photoDisplayLabel.getHeight(), Image.SCALE_SMOOTH);
					photoDisplayLabel.setIcon(new ImageIcon(scaledImage));
					FileWriter logWriter = new FileWriter("logs.txt", true);
		            logWriter.write(thisUser.getNickname() + " applied brightness at " + LocalDate.now() + " " + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")) + "\n");
		            logWriter.close();
				} catch (IOException e1) {
					ErrorLogger.errorWrite("logs.txt file not found at the expected location at " + LocalDate.now() + " " + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")) + "\n");
				}
				}
			}
        });
    
		editFrame.getContentPane().add(brightnessSlider);
		
		JButton edgeDetectButton = new JButton("Apply Edge Detection");
		edgeDetectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (photoDisplayLabel.getIcon() != null) {
					try {
						image = Filters.detectEdges(ImageIO.read(file));
						Image scaledImage = image.getScaledInstance(photoDisplayLabel.getWidth(), photoDisplayLabel.getHeight(), Image.SCALE_SMOOTH);
						photoDisplayLabel.setIcon(new ImageIcon(scaledImage));
						FileWriter logWriter = new FileWriter("logs.txt", true);
			            logWriter.write(thisUser.getNickname() + " applied edge detection at " + LocalDate.now() + " " + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")) + "\n");
			            logWriter.close();
					} catch (IOException e1) {
						ErrorLogger.errorWrite("logs.txt file not found at the expected location at " + LocalDate.now() + " " + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")) + "\n");
					}
				}
			}
		});
		edgeDetectButton.setFont(new Font("Tahoma", Font.PLAIN, 21));
		edgeDetectButton.setBounds(998, 466, 258, 65);
		edgeDetectButton.setVisible(false);
		edgeDetectButton.setFocusable(false);
		editFrame.getContentPane().add(edgeDetectButton);
		
		JButton resetButton = new JButton("Reset");
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (photoDisplayLabel.getIcon() != null) {
					try {
					image = ImageIO.read(originalFile);
					photoDisplayLabel.setIcon(new ImageIcon(new ImageIcon(file.getAbsolutePath()).getImage().getScaledInstance(photoDisplayLabel.getWidth(), photoDisplayLabel.getHeight(), Image.SCALE_SMOOTH)));
				} catch (IOException e2) {
					ErrorLogger.errorWrite("File could not be read at the expected location at " + LocalDate.now() + " " + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")) + "\n");
				}
				}
				
			}
			
		});
		resetButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		resetButton.setBounds(869, 28, 116, 45);
		resetButton.setFocusable(false);
		editFrame.getContentPane().add(resetButton);
		contrastSlider.setMaximum(120);
		
		contrastSlider.setMinimum(-50);
		contrastSlider.setBounds(1110, 335, 146, 22);
		contrastSlider.setVisible(false);
		editFrame.getContentPane().add(contrastSlider);
		contrastSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (photoDisplayLabel.getIcon() != null) {
					int contrastPercentage = contrastSlider.getValue();
				try {
					image = Filters.contrast(ImageIO.read(file), contrastPercentage);
					Image scaledImage = image.getScaledInstance(photoDisplayLabel.getWidth(), photoDisplayLabel.getHeight(), Image.SCALE_SMOOTH);
					photoDisplayLabel.setIcon(new ImageIcon(scaledImage));
					FileWriter logWriter = new FileWriter("logs.txt", true);
		            logWriter.write(thisUser.getNickname() + " applied contrast at " + LocalDate.now() + " " + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")) + "\n");
		            logWriter.close();
				} catch (IOException e1) {
					ErrorLogger.errorWrite("logs.txt file not found at the expected location at " + LocalDate.now() + " " + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")) + "\n");
				}
				}
			}
        });
		
		if (user.getUserType().equals("Hobbyist")) {
			brightnessSlider.setVisible(true);
			brightnessLabel.setVisible(true);
			contrastSlider.setVisible(true);
			contrastLabel.setVisible(true);
		}
		else if (user.getUserType().equals("Professional") || user.getUserType().equals("Administrator")) {
			brightnessSlider.setVisible(true);
			brightnessLabel.setVisible(true);
			contrastSlider.setVisible(true);
			contrastLabel.setVisible(true);
			grayscaleLabel.setVisible(true);
			grayscaleButton1.setVisible(true);
			grayscaleButton2.setVisible(true);
			edgeDetectButton.setVisible(true);
			
		}
		
	}
}
