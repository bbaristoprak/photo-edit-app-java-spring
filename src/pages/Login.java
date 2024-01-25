package pages;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import fileOperation.ErrorLogger;
import users.User;

public class Login extends JFrame implements ActionListener{
	private JFrame frame = new JFrame();
	private JButton loginButton = new JButton("Login");
	private JButton signUpButton = new JButton("Signup");
	private JTextField usernameInputField = new JTextField();
	private JPasswordField passwordInputField = new JPasswordField();
	private JLabel usernameLabel = new JLabel("Username: ");
	private JLabel passwordLabel = new JLabel("Password: ");
	private JLabel loginMessage = new JLabel();
	private JLabel welcomeLabel = new JLabel("Welcome To NBA Simulator");
	private HashMap<String, User> logInfo = new HashMap<String, User>();
	
	public Login() {
		this.logInfo = UsernamePassword.logInfo;
		
		usernameLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		usernameLabel.setBounds(32,92,86,40);
		frame.setLocation(600,130);
		frame.setLayout(new FlowLayout());
		frame.setResizable(false);
		
		passwordLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		passwordLabel.setBounds(34,152,91,37);
		
		loginMessage.setBounds(123,281,198,35);
		loginMessage.setFont(new Font("Serif", Font.BOLD, 18));
		loginButton.setBackground(new Color(222, 184, 135));
		loginButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		loginButton.setBounds(106, 226, 87, 45);
		loginButton.setFocusable(false); 
		loginButton.addActionListener(this);
		signUpButton.setBackground(new Color(222, 184, 135));
		signUpButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		
		signUpButton.setBounds(209,226,86,45);
		signUpButton.setFocusable(false);
		signUpButton.addActionListener(this);
		
		
		frame.getContentPane().add(usernameLabel);
		frame.getContentPane().add(passwordLabel);
		frame.getContentPane().add(loginMessage);
		frame.getContentPane().add(loginButton);
		frame.getContentPane().add(signUpButton);
		frame.getContentPane().add(loginButton);
		frame.getContentPane().add(signUpButton);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400,400);
		frame.getContentPane().setLayout(null);
		
		usernameInputField.setBounds(135, 94, 140, 37);
		frame.getContentPane().add(usernameInputField);
		usernameInputField.setColumns(10);
		
		passwordInputField.setBounds(135, 153, 140, 37);
		frame.getContentPane().add(passwordInputField);
		
		welcomeLabel.setText("Welcome To NBA Simulator");
		welcomeLabel.setForeground(new Color(255, 99, 71));
		welcomeLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		welcomeLabel.setBounds(106, 10, 238, 60);
		frame.getContentPane().add(welcomeLabel);
		frame.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == loginButton) {
			String username = usernameInputField.getText();
			String password = String.valueOf(passwordInputField.getPassword());
			
			if (logInfo.containsKey(username)) {
				if (logInfo.get(username).getPassword().equals(password)) {
					frame.dispose();
					HomePage homePage = new HomePage(logInfo.get(username));
					try {
						FileWriter logWriter = new FileWriter("logs.txt", true);
						logWriter.write(username + " has been logged in at " + LocalDate.now() + " " + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")) + "\n");
			            logWriter.close();
						
					} catch (IOException e1) {
						ErrorLogger.errorWrite("logs.txt file not found at the expected location at " + LocalDate.now() + " " + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")) + "\n");
					}
				}	
				else {
					loginMessage.setForeground(Color.red);
					loginMessage.setText("Wrong password");
					loginMessage.setVisible(true);
				}
			}
			
			else {
				loginMessage.setForeground(Color.red);
				loginMessage.setText("Invalid Username");
				loginMessage.setVisible(true);
			}
		}
		
		if (e.getSource() == signUpButton) {
			frame.dispose();
			SignUp signUp = new SignUp();
		}
	}
}
