package files;

import java.util.Collections;
/**
 * Class that implements the dealer, and utilizes it to deal cards/trigger effects/manage turns.
 * @author evelyn
 */
public class Dealer {
	
	protected Deck mainDeck;
	/**
	 * 1 arg constructor
	 * @param d Deck to set.
	 */
	public Dealer(Deck d) {
		mainDeck = d;
	}
	/**
	 * Returns the deck.
	 * @return The deck.
	 */
	public Deck getDeck() {
		return mainDeck;
	}
	/**
	 * Sets the deck.
	 * @param d The new deck.
	 */
	public void setDeck(Deck d) {
		mainDeck = d;
	}

}
