package server;

import client.Client;
import client.ClientLogin;
import client.ClientWindow;

public class ServerStart {

	public static void main(String[] args) {

		Server.start(3306);

	}

}