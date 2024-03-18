package files;

import java.util.ArrayList;

public final class TrashDiscard extends Deck {
	/**
	 * 1 arg constuctor
	 * @param isTrash Trash boolean.
	 */
	public TrashDiscard() {
		super();
	}
	public TrashDiscard(ArrayList<Card> cards) {
		super(cards);
	}
	/**
	 * Method that moves the discard/trash to the draw pile.
	 * @param d
	 */
	public void moveToPile(Deck d) {
		Deck temp = new Deck(this.cards);
		this.cards.clear();
		temp.shuffleDeck();
		d = temp;
	}

}
