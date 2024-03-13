package files;

import java.util.ArrayList;

public final class TrashDiscard extends Deck {
	
	protected boolean isTrash;
	
	public TrashDiscard(boolean isTrash) {
		super();
		this.isTrash = isTrash;
	}
	public TrashDiscard(boolean isTrash, int numberOfCards, ArrayList<Card> cards) {
		super(numberOfCards,cards);
		this.isTrash = isTrash;
	}

}
