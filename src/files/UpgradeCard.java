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
		System.out.printf("Please give a card to upgrade in the prompt.");
	}
	/**
	 * Upgrades another card, via the trigger function.
	 * @param c Card to upgrade.
	 */
	public void trigger(Card c) {
		if(c.getCardLevel().equals(CardLevel.LV0)) {
			c.setCardLevel(CardLevel.LV1);
		}
		else if(c.getCardLevel().equals(CardLevel.LV1)) {
			c.setCardLevel(CardLevel.LV2);
		}
		else if(c.getCardLevel().equals(CardLevel.LV2)) {
			c.setCardLevel(CardLevel.LV3);
		}
		else if(c.getCardLevel().equals(CardLevel.LV3)) {
			c.setCardLevel(CardLevel.LV4);
		}
		else if(c.getCardLevel().equals(CardLevel.LV4)) {
			c.setCardLevel(CardLevel.LV5);
		}
		else {
			System.out.printf("Card cannot be upgraded.");
		}
	}

	@Override
	public CardTypes getCardType() {
		return this.type;
	}

	@Override
	public void setCardType(CardTypes t) {
		this.type = t;

	}

}
