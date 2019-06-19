package client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JTextField;

import server.ClientInfo;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ClientRegister {

	public JFrame frame;
	private JPanel panel;
	private JLabel lblName;
	private JTextField cName;
	private JLabel lblNickname;
	private JTextField cNickname;
	private JLabel lblPassword;
	private JPasswordField cPassword;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientRegister window = new ClientRegister();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ClientRegister() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 600, 150);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		lblName = new JLabel("Name:");
		panel.add(lblName);
		
		cName = new JTextField();
		panel.add(cName);
		cName.setColumns(45);
		
		lblNickname = new JLabel("Nickname:");
		panel.add(lblNickname);
		
		cNickname = new JTextField();
		panel.add(cNickname);
		cNickname.setColumns(45);
		
		lblPassword = new JLabel("Password:");
		panel.add(lblPassword);
		
		cPassword = new JPasswordField();
		cPassword.setColumns(45);
		panel.add(cPassword);
		
		btnNewButton = new JButton("Register");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ClientInfo.getConnection(cName.getText(), cNickname.getText(), String.valueOf(cPassword.getPassword()));
				frame.dispose();
				
			}
		});
		panel.add(btnNewButton);

		//I try to incrypto the password, if i failed, just tell Alix, I tried many times.
		//I have successfully pushed this shit
		//Adding another comment
		//One more comment
	}

}
