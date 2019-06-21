package client;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import javax.swing.JTextField;

import server.ClientInfo;

import java.awt.Insets;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.security.NoSuchAlgorithmException;
import java.awt.Toolkit;

public class ClientLogin {

	public JFrame frmLogin;
	public JTextField loginNickname;
	public JPasswordField loginPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientLogin window = new ClientLogin();
					window.frmLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ClientLogin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		frmLogin = new JFrame();
		frmLogin.setTitle("Login");
		frmLogin.setIconImage(
		Toolkit.getDefaultToolkit().getImage(ClientWindow.class.getResource("/images/pikachu.png")));
		frmLogin.setAlwaysOnTop(true);
		frmLogin.setBounds(100, 100, 300, 300);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, 0.0, 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };

		frmLogin.getContentPane().setLayout(gridBagLayout);
		frmLogin.getContentPane().setBackground(new java.awt.Color(255, 255, 204));

		JLabel lblEnterYourNickname = new JLabel("Enter your nickname:");
		GridBagConstraints gbc_lblEnterYourNickname = new GridBagConstraints();
		gbc_lblEnterYourNickname.insets = new Insets(0, 0, 5, 5);
		gbc_lblEnterYourNickname.gridx = 1;
		gbc_lblEnterYourNickname.gridy = 1;
		frmLogin.getContentPane().add(lblEnterYourNickname, gbc_lblEnterYourNickname);

		loginNickname = new JTextField();
		GridBagConstraints gbc_loginNickname = new GridBagConstraints();
		gbc_loginNickname.fill = GridBagConstraints.HORIZONTAL;
		gbc_loginNickname.insets = new Insets(0, 0, 5, 5);
		gbc_loginNickname.gridx = 1;
		gbc_loginNickname.gridy = 2;
		frmLogin.getContentPane().add(loginNickname, gbc_loginNickname);
		loginNickname.setColumns(30);

		JLabel lblEnterYouPassword = new JLabel("Enter you password:");
		GridBagConstraints gbc_lblEnterYouPassword = new GridBagConstraints();
		gbc_lblEnterYouPassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblEnterYouPassword.gridx = 1;
		gbc_lblEnterYouPassword.gridy = 3;
		frmLogin.getContentPane().add(lblEnterYouPassword, gbc_lblEnterYouPassword);

		JButton btnLogOn = new JButton("Log On");
		btnLogOn.setBackground(new java.awt.Color(255, 102, 102));
		btnLogOn.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnLogOn.setForeground(Color.WHITE);

		btnLogOn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String hash = "";
				String code = String.copyValueOf(loginPassword.getPassword());

				try {

					hash = ClientRegister.hashPassword(code);

				} catch (Exception e) {
					e.printStackTrace();
				} finally {
				boolean check = false;
				while(!check) {
					
					if (!ClientInfo.getConnection(loginNickname.getText(), hash)) {
						JOptionPane.showMessageDialog(null, "Invalid nickname or password");
						break;
					} else {				
					frmLogin.dispose();

					String name = loginNickname.getText();
					ClientWindow chatWindow = new ClientWindow(name);
					chatWindow.frmPikachat.setVisible(true);
					check = true;
						}
					}
				}

			}
		});

		loginPassword = new JPasswordField();
		GridBagConstraints gbc_loginPassword = new GridBagConstraints();
		gbc_loginPassword.insets = new Insets(0, 0, 5, 5);
		gbc_loginPassword.fill = GridBagConstraints.HORIZONTAL;
		gbc_loginPassword.gridx = 1;
		gbc_loginPassword.gridy = 4;
		frmLogin.getContentPane().add(loginPassword, gbc_loginPassword);
		GridBagConstraints gbc_btnLogOn = new GridBagConstraints();
		gbc_btnLogOn.insets = new Insets(0, 0, 5, 5);
		gbc_btnLogOn.gridx = 1;
		gbc_btnLogOn.gridy = 5;
		frmLogin.getContentPane().add(btnLogOn, gbc_btnLogOn);

		JButton btnRegister = new JButton("Register ");
		btnRegister.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnRegister.setForeground(Color.WHITE);
		btnRegister.setBackground(new java.awt.Color(255, 102, 102));
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				frmLogin.dispose();
				ClientRegister window = new ClientRegister();
				window.frame.setVisible(true);

			}
		});
		GridBagConstraints gbc_btnRegister = new GridBagConstraints();
		gbc_btnRegister.insets = new Insets(0, 0, 0, 5);
		gbc_btnRegister.gridx = 1;
		gbc_btnRegister.gridy = 6;
		frmLogin.getContentPane().add(btnRegister, gbc_btnRegister);
	}

}