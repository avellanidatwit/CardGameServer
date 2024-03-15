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
	protected boolean usability;
	/**
	 * 5-arg constructor.
	 * @param name Name of card.
	 * @param description Description of card.
	 * @param usability Whether or not card is usable at a current given moment.
	 * @param level Level of card.
	 * @param type Type of card.
	 */
	public Card(String name, String description, boolean usability, CardLevel level, CardTypes type) {
		this.NAME = name;
		this.DESCRIPTION = description;
		this.usability = usability;
		this.level = level;
		this.type = type;
	}
	/**
	 * 4-arg constructor Type 1.
	 * @param name Name of card.
	 * @param description Description of card.
	 * @param usability Whether or not card is usable at a current given moment.
	 * @param level Current level of card.
	 */
	public Card(String name, String description, boolean usability, CardLevel level) {
		this.NAME = name;
		this.DESCRIPTION = description;
		this.level = level;
		this.type = CardTypes.NULL;
	}
	/**
	 *4-arg constructor Type 2.
	 * @param name Name of card.
	 * @param description Description of card.
	 * @param usability Whether or not a card is usable at a current given moment.
	 * @param type Type of card.
	 */
	public Card(String name, String description, boolean usability, CardTypes type) {
		this.NAME = name;
		this.DESCRIPTION = description;
		this.usability = usability;
		this.level = CardLevel.LV0;
		this.type = type;
	}
	/**
	 * 3-arg constructor.
	 * @param name Name of card.
	 * @param description Description of card.
	 * @param usability Whether or not a card is usable at a current given moment.
	 */
	public Card(String name, String description, boolean usability) {
		this.NAME = name;
		this.DESCRIPTION = description;
		this.usability = usability;
		this.level = CardLevel.LV0;
		this.type = CardTypes.NULL;
	}
	/**
	 * No-arg constructor (mostly unused)
	 */
	public Card() {
		this.NAME = null;
		this.DESCRIPTION = null;
		this.usability = false;
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
	@Override
	public CardLevel getCardLevel() {
		return this.level;
	}
	/**
	 * Sets the card level.
	 * @param l Level to set.
	 */
	@Override
	public void setCardLevel(CardLevel l) {
		this.level = l;
	}
	/**
	 * Returns the card's type.
	 * @return Value to return.
	 */
	public CardTypes getCardType() {
		return this.type;
	}
	/**
	 * Sets a card's type.
	 * @param t Type to set.
	 */
	public void setCardType(CardTypes t) {
		this.type = t;
	}
	/**
	 * Gets whether or not a card is usable.
	 * @return Usability of card.
	 */
	public boolean getUsability() {
		return this.usability;
	}
	/**
	 * Sets a card's usability
	 * @param u Usability boolean to set.
	 */
	public void setUsability(boolean u) {
		this.usability = u;
	}
	/**
	 * Triggers a card's effects. This method is not to be called, and should later be implemented to throw an error that is handled appropriately as such.
	 * @throws Exception 
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
