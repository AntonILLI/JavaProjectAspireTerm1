package client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import server.ClientInfo;

import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.JTextPane;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.BoxLayout;

public class ClientWindow {

	protected JFrame frmPikachat;
	private JTextField messageField;
	private static JTextPane textArea = new JTextPane();

	private Client client;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

					ClientWindow window = new ClientWindow("User");
					window.frmPikachat.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ClientWindow(String nickname) {

		client = new Client(nickname, "localhost", 3306);

		initialize();
	}

	public void initialize() {
		frmPikachat = new JFrame();
		frmPikachat.setAlwaysOnTop(true);
		frmPikachat.setResizable(false);
		frmPikachat.setTitle("Pikachat");
		frmPikachat.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Aspire2 Student\\eclipse-workspace\\JavaProjectAspireTerm1\\Pikachat\\images\\pikachu.png"));
		frmPikachat.setBounds(100, 100, 450, 300);
		frmPikachat.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JScrollPane scrollPane = new JScrollPane();
		frmPikachat.getContentPane().add(scrollPane, BorderLayout.CENTER);
		textArea.setEditable(false);
		textArea.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		textArea.setBackground(new java.awt.Color(255, 255, 204));

		frmPikachat.getContentPane().add(textArea, BorderLayout.CENTER);

		JPanel panel = new JPanel();
		panel.setBackground(new java.awt.Color(255, 102, 102));
		frmPikachat.getContentPane().add(panel, BorderLayout.SOUTH);

		messageField = new JTextField();
		messageField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					client.send(messageField.getText());
					messageField.setText("");
				}
			}
		});
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		panel.add(messageField);
		messageField.setColumns(40);

		JButton btnSendMessage = new JButton("");
		btnSendMessage.setForeground(Color.WHITE);
		btnSendMessage.setBackground(new java.awt.Color(255, 255, 204));
		btnSendMessage.setIcon(new ImageIcon("C:\\Users\\Aspire2 Student\\eclipse-workspace\\JavaProjectAspireTerm1\\Pikachat\\images\\pokeball.png"));
		btnSendMessage.addActionListener(e -> {//try out lambda expression
			if (!messageField.getText().equals("")) {
				client.send(messageField.getText());
				messageField.setText("");
			}
		});
		panel.add(btnSendMessage);

		frmPikachat.setLocationRelativeTo(null);
	}

	public static void printToConsole(String message) {
		textArea.setText(textArea.getText() + message + "\n");
	}

}