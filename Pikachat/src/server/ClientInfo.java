package server;

import java.net.InetAddress;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

public class ClientInfo {

	public static boolean userConfirmed;
	private InetAddress address;
	private int port;
	private int id;
	private String name;

	public ClientInfo(String name, int id, InetAddress address, int port) {

		this.name = name;
		this.id = id;
		this.address = address;
		this.port = port;
	}

	public String getName() {
		return name;
	}

	public int getID() {
		return id;
	}

	public InetAddress getAddress() {
		return address;
	}

	public int getPort() {
		return port;
	}

	/*
	 * Register new user into MySQL database
	 */
	public static boolean getConnection(String cName, String cNickname, String cPassword) {

		Connection cnx = null;
		boolean executed = false;
		try {
			String url = "jdbc:mysql://localhost:3306/test";
			String user = "root";
			String password = null;

			cnx = DriverManager.getConnection(url, user, password);

			PreparedStatement stmnt = cnx
					.prepareStatement("INSERT INTO `users_registered`(`name`, `nickname`, `password`) VALUES ('" + cName
							+ "','" + cNickname + "','" + cPassword + "')");

			executed = stmnt.execute();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return executed;
	}

	/*
	 * Allow access to chat application reading nickname and password from the MySQL
	 * Database
	 */
	public static boolean getConnection(String loginName, String loginPassword) {

		Connection cnx = null;

		boolean userConfirmed = false;

		try {
			String url = "jdbc:mysql://localhost:3306/test";
			String user = "root";
			String password = null;

			cnx = DriverManager.getConnection(url, user, password);

			PreparedStatement stmnt = cnx.prepareStatement("SELECT * FROM `users_registered` WHERE `nickname` = '"
					+ loginName + "' AND `password` = '" + loginPassword + "'");

			ResultSet rs = stmnt.executeQuery();

			if (rs.next()) {
				System.out.println("Access granted to: " + loginName + " " + loginPassword);
				userConfirmed = true;
			} else {
				JOptionPane.showMessageDialog(null, "Invalid nickname and/or password");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return userConfirmed;
	}
}