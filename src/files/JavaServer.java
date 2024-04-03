package files;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

/**
 * A class that represents a game server which players connect to and get their
 * actions from.
 *
 * @author evelyn
 * @category Networking
 */
public class JavaServer {

	// Mapping connections to users
	private User[] users = new User[3];
	public Server server;

	private int actions = 5;

	public JavaServer() throws IOException {
		server = new Server();
		// Registering the classes to be sent over the network
		Network.register(server);

		Kryo kryo = server.getKryo();
		kryo.setRegistrationRequired(false);

		// Adding responses to client requests
		server.addListener(new Listener() {
			@SuppressWarnings("incomplete-switch")
			@Override
			public void received(Connection connection, Object object) {
				User user = users[connection.getID()];

				// Card behavior
				if (object instanceof Card) {
					Card card = (Card) object;
					switch (card.action) {
					case CRAFT:
						actions--;
						sendNumberOfActions(actions, connection.getID());
						card.action = Action.NONE;
						user.pile.addCard(card);
						String text = "Crafted a " + card.getName();
						printToUser(text, connection.getID());
						break;
					case REMOVEFROMHAND:
						card.action = Action.NONE;
						user.hand.removeCard(card);
						break;
					case NONE:
						actions--;
						sendNumberOfActions(actions, connection.getID());
						user.hand.removeCard(card);
						cardEffects(card, connection, user);
						break;
					}
					if (actions <= 0) {
						actions = 5;
						if (getOpponentInt(connection.getID()) == 1) {
							if (users[1].checkLoss()) {
								changeStatusOfAllUsers(TurnStates.PLAYER2WINS);
							} else {
								changeStatusOfAllUsers(TurnStates.PLAYER1TURN);
								sendNumberOfActions(actions, 1);
							}
						} else {
							if (users[1].checkLoss()) {
								changeStatusOfAllUsers(TurnStates.PLAYER1WINS);
							} else {
								changeStatusOfAllUsers(TurnStates.PLAYER2TURN);
								sendNumberOfActions(actions, 2);
							}
						}
					}
				}

				// Other request behavior
				if (object instanceof Request) {
					Request request = (Request) object;

					// Sends user data back to the client when a game starts
					if (request.message.equals("Init User")) {
						server.sendToTCP(connection.getID(), user);
					}

					// Draws a card and sends it to the user
					if (request.message.equals("Draw Card")) {
						Card card = user.drawCard();
						if (card != null) {
							server.sendToTCP(connection.getID(), card);
						}
					}
				}
			}

			// Handles removing clients
			@Override
			public void disconnected(Connection connection) {
				if (users[connection.getID()] != null) {
					users[connection.getID()] = null;
				}
				if (users[1] == null && users[2] == null) {
					server.stop();
				}
			}

			// Handles adding clients
			@Override
			public void connected(Connection connection) {
				if (users[connection.getID()] == null) {
					users[connection.getID()] = new User("Player " + connection.getID());
				}
				if (connection.getID() == 1) {
					changeStatus(TurnStates.WAITINGFORPLAYER2, connection.getID());
				} else if (connection.getID() == 2) {
					if ((new Random().nextInt(2) + 1) == 1) {
						changeStatusOfAllUsers(TurnStates.PLAYER1TURN);
						sendNumberOfActions(actions, 1);
						sendNumberOfActions(0, 2);
					} else {
						changeStatusOfAllUsers(TurnStates.PLAYER2TURN);
						sendNumberOfActions(actions, 2);
						sendNumberOfActions(0, 1);
					}
				}
			}
		});

		// Starts the server
		server.bind(54555, 54777);
		server.start();
	}

