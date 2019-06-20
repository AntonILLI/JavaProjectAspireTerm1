package client;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import server.ClientInfo;

public class Client {

	private DatagramSocket socket;
	public static InetAddress address;
	public static int port;
	public static String name;
	private boolean running;

	public static String getName() {
		return name;
	}

	public static InetAddress getAddress() {
		return address;
	}

	public static int getPort() {
		return port;
	}

	public Client(String name, String address, int port) {

		try {
			this.name = name;
			this.address = InetAddress.getByName(address);
			this.port = port;

			socket = new DatagramSocket();

			running = true;
			listen();
			send("\\con:" + name);

		} catch (Exception exception) {
			exception.printStackTrace();
		}

	}

	public void send(String message) {
		try {
			if (!message.startsWith("\\")) {
				message = name + ":" + message;
			}
			message += "\\e";
			byte[] data = message.getBytes();
			DatagramPacket packet = new DatagramPacket(data, data.length, address, port);
			socket.send(packet);
			System.out.println("Send Message To: " + address.getHostAddress() + " : " + port);

		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	private void listen() {
		Thread listenThread = new Thread("ChatProgram Listener") {
			public void run() {

				try {

					while (running) {
						byte[] data = new byte[1024];
						DatagramPacket packet = new DatagramPacket(data, data.length);
						socket.receive(packet);

						String message = new String(data);
						message = message.substring(0, message.indexOf("\\e"));

						if (!isCommand(message, packet)) {
							ClientWindow.printToConsole(message);
						}
					}

				} catch (Exception exception) {
					exception.printStackTrace();
				}

			}
		};
		listenThread.start();
	}

	public static boolean isCommand(String message, DatagramPacket packet) {

		if (message.startsWith("\\con:")) {

		}

		return false;
	}

}