package files;

import java.util.ArrayList;

public final class TrashDiscard extends Deck {
	
	protected boolean isTrash;
	/**
	 * 1 arg constuctor
	 * @param isTrash Trash boolean.
	 */
	public TrashDiscard(boolean isTrash) {
		super();
		this.isTrash = isTrash;
	}
	/**
	 * 2 arg constuctor
	 * @param isTrash Trash boolean.
	 * @param cards Cards to set.
	 */
	public TrashDiscard(boolean isTrash, ArrayList<Card> cards) {
		super(cards.size(), cards);
		this.isTrash = isTrash;
	}
	/**
	 * 3 arg constuctor
	 * @param isTrash Trash boolean.
	 * @param numberOfCards Number of cards.
	 * @param cards Cards to set.
	 */
	public TrashDiscard(boolean isTrash, int numberOfCards, ArrayList<Card> cards) {
		super(numberOfCards,cards);
		this.isTrash = isTrash;
	}
	/**
	 * Sets the trash boolean.
	 * @param b The trash boolean's new value.
	 */
	public void setIsTrash(boolean b) {
		this.isTrash = b;
	}
	/**
	 * Gets the trash boolean.
	 * @return The trash boolean.
	 */
	public boolean getIsTrash() {
		return this.isTrash;
	}
	/**
	 * Method that moves the discard/trash to the draw pile.
	 * @param d
	 */
	public void moveToPile(Deck d) {
		d.setNumberOfCards(this.numberOfCards);
		this.numberOfCards = 0;
		Deck temp = new Deck(this.numberOfCards, this.cards);
		this.cards.clear();
		d = temp;
	}

}
