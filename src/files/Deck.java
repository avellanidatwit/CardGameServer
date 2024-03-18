package files;

import java.util.ArrayList;
import java.util.Collections;
/**
 * Class that establishes a deck, for use in the user hand and the overall deck of cards that will be drawn from.
 * @author evelyn
 */
public sealed class Deck permits TrashDiscard {
	protected ArrayList<Card> cards;
	
	/**
	 * No-arg constructor.
	 */
	public Deck() {
		this.cards = new ArrayList<Card>();
	}
	public Deck(ArrayList<Card> cards) {
		this.cards = cards;
	}
	/**
	 * Gets number of cards.
	 * @return Returns the number of cards
	 */
	public int getNumberOfCards() {
		return this.cards.size();
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
	 * Sets cards in the deck
	 * @param c New deck to set.
	 */
	public void setCards(ArrayList<Card> c) {
		this.cards = c;
	}
	/**
	 * Shuffles the deck.
	 */
	public void shuffleDeck() {
		Collections.shuffle(this.cards);
	}
	
	@Override
	public String toString() {
		return "Number of Cards: " + this.getNumberOfCards() + "\nCards: " + this.cards;
	}
	@Override
	public boolean equals(Object o) {
		if(o instanceof Deck) {
			Deck temp1 = this;
			Deck temp2 = (Deck) o;
			temp1.cards.sort(null);
			temp2.cards.sort(null);
			if(temp1.cards.equals(temp2.cards)) {
				return true;
			}
		}
		return false;
		
	}
}
