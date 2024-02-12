package files;
/**
 * Card type that removes cards, in a generic fashion, implemented to possible remove from deck, or user.
 * @author evelyn
 */
public final class RemovalCard extends Card implements CardEffects {
	
	public RemovalCard(String NAME, String DESCRIPTION, CardLevel level) {
		super(NAME, DESCRIPTION, level, CardTypes.REMOVER);
	}
	/**
	 * 2 arg constructor.
	 * @param NAME Name of card.
	 * @param DESCRIPTION Description of card.
	 */
	public RemovalCard(String NAME, String DESCRIPTION) {
		super(NAME, DESCRIPTION, CardLevel.LV0, CardTypes.REMOVER);
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

	@Override
	public CardTypes getCardType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setCardType(CardTypes t) {
		// TODO Auto-generated method stub

	}

}
