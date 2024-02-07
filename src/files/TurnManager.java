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
public abstract class TurnManager {
	private final ServerSocket server;
	protected final int port;
	protected TurnStates currentPhase;

	/**
	 * 1 arg constructor.
	 * @param port Port to set.
	 * @throws IOException
	 */
	public TurnManager(int port) throws IOException {
		server = new ServerSocket(port);
		this.port = port;
		currentPhase = TurnStates.USER1PLAY;
	}
	/**
	 * 2 arg constructor.
	 * @param port Port to set.
	 * @param currentTurnPhase Turn phase to set.
	 * @throws IOException
	 */
	public TurnManager(int port, TurnStates currentTurnPhase) throws IOException {
		server = new ServerSocket(port);
		this.port = port;
		currentPhase = currentTurnPhase;
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
	/**
	 * Initializes the server-side connection.
	 * @throws IOException
	 */
	public void serverInit() throws IOException {
		Socket s = server.accept();
	}
	/**
	 * Updates the visuals on the JavaFX side.
	 */
	public void visualUpdate() {
		
	}
	
	public void advanceTurn() {
		if(this.currentPhase.equals(TurnStates.USER1PLAY)) {
			this.currentPhase = TurnStates.USER2PLAY;
		}
		else if(this.currentPhase.equals(TurnStates.USER2PLAY)) {
			this.currentPhase = TurnStates.GETEFFECTS;
		}
		else if(this.currentPhase.equals(TurnStates.GETEFFECTS)) {
			this.currentPhase = TurnStates.TRIGGEREFFECTS;
		}
		else if(this.currentPhase.equals(TurnStates.TRIGGEREFFECTS)) {
			this.currentPhase = TurnStates.SYNCDECK;
		}
		else if(this.currentPhase.equals(TurnStates.SYNCDECK)) {
			this.currentPhase = TurnStates.CHECKWINCONDITIONS;
		}
		else if(this.currentPhase.equals(TurnStates.CHECKWINCONDITIONS)) {
			this.currentPhase = TurnStates.RESET;
		}
		else if(this.currentPhase.equals(TurnStates.RESET)) {
			this.currentPhase = TurnStates.AWAITFORSYNC;
		}
	}
}
