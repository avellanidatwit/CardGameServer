package files;
/**
 * Upgrades another card.
 * @author evelyn
 */
public final class UpgradeCard extends Card implements CardEffects {
	/**
	 * 3 arg constructor
	 * @param NAME Name of card.
	 * @param DESCRIPTION Description of card.
	 * @param level Level of card.
	 */
	public UpgradeCard(String NAME, String DESCRIPTION, CardLevel level) {
		super(NAME, DESCRIPTION, level, CardTypes.UPGRADER);
	}
	/**
	 * 2 arg constructor
	 * @param NAME Name of card.
	 * @param DESCRIPTION Description of card.
	 */
	public UpgradeCard(String NAME, String DESCRIPTION) {
		super(NAME, DESCRIPTION, CardLevel.LV0, CardTypes.UPGRADER);
	}
	/**
	 * Triggers a card's effects.
	 */
	public void trigger() {
		
	}
	@Override
	public String getEffects() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setEffects(Card e) {
		// TODO Auto-generated method stub

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
