package server;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.HashSet;

public class Server {
	
	private static DatagramSocket socket;
	//private static int port;
	private static HashSet<ClientInfo> clients = new HashSet<ClientInfo>();
	public static int ClientID;
	public static boolean running;
	
	public static void start(int port) {
		try {
			
			socket = new DatagramSocket(port);
			running = true;
			listen();
			
			System.out.println("The Server Has Started On Port: " + port);
			
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
	
	private static void broadcast(String message) {
		for (ClientInfo info : clients) {
			send(message, info.getAddress(), info.getPort());
		}
	}
	
	private static void send(String message, InetAddress address, int port) {
		try {
			
			message += "\\e";
			byte[] data = message.getBytes();
			DatagramPacket packet = new DatagramPacket(data, data.length, address, port);
			socket.send(packet);
			System.out.println("Send Message To: " + address.getHostAddress() + " : " + port);
			
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
	
	private static void listen() {
		Thread listenThread = new Thread("ChatProgram Listener") {
			public void run() {
				
				try {
					
					while(running) {
						byte[] data = new byte[1024];
						DatagramPacket packet = new DatagramPacket(data, data.length);
						socket.receive(packet);
						
						String message = new String(data);
						message = message.substring(0, message.indexOf("\\e"));
						
						if(!isCommand(message, packet)) {
						broadcast(message);
						}
					}
					
					
				} catch (Exception exception) {
					exception.printStackTrace();
				}
				
			}
		};
		listenThread.start();
	}
		
	public static void stop() {
		running = false;
	}
	/*Server command list
	 * 
	 * \con - connect client to the server
	 * \dis - disconnect a client from the server
	 */
	public static boolean isCommand(String message, DatagramPacket packet) {
		
		if (message.startsWith("\\con:")) {
			
			String name = message.substring(message.indexOf(" : ") + 1);
			clients.add(new ClientInfo(name, ClientID++, packet.getAddress(), packet.getPort()));
			broadcast("User " + name + " connected");
			return true;
		}
		
		return false;
	}
}
