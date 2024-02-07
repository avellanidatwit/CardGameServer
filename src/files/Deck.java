package files;

import java.util.ArrayList;
import java.util.Collections;
/**
 * Class that establishes a deck, for use in the user hand and the overall deck of cards that will be drawn from.
 * @author evelyn
 */
public class Deck {
	protected int numberOfCards;
	protected ArrayList<Card> cards;
	
	/**
	 * No-arg constructor.
	 */
	public Deck() {
		this.numberOfCards = 0;
	}
	/**
	 * Gets number of cards.
	 * @return
	 */
	public int getNumberOfCards() {
		return this.numberOfCards;
	}
	/**
	 * Sets number of cards.
	 * @param n Value to set.
	 */
	public void setNumberOfCards(int n) {
		this.numberOfCards = n;
	}
	/**
	 * Increases the number of cards by 1.
	 */
	public void incrementNumberOfCards() {
		this.numberOfCards++;
	}
	/**
	 * Decreases the number of cards by 1.
	 */
	public void decrementNumberOfCards() {
		this.numberOfCards--;
	}
	/**
	 * Gets cards in the deck.
	 * @return Returns the cards in the deck.
	 */
	public ArrayList<Card> getCards() {
		return this.cards;
	}
	/**
	 * Adds a card to the deck.
	 * @param c Card to add.
	 */
	public void addCard(Card c) {
		this.cards.add(c);
	}
	/**
	 * Removes a card from the deck.
	 * @param c Card to remove.
	 */
	public void removeCard(Card c) {
		this.cards.remove(c);
	}
	/**
	 * Shuffles the deck.
	 */
	public void shuffleDeck() {
		Collections.shuffle(this.cards);
	}
	
	@Override
	public String toString() {
		return "Number of Cards: " + this.numberOfCards + "\nCards: " + this.cards;
	}
}
