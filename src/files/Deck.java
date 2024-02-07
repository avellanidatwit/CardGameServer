package files;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
	protected int numberOfCards;
	protected ArrayList<Card> cards;
	
	public Deck() {
		this.numberOfCards = 0;
	}
	
	public int getNumberOfCards() {
		return numberOfCards;
	}
	public void setNumberOfCards(int n) {
		this.numberOfCards = n;
	}
	public void incrementNumberOfCards() {
		this.numberOfCards++;
	}
	public void decrementNumberOfCards() {
		this.numberOfCards--;
	}
	
	public ArrayList<Card> returnCards() {
		return this.cards;
	}
	public void addCard(Card c) {
		this.cards.add(c);
	}
	public void removeCard(Card c) {
		this.cards.remove(c);
	}
	public void shuffleDeck() {
		Collections.shuffle(this.cards);
	}
	
	@Override
	public String toString() {
		return "Number of Cards: " + this.numberOfCards + "\nCards: " + this.cards;
	}
}
