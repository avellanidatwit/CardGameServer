package files;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;


/**
 * Manages the player's turns, and game as a whole.
 */
public class TurnManager {
	private final ServerSocket server;
	protected final int port;

	/**
	 * 1 arg constructor.
	 * @param port Port to set.
	 * @throws IOException
	 */
	public TurnManager(int port) throws IOException {
		server = new ServerSocket(port);
		this.port = port;
	}
	/**
	 * No-arg constructor.
	 * @throws IOException
	 */
	public TurnManager() throws IOException {
		server = new ServerSocket(3333);
		this.port = 3333;
	}
	/**
	 * Closes the server-side connection.
	 * @throws IOException
	 */
	public void serverClose() throws IOException {
		server.close();
	}
}
