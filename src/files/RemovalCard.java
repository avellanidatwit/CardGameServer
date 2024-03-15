package files;
/**
 * Card type that removes cards, in a generic fashion, implemented to possible remove from deck, or user.
 * @author evelyn
 */
public final class RemovalCard extends Card implements CardEffects {
	
	public RemovalCard(String NAME, String DESCRIPTION, boolean usability, CardLevel level) {
		super(NAME, DESCRIPTION, usability, level, CardTypes.REMOVER);
	}
	/**
	 * 2 arg constructor.
	 * @param NAME Name of card.
	 * @param DESCRIPTION Description of card.
	 */
	public RemovalCard(String NAME, String DESCRIPTION, boolean usability) {
		super(NAME, DESCRIPTION, usability, CardLevel.LV0, CardTypes.REMOVER);
	}
	/**
	 * Triggers a card's effects.
	 */
	@Override
	public void trigger() {
		
	}
	/**
	 * Triggers a card's effects on another card.
	 * @param c Card to trigger against.
	 */
	@Override
	public void trigger(Card c) {
		
	}

}
