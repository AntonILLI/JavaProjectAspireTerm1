package client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import java.awt.Toolkit;

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
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.ImageIcon;

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
	private JLabel label_1;
	private JLabel label_4;
	private JPanel panel_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

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
		frame.setAlwaysOnTop(true);
		frame.setBounds(100, 100, 400, 150);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		frame.setTitle("Register");
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(ClientWindow.class.getResource("/images/pikachu.png")));

		panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(0, 3, 0, 0));
		panel.setBackground(new java.awt.Color(255, 255, 204));

		lblName = new JLabel("Name: ");
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(lblName);

		cName = new JTextField();
		panel.add(cName);
		cName.setColumns(45);

		label_1 = new JLabel("");
		panel.add(label_1);

		lblNickname = new JLabel("Nickname:");
		lblNickname.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(lblNickname);

		cNickname = new JTextField();
		panel.add(cNickname);
		cNickname.setColumns(45);

		label_4 = new JLabel("");
		panel.add(label_4);

		lblPassword = new JLabel("Password:");
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(lblPassword);

		cPassword = new JPasswordField();
		cPassword.setColumns(45);
		panel.add(cPassword);

		panel_1 = new JPanel();
		panel_1.setBackground(new java.awt.Color(255, 102, 102));
		frame.getContentPane().add(panel_1, BorderLayout.SOUTH);

		btnNewButton = new JButton("Register");
		panel_1.add(btnNewButton);
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

				ClientLogin window = new ClientLogin();
				window.frmLogin.setVisible(true);

			}
		});

	}

	public static String hashPassword(String password) throws NoSuchAlgorithmException {

		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(password.getBytes());
		byte[] b = md.digest();
		StringBuffer sb = new StringBuffer();
		for (byte b1 : b) {
			sb.append(Integer.toHexString(b1 & 0xff).toString());
		}

		return sb.toString();
	}

}