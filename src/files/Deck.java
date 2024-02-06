package files;

import java.util.ArrayList;

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
		return cards;
	}
}
