package files;

import java.io.IOException;
import java.net.InetAddress;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Listener.TypeListener;

import javafx.application.Platform;

/**
 * A players connection to the game server.
 *
 * @author evelyn
 * @category Networking
 */
public class JavaClient {
	public Client client;

	// A reference to the GUI script
	public VFXMethods vfxmethods;

	public JavaClient(VFXMethods vfxmethods) throws IOException {

		this.vfxmethods = vfxmethods;

		client = new Client();
		// Registering the classes to be sent over the network
		Network.register(client);
		InetAddress address = client.discoverHost(54777, 5000);

		Kryo kryo = client.getKryo();
		kryo.setRegistrationRequired(false);

		client.start();
		client.connect(5000, address, 54555, 54777);

		setupController();
	}

	// Server response handling
	public void setupController() {
		TypeListener typeListener = new TypeListener();

		// When the server sends a card object, the player draw the card.
		typeListener.addTypeHandler(Card.class, (connection, card) -> {
			if (card.action == Action.DISCARD) {
				card.action = Action.NONE;
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						vfxmethods.removeCardHromHand(card);
					}
				});
			} else {
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						vfxmethods.addCardToHand(card);
					}
				});
			}
		});

		// When the server sends a text object, the user gets a notification.
		typeListener.addTypeHandler(Text.class, (connection, text) -> {
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					vfxmethods.textNotification(text.message);
				}
			});
		});

		// When the server sends a ActionsLeft object, the users actions update.
		typeListener.addTypeHandler(ActionsLeft.class, (connection, actions) -> {
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					vfxmethods.changeActions(actions.num);
				}
			});
		});

		// When the server sends a status object, the GUI changes the status text.
		typeListener.addTypeHandler(Status.class, (connection, status) -> {
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					String text = null;
					switch (status.state) {
					case WAITINGFORPLAYER2:
						text = "Waiting for Player 2";
						break;
					case PLAYER1TURN:
						if (client.getID() == 1) {
							vfxmethods.unfreezeHand();
							text = "Your turn";
						} else {
							vfxmethods.freezeHand();
							text = "Opponent's turn";
						}
						break;
					case PLAYER2TURN:
						if (client.getID() == 2) {
							vfxmethods.unfreezeHand();
							text = "Your turn";
						} else {
							vfxmethods.freezeHand();
							text = "Opponent's turn";
						}
						break;
					case PLAYER1WINS:
						vfxmethods.freezeHand();
						if (client.getID() == 1) {
							text = "You win";
						} else {
							text = "Your Opponent won";
						}
						break;
					case PLAYER2WINS:
						vfxmethods.freezeHand();
						if (client.getID() == 2) {
							text = "You win";
						} else {
							text = "Your Opponent won";
						}
						break;
					}
					vfxmethods.changeStatus(text);
				}
			});
		});

		client.addListener(typeListener);
	}
}