package files;
/**
 * A class to make a card, for use in the game.
 * @author evelyn
 */
public class Card {
	final protected String name;
	final protected String description;
	/**
	 * 2-arg constructor.
	 * @param name Name of card.
	 * @param description Description of card.
	 */
	public Card(String name, String description) {
		this.name = name;
		this.description = description;
	}
	/**
	 * No-arg constructor (mostly unused)
	 */
	public Card() {
		this.name = null;
		this.description = null;
	}
	/**
	 * Gets the name of the card.
	 * @return Name of the card.
	 */
	public String getName() {
		return this.name;
	}
	/**
	 * Gets the description of the card.
	 * @return Description of the card.
	 */
	public String getDescription() {
		return this.description;
	}
	
	@Override
	public String toString() {
		return "Name: " + this.name + "\nDescription: " + this.description;
	}
	@Override
	public boolean equals(Object o) {
		if(o instanceof Card) {
			Card temp = (Card) o;
			if(temp.getName().equals(this.name) && temp.getDescription().equals(this.description)) {
				return true;
			}
			
		}
		return false;
	}
}
