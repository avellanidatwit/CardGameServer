package files;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
/**
 * Class to establish a user's identity and hand.
 * @author evelyn
 */
public final class User {
	
	final protected String USERNAME;
	protected Deck pile;
	protected Deck hand;
	protected Deck trash;
	protected static int totalId = 0;
	protected int id;
	
	public User() {
		super();
		this.USERNAME = null;
		this.pile = null;
		this.trash = null;
		this.id = -1;
	}
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
		pile.addCard(CardCreator.getInstance().createCard("Forest Booster"));
		
		// Create the deck objects for the player
		this.trash = new Deck();
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
		else return null;}
	
	/**
	 * Removes card from hand and adds to bottom of the pile.
	 */
	public void discardCard(Card card) {
		hand.removeCard(card);
		moveToPile(card);}
	
	/**
	 * Moves a card to the pile and shuffles it.
	 */
	public void moveToPile(Card card) {
		pile.addCard(card);
		pile.shuffleDeck();}
	/**
	 * Trashes a card in the pile.
	 */
	public Card destroyPileCard() {
		Card card = pile.getCard();
		trash.addCard(card);
		return card;}
	
	public ArrayList<Card> ForestBooster() {
		int range = randomIntRange(3, 5);
		ArrayList<Card> list = new ArrayList<Card>();
		Card card;
		for (int i = 0; i < range; i++) {
			switch(randomIntRange(1, 3)) {
			case 1: 
				card = CardCreator.getInstance().createCard("Log");
				moveToPile(card);
				list.add(card);
				break;
			case 2:
				card = CardCreator.getInstance().createCard("Stone");
				moveToPile(card);
				list.add(card);
				break;
			case 3:
				card = CardCreator.getInstance().createCard("Rope");
				moveToPile(card);
				list.add(card);
				break;
			}
		}
		return list;
	}
	
	public ArrayList<Card> Log() {
		int range = randomIntRange(2, 3);
		ArrayList<Card> list = new ArrayList<Card>();
		Card card;
		for (int i = 0; i < range; i++) {
			card = CardCreator.getInstance().createCard("Stick");
			moveToPile(card);
			list.add(card);
		}
		return list;
	}
	
	public ArrayList<Card> Stone() {
		int range = randomIntRange(2, 3);
		ArrayList<Card> list = new ArrayList<Card>();
		Card card;
		for (int i = 0; i < range; i++) {
			card = CardCreator.getInstance().createCard("Sharp Stone");
			moveToPile(card);
			list.add(card);
		}
		return list;
	}
	
	public ArrayList<Card> Bandage() {
		ArrayList<Card> list = new ArrayList<Card>();
		Card card;
		for (int i = 0; i < 2; i++) {
			card = CardCreator.getInstance().createCard("Bandage");
			moveToPile(card);
			list.add(card);
		}
		return list;
	}

	public ArrayList<Card> Stick() {
		ArrayList<Card> list = new ArrayList<Card>();
		Card card;
		switch (pile.cards.size()) {
		case 0:
			list = null;
			break;
		case 1:
			list.add(pile.getCard());
			break;
		default:
			list.add(pile.getCard());
			list.add(pile.getCard());
			break;
		}
		return list;
	}
	
	public Card SharpStone() {
		Card card;
		if (hand.cards.size() == 0) {return null;}
		else {
			card = hand.getCard();
			return card;}
	}
	
	public ArrayList<Card> Rope() {
		ArrayList<Card> list = new ArrayList<Card>();
		Card card;
		switch (hand.cards.size()) {
		case 0:
			list = null;
			break;
		case 1:
			list.add(hand.getCard());
			break;
		default:
			list.add(hand.getCard());
			list.add(hand.getCard());
			break;
		}
		return list;
	}
	/**
	 * Generates a random number between a min and max(inclusive)
	 * @param min
	 * @param max
	 * @return number between min and max
	 */
	public int randomIntRange(int start, int end) {
	    Random random = new Random();
	    int number = random.nextInt((end - start) + 1) + start; // see explanation below
	    return number;
	}
	
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
