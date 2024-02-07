package files;
/**
 * A class to make a card, for use in the game.
 * @author evelyn
 */
public abstract class Card {
	final protected String NAME;
	final protected String DESCRIPTION;
	/**
	 * 2-arg constructor.
	 * @param name Name of card.
	 * @param description Description of card.
	 */
	public Card(String name, String description) {
		this.NAME = name;
		this.DESCRIPTION = description;
	}
	/**
	 * No-arg constructor (mostly unused)
	 */
	public Card() {
		this.NAME = null;
		this.DESCRIPTION = null;
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
	
	@Override
	public String toString() {
		return "Name: " + this.NAME + "\nDescription: " + this.DESCRIPTION;
	}
	@Override
	public boolean equals(Object o) {
		if(o instanceof Card) {
			Card temp = (Card) o;
			if(temp.getName().equals(this.NAME) && temp.getDescription().equals(this.DESCRIPTION)) {
				return true;
			}
			
		}
		return false;
	}
}
