package files;

import javafx.scene.image.Image;

/**
 * A class to make a card, for use in the game.
 * @author evelyn
 */
public final class Card {
	final protected String NAME;
	final protected String DESCRIPTION;
	final protected String IMAGE;
	
	final protected int priority;
	protected int uses, maxUses;
	
	public Card() {
		this.NAME = "";
		this.DESCRIPTION = "";
		this.IMAGE = "";
		this.priority = 1;
	}
	
	/**
	 * 3-arg constructor.
	 * @param name Name of card.
	 * @param description Description of card.
	 * @param image Card image.
	 */
	public Card(String name, String description, String image) {
		this.NAME = name;
		this.DESCRIPTION = description;
		this.IMAGE = image;
		this.priority = 1;
	}
	
	public Card(String name, String description, String image, int priority) {
		this.NAME = name;
		this.DESCRIPTION = description;
		this.IMAGE = image;
		this.priority = priority;
	}
	
	/**
	 * Gets the name of the card.
	 * @return Name of the card.
	 */
	public String getName() {
		return this.NAME;
	}
	/**
	 * Gets the priority of the card.
	 * @return Description of the card.
	 */
	public int getPriority() {
		return this.priority;
	}
	/**
	 * Gets the description of the card.
	 * @return Description of the card.
	 */
	public String getDescription() {
		return this.DESCRIPTION;
	}
	/**
	 * Gets the name of the card.
	 * @return Name of the card.
	 */
	public String getImage() {
		return this.IMAGE;
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
		return this.NAME;
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
	
	public void trigger(Card c) {
		// TODO Auto-generated method stub
		
	}
}
