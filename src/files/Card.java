package files;
/**
 * A class to make a card, for use in the game.
 * @author evelyn
 */
public abstract class Card {
	final protected String NAME;
	final protected String DESCRIPTION;
	protected CardLevel level;
	/**
	 * 3-arg constructor.
	 * @param name Name of card.
	 * @param description Description of card.
	 * @param level Current level of card.
	 */
	public Card(String name, String description, CardLevel level) {
		this.NAME = name;
		this.DESCRIPTION = description;
		this.level = level;
	}
	/**
	 * 2-arg constructor.
	 * @param name Name of card.
	 * @param description Description of card.
	 */
	public Card(String name, String description) {
		this.NAME = name;
		this.DESCRIPTION = description;
		level = CardLevel.LV0;
	}
	/**
	 * No-arg constructor (mostly unused)
	 */
	public Card() {
		this.NAME = null;
		this.DESCRIPTION = null;
		level = CardLevel.LV0;
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
