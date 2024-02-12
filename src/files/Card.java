package files;
/**
 * A class to make a card, for use in the game.
 * @author evelyn
 */
public abstract sealed class Card implements CardEffects permits LocationCard, RemovalCard, RevealCard, UpgradeCard {
	final protected String NAME;
	final protected String DESCRIPTION;
	protected CardLevel level;
	protected CardTypes type;
	/**
	 * 4-arg constructor.
	 * @param name Name of card.
	 * @param description Description of card.
	 * @param level Level of card.
	 * @param type Type of card.
	 */
	public Card(String name, String description, CardLevel level, CardTypes type) {
		this.NAME = name;
		this.DESCRIPTION = description;
		this.level = level;
		this.type = type;
	}
	/**
	 * 3-arg constructor Type 1.
	 * @param name Name of card.
	 * @param description Description of card.
	 * @param level Current level of card.
	 */
	public Card(String name, String description, CardLevel level) {
		this.NAME = name;
		this.DESCRIPTION = description;
		this.level = level;
		this.type = CardTypes.NULL;
	}
	/**
	 * 3-arg constructor Type 2.
	 * @param name Name of card.
	 * @param description Description of card.
	 * @param type Type of card.
	 */
	public Card(String name, String description, CardTypes type) {
		this.NAME = name;
		this.DESCRIPTION = description;
		this.level = CardLevel.LV0;
		this.type = type;
	}
	/**
	 * 2-arg constructor.
	 * @param name Name of card.
	 * @param description Description of card.
	 */
	public Card(String name, String description) {
		this.NAME = name;
		this.DESCRIPTION = description;
		this.level = CardLevel.LV0;
		this.type = CardTypes.NULL;
	}
	/**
	 * No-arg constructor (mostly unused)
	 */
	public Card() {
		this.NAME = null;
		this.DESCRIPTION = null;
		this.level = CardLevel.LV0;
		this.type = CardTypes.NULL;
	}
	/**
	 * Gets the name of the card.
	 * @return Name of the card.
	 */
	public String getName() {
		return this.NAME;
	}
	/**
	 * Gets the description of the card.
	 * @return Description of the card.
	 */
	public String getDescription() {
		return this.DESCRIPTION;
	}
	/**
	 * Gets the card level.
	 * @return The card's current level.
	 */
	public CardLevel getCardLevel() {
		return this.level;
	}
	/**
	 * Sets the card level.
	 * @param l Level to set.
	 */
	public void setCardLevel(CardLevel l) {
		this.level = l;
	}
	/**
	 * Triggers a card's effects. This method is not to be called, and should later be implemented to throw an error that is handled appropriately as such.
	 */
	public void trigger() {
		System.out.println("Unimplemented Superclass Method");
	}
	
	@Override
	public String toString() {
		return "Name: " + this.NAME + "\nDescription: " + this.DESCRIPTION;
	}
	@Override
	public boolean equals(Object o) {
		if(o instanceof Card) {
			Card temp = (Card) o;
			if(temp.getName().equals(this.NAME) && temp.getDescription().equals(this.DESCRIPTION) && temp.getCardLevel().equals(this.level)) {
				return true;
			}
			
		}
		return false;
	}
}
