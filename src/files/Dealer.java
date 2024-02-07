package files;

import java.io.IOException;
import java.util.Collections;
/**
 * Class that implements the dealer, and utilizes it to deal cards/trigger effects/manage turns.
 * @author evelyn
 */
public class Dealer extends TurnManager implements DealerControls {
	
	protected Deck mainDeck;
	/**
	 * 2 arg constructor
	 * @param d Deck to set.
	 * @param port Port for server.
	 * @throws IOException 
	 */
	public Dealer(Deck d, int PORT) throws IOException {
		super(PORT);
		mainDeck = d;
	}
	/**
	 * 1 arg constructor
	 * @param d Deck to set.
	 * @throws IOException
	 */
	public Dealer(Deck d) throws IOException {
		super(3333);
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
	/**
	 * Triggers effects on a hand based upon a given card.
	 * @param c Card to base effects from.
	 * @param h Hand to affect.
	 */
	@Override
	public void triggerEffects(Card c, UserHand h) {
		// TODO Auto-generated method stub
		
	}
}
