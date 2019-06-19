package client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
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

public class ClientLogin {

	public JFrame frame;
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
	public ClientLogin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 330, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, 0.0, 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		frame.getContentPane().setLayout(gridBagLayout);

		JLabel lblEnterYourNickname = new JLabel("Enter your nickname:");
		GridBagConstraints gbc_lblEnterYourNickname = new GridBagConstraints();
		gbc_lblEnterYourNickname.insets = new Insets(0, 0, 5, 5);
		gbc_lblEnterYourNickname.gridx = 1;
		gbc_lblEnterYourNickname.gridy = 1;
		frame.getContentPane().add(lblEnterYourNickname, gbc_lblEnterYourNickname);

		loginNickname = new JTextField();
		GridBagConstraints gbc_loginNickname = new GridBagConstraints();
		gbc_loginNickname.fill = GridBagConstraints.HORIZONTAL;
		gbc_loginNickname.insets = new Insets(0, 0, 5, 5);
		gbc_loginNickname.gridx = 1;
		gbc_loginNickname.gridy = 2;
		frame.getContentPane().add(loginNickname, gbc_loginNickname);
		loginNickname.setColumns(30);

		JLabel lblEnterYouPassword = new JLabel("Enter you password:");
		GridBagConstraints gbc_lblEnterYouPassword = new GridBagConstraints();
		gbc_lblEnterYouPassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblEnterYouPassword.gridx = 1;
		gbc_lblEnterYouPassword.gridy = 3;
		frame.getContentPane().add(lblEnterYouPassword, gbc_lblEnterYouPassword);
<<<<<<< HEAD

		JButton btnLogOn = new JButton("Log On");
=======
		
		//JButton btnLogOn = new JButton("Log On");
		JButton btnLogOn = new JButton(" Log On  ");
		btnLogOn.setBackground(new Color(106, 90, 255));
		btnLogOn.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnLogOn.setForeground(new Color(106, 90, 255));
>>>>>>> branch 'master' of https://github.com/AntonILLI/JavaProjectAspireTerm1.git
		btnLogOn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {

					frame.dispose();

					ClientInfo.getConnection(loginNickname.getText(), loginPassword.getPassword());
					// boolean check = ClientInfo.getConnection(loginNickname.getText(),
					// loginPassword.getPassword());
					// if(check == true) {
					String name = loginNickname.getText();
					ClientWindow chatWindow = new ClientWindow(name);
					chatWindow.frmPikachat.setVisible(true);
					// }

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		loginPassword = new JPasswordField();
		GridBagConstraints gbc_loginPassword = new GridBagConstraints();
		gbc_loginPassword.insets = new Insets(0, 0, 5, 5);
		gbc_loginPassword.fill = GridBagConstraints.HORIZONTAL;
		gbc_loginPassword.gridx = 1;
		gbc_loginPassword.gridy = 4;
		frame.getContentPane().add(loginPassword, gbc_loginPassword);
		GridBagConstraints gbc_btnLogOn = new GridBagConstraints();
		gbc_btnLogOn.insets = new Insets(0, 0, 5, 5);
		gbc_btnLogOn.gridx = 1;
		gbc_btnLogOn.gridy = 5;
		frame.getContentPane().add(btnLogOn, gbc_btnLogOn);

		JButton btnRegister = new JButton("Register ");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				frame.dispose();
				ClientRegister window = new ClientRegister();
				window.frame.setVisible(true);

			}
		});
		GridBagConstraints gbc_btnRegister = new GridBagConstraints();
		gbc_btnRegister.insets = new Insets(0, 0, 0, 5);
		gbc_btnRegister.gridx = 1;
		gbc_btnRegister.gridy = 6;
		frame.getContentPane().add(btnRegister, gbc_btnRegister);
	}

}