	public void cardEffects(Card card, Connection connection, User user) {
		// Forest Booster Pack
		if (card.getName().equals("Forest Booster")) {
			String text = "You found " + formatList(user.ForestBooster()) + ".";
			printToUser(text, connection.getID());
			user.discardCard(card);
		}

		// Log
		if (card.getName().equals("Log")) {
			String text = "You found " + formatList(user.Log()) + ".";
			printToUser(text, connection.getID());
		}

		// Stone
		if (card.getName().equals("Stone")) {
			String text = "You found " + formatList(user.Stone()) + ".";
			printToUser(text, connection.getID());
		}

		// Bandage
		if (card.getName().equals("Bandage")) {
			String text = "You found " + formatList(user.Bandage()) + ".";
			printToUser(text, connection.getID());
		}

		// Apple
		if (card.getName().equals("Bandage")) {
			String text = "You found " + formatList(user.Apple()) + ".";
			printToUser(text, connection.getID());
		}

		// Stick
		if (card.getName().equals("Stick")) {
			ArrayList<Card> stick = getOpponentUser(connection.getID()).Stick();
			String userOutput, opponent, list;
			if (stick == null) {
				userOutput = "Opponent has nothing to destroy";
				opponent = "Opponents Stick destroyed nothing";
			} else {
				list = formatList(stick);
				userOutput = "You destroyed your opponents " + list + ".";
				opponent = "Your " + list + " were destroyed.";
			}

			printToUser(userOutput, connection.getID());
			printToUser(opponent, getOpponentInt(connection.getID()));
		}

		// Sharp Stone
		if (card.getName().equals("Sharp Stone")) {
			Card sharpStone = getOpponentUser(connection.getID()).SharpStone();
			String userOutput, opponent;
			if (sharpStone == null) {
				userOutput = "Opponent's hand is empty";
				opponent = "Opponent's Sharp Stone destroyed nothing";
			} else {
				userOutput = "You destroyed your opponents " + sharpStone.getName() + ".";
				opponent = "Your " + sharpStone.getName() + " were destroyed.";
				sharpStone.action = Action.DISCARD;
				server.sendToTCP(getOpponentInt(connection.getID()), sharpStone);
			}

			printToUser(userOutput, connection.getID());
			printToUser(opponent, getOpponentInt(connection.getID()));
		}

		// Rope
		if (card.getName().equals("Rope")) {
			ArrayList<Card> rope = getOpponentUser(connection.getID()).Rope();
			String userOutput, opponent, list;
			if (rope == null) {
				userOutput = "Opponent has nothing to discard";
				opponent = "Opponents Rope discarded nothing";
			} else {
				list = formatList(rope);
				userOutput = "You discarded your opponents " + list + ".";
				opponent = "Your " + list + " were discarded.";
				for (Card i : rope) {
					i.action = Action.DISCARD;
					server.sendToTCP(getOpponentInt(connection.getID()), i);
				}
			}

			printToUser(userOutput, connection.getID());
			printToUser(opponent, getOpponentInt(connection.getID()));

		}
	}

	/**
	 * Sends a message object to the user to be displayed
	 *
	 * @param String - text to print
	 * @param int    - id of connection to send to
	 */
	public void printToUser(String string, int ID) {
		Text text = new Text(string);
		server.sendToTCP(ID, text);
	}

	/**
	 * Sends a message object to all users
	 *
	 * @param String - text to print
	 */
	public void changeStatusOfAllUsers(TurnStates state) {
		changeStatus(state, 1);
		changeStatus(state, 2);

	}

	/**
	 * Sends a packet to a user to change the status bar on their screen
	 *
	 * @param TurnStates - state to send
	 * @param int        - id of connection to send to
	 */
	public void changeStatus(TurnStates state, int ID) {
		Status status = new Status(state);
		server.sendToTCP(ID, status);
	}

	/**
	 * Sends a packet to a user to change the actions left bar on their screen
	 */
	public void sendNumberOfActions(int actions, int ID) {
		ActionsLeft actionsLeft = new ActionsLeft(actions);
		server.sendToTCP(ID, actionsLeft);
	}

	// TODO fix this function
	public String formatList(ArrayList<Card> list) {
		StringBuilder string = new StringBuilder();
		if (list.size() == 1) {
			return list.get(0).getName();
		} else {
			for (int i = 0; i < list.size(); i++) {
				if (i == list.size() - 1) {
					string.append("and a ");
					string.append(list.get(i).getName());
				} else {
					string.append(list.get(i).getName());
					string.append(", ");
				}
			}
			return string.toString();
		}
	}

	/**
	 * Returns user class of opponent
	 *
	 * @param int - user connection id
	 */
	public User getOpponentUser(int id) {
		if (id == 1) {
			return users[2];
		} else {
			return users[1];
		}
	}

	/**
	 * Returns id of opponent
	 *
	 * @param int - user connection id
	 */
	public int getOpponentInt(int id) {
		if (id == 1) {
			return 2;
		} else {
			return 1;
		}
	}
}