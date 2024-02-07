package files;

import java.util.Collections;
/**
 * Class that implements the dealer, and utilizes it to deal cards/trigger effects/manage turns.
 * @author evelyn
 */
public class Dealer implements DealerControls {
	
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
	/**
	 * Triggers all cards effects.
	 */
	@Override
	public void triggerEffects() {
		// TODO Auto-generated method stub
		
	}
	/**
	 * Triggers effects based upon a given card
	 * @param c Card to check against.
	 */
	@Override
	public void triggerEffects(Card c) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * Checks interactions with a given card.
	 * @param c Card to check against.
	 */
	@Override
	public void checkInteractions(Card c) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * Checks interactions with all cards in play.
	 */
	@Override
	public void checkInteractions() {
		// TODO Auto-generated method stub
		
	}
}
