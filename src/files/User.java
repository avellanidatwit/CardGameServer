package files;
import java.io.IOException;
/**
 * Class to establish a user's identity and hand.
 * @author evelyn
 */
public final class User extends JavaClient {
	
	final protected String USERNAME;
	protected Deck pile;
	protected Deck trash;
	protected Deck discard;
	protected int id = 0;
	/**
	 * 0-arg constructor, should never be used.
	 * @throws IOException
	 */
	public User() throws IOException {
		super();
		this.USERNAME = null;
		this.pile = null;
		this.trash = null;
		this.discard = null;
		this.id = -1;
	}
	/**
	 * 1-arg Constructor
	 * @param username A user's username.
	 * @throws IOException
	 */
	public User(String username) throws IOException {
		super();
		if(id == 0) {
			this.id = 0;
		}
		else {
			this.id = id++;
			id++;
		}
		this.USERNAME = username;
		this.pile = new Deck();
		pile.addCard(CardCreator.createCard("Forest"));
		this.trash = new Deck();
		this.discard = new Deck();
	}
	/**
	 * Returns the user's username.
	 * @return The user's username.
	 */
	public String getUsername() {return this.USERNAME;}
	/**
	 * Draws a card from the pile. If the pile is empty, it reshuffles the discard and adds it to the pile.
	 */
	public Card drawCard() {
		if(pile.isEmpty() == false) {
			return pile.drawCard();
		}
		else if (discard.isEmpty() == false) {
			pile.reshuffleDeck(discard.getCards());
			discard.emptyDeck();
			return pile.drawCard();
		}
		else return null;
	}
	/**
	 * Adds a card to the discard.
	 */
	public void discard(Card card) {discard.addCard(card);}
	
	@Override
	public String toString() {
		return "Username: " + this.USERNAME;
	}
	@Override
	public boolean equals(Object o) {
		if(o instanceof User) {
			User temp = (User) o;
			if(this.USERNAME.equals(temp.getUsername())) {
				return true;
			}
		}
		return false;
	}
}
