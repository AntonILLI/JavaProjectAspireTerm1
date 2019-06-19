import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import org.junit.jupiter.api.Test;
import org.junit.runners.JUnit4;

import client.Client;
import server.ClientInfo;

class Testing {

	@Test
	public void testA() {
		Client user = new Client("John", "localhost", 3306);
		String name = "John";
		assertEquals(name, Client.getName());
	}
	
	@Test
	public void testB() throws Throwable {
		Client user = new Client("John", "localhost", 3306);
		InetAddress address = InetAddress.getByName("localhost");
		assertEquals(address, Client.getAddress());
	}
	
	@Test
	public void testC() {
		Client user = new Client("John", "localhost", 3306);
		int port = 3306;
		assertEquals(port, Client.getPort());	
	}

	@Test
	public void testD() {
		String user = "billy";
		String password = "b00bs";
		char[] c = password.toCharArray();
		boolean result = ClientInfo.getConnection(user, c);
		assertEquals(true, result);	
	}
	
	@Test
	public void testF() throws SQLException {
		String user = "admin";
		String nickname = "admin";
		String password = "admin";
		
		boolean result = ClientInfo.getConnection(user, nickname, password);
		assertEquals(false, result);
		/*
		 * Delete record from the database
		 */
		Connection cnx = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", null);
		PreparedStatement stmnt = cnx.prepareStatement("DELETE FROM `users_registered` WHERE `password` = 'admin'");
		stmnt.execute();
		}
}
