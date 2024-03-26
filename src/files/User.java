package files;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * Class to establish a user's identity and hand.
 * @author evelyn
 */
public final class User extends JavaClient {
	
	final protected String USERNAME;
	protected Deck pile;
	protected Deck hand;
	protected Deck trash;
	protected Deck discard;
	protected static int totalId = 0;
	protected int id;
	
	/**
	 * 1-arg Constructor
	 * @param username A user's username.
	 */
	public User(String username) {
		// Create id and username
		this.id = totalId;
		totalId++;
		this.USERNAME = username;
		
		// Add the forest booster to starter deck
		this.pile = new Deck();
		pile.addCard(CardCreator.getInstance().createCard("Forest"));
		
		// Create the deck objects for the player
		this.trash = new Deck();
		this.discard = new Deck();
		this.hand = new Deck();}
	
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
			Card card = pile.drawCard();
			hand.addCard(card);
			return card;
		}
		else if (discard.isEmpty() == false) {
			pile.reshuffleDeck(discard.getCards());
			discard.emptyDeck();
			Card card = pile.drawCard();
			hand.addCard(card);
			return card;
		}
		else return null;}
	
	/**
	 * Adds a card to the discard.
	 */
	public void discardCard(Card card) {
		hand.removeCard(card); 
		discard.addCard(card);}
	
	/**
	 * Trashes a card in the pile.
	 */
	public void destoryPileCard() {
		Card card = pile.getCard();
		pile.removeCard(card); 
		trash.addCard(card);}
	
	@Override
	public String toString() {return "Username: " + this.USERNAME;}
	
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
