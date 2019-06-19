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
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
				
				String hash = "";
				String code = String.copyValueOf(cPassword.getPassword());
				try {
					hash = hashPassword(code);
				} catch (NoSuchAlgorithmException e1) {
					e1.printStackTrace();
				} finally {
					ClientInfo.getConnection(cName.getText(), cNickname.getText(), hash);
					frame.dispose();
				}
				

			}
		});
		panel.add(btnNewButton);

	}

	public static String hashPassword(String password) throws NoSuchAlgorithmException {

		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(password.getBytes());
		byte[] b = md.digest();
		StringBuffer sb = new StringBuffer();
		for (byte b1 : b) {
			sb.append(Integer.toHexString(b1 & 0xff).toString());
		}
		//System.out.println(sb.toString());
	
	return sb.toString();
	}

}