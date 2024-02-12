package files;
/**
 * Card that reveals cards in deck/other player's hand.
 * @author evelyn
 */
public final class RevealCard extends Card implements CardEffects {
	/**
	 * 3 arg constructor.
	 * @param NAME Name of card.
	 * @param DESCRIPTION Description of card.
	 * @param level Level of card.
	 */
	public RevealCard(String NAME, String DESCRIPTION, CardLevel level) {
		super(NAME, DESCRIPTION, level, CardTypes.REVEALER);
	}
	/**
	 * 2 arg constructor.
	 * @param NAME Name of card.
	 * @param DESCRIPTION Description of card.
	 */
	public RevealCard(String NAME, String DESCRIPTION) {
		super(NAME, DESCRIPTION, CardLevel.LV0, CardTypes.REVEALER);
	}
	/**
	 * Triggers a card's effects.
	 */
	@Override
	public void trigger() {
		
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
	@Override
	public void trigger(Card c) {
		// TODO Auto-generated method stub
		
	}

}
