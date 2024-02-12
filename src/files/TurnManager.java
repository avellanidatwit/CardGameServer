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
public abstract sealed class TurnManager extends GUIController permits Dealer {
	private final ServerSocket SERVER;
	protected final int PORT;
	protected TurnStates currentPhase;

	/**
	 * 1 arg constructor.
	 * @param PORT PORT to set.
	 * @throws IOException
	 */
	public TurnManager(int PORT) throws IOException {
		SERVER = new ServerSocket(PORT);
		this.PORT = PORT;
		currentPhase = TurnStates.USER1PLAY;
	}
	/**
	 * 2 arg constructor.
	 * @param PORT PORT to set.
	 * @param currentTurnPhase Turn phase to set.
	 * @throws IOException
	 */
	public TurnManager(int PORT, TurnStates currentTurnPhase) throws IOException {
		SERVER = new ServerSocket(PORT);
		this.PORT = PORT;
		currentPhase = currentTurnPhase;
	}
	/**
	 * No-arg constructor.
	 * @throws IOException
	 */
	public TurnManager() throws IOException {
		SERVER = new ServerSocket(3333);
		this.PORT = 3333;
	}
	/**
	 * Closes the server-side connection.
	 * @throws IOException
	 */
	public void serverClose() throws IOException {
		SERVER.close();
	}
	/**
	 * Initializes the server-side connection.
	 * @throws IOException
	 */
	public void serverInit() throws IOException {
		Socket s = SERVER.accept();
	}
	/**
	 * Advances the turn forward 1 move, resets to 1st move if at end.;
	 */
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
		else {
			this.currentPhase = TurnStates.USER1PLAY;
		}
	}
}